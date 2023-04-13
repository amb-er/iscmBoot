package com.armitage.server.activity.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TimerEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.armitage.server.activity.common.model.BillCondition;
import com.armitage.server.activity.common.service.BillConditionBiz;
import com.armitage.server.activity.model.ActivityCheckException;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ListSortUtil;
import com.armitage.server.user.model.Role;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.UsrOrgRange2;
import com.armitage.server.user.model.UsrRole2;
import com.armitage.server.user.service.RoleBiz;
import com.armitage.server.user.service.UsrBiz;
import com.armitage.server.user.service.UsrOrgRangeBiz;
import com.armitage.server.user.service.UsrRoleBiz;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class ActivityUtil {

	private static UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private static RoleBiz roleBiz = (RoleBiz) AppContextUtil.getBean("roleBiz");
	private static BillConditionBiz billConditionBiz = (BillConditionBiz) AppContextUtil.getBean("billConditionBiz");
	private static UsrRoleBiz usrRoleBiz = (UsrRoleBiz) AppContextUtil.getBean("usrRoleBiz");
	private static UsrOrgRangeBiz usrOrgRangeBiz = (UsrOrgRangeBiz) AppContextUtil.getBean("usrOrgRangeBiz");
	
	public static ActivityUtil activityUtil;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ManagementService managementService;

	@PostConstruct
	public void init() {
		activityUtil = this;
	}
	
	/**
	 * @param bean bean.getList()存储主表，bean.getList2()存储明细
	 * @param billTypeId 单据类型Id
	 * @param modelId 单据设置的流程定义Id
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public static String startProcessInstance(CommonBean bean, long billTypeId, String modelId, Param param) throws AppException{
	    if(StringUtils.isNotBlank(modelId)){
	    	String deploymentId = "";
	    	//部署流程定义
			Model modelData = activityUtil.repositoryService.getModel(modelId);
			try {
				ObjectNode modelNode = (ObjectNode) new ObjectMapper()
						.readTree(activityUtil.repositoryService.getModelEditorSource(modelData.getId()));
				byte[] bpmnBytes = null;
				BpmnJsonConverter bjc = new BpmnJsonConverter();
				BpmnModel model = bjc.convertToBpmnModel(modelNode);
				bpmnBytes = new BpmnXMLConverter().convertToXML(model);

				String processName = modelData.getName() + ".bpmn20.xml";
				Deployment deployment = activityUtil.repositoryService.createDeployment().name(modelData.getName())
						.addString(processName, new String(bpmnBytes, "utf-8")).deploy();
				deploymentId = deployment.getId();
			} catch (Exception e) {
			}
			//启动流程
			ProcessDefinitionQuery processDefinitionQuery = activityUtil.repositoryService
					.createProcessDefinitionQuery();
			// 根据某个字段值查询
			processDefinitionQuery.deploymentId(deploymentId);
			//processDefinitionQuery.processDefinitionResourceName(modelData.getName() + ".bpmn20.xml");
			// 根据某个字段排序
			processDefinitionQuery.orderByProcessDefinitionVersion().desc();
			List<ProcessDefinition> list = processDefinitionQuery.list();
			if(list != null && !list.isEmpty()){
				Map<String, Object> variables = new HashMap<String, Object>();
				variables.put("billTypeId", billTypeId);
				variables.put("currentOrgUnitNo", param.getOrgUnitNo());
				List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId, param);
				if (billConditionList != null && !billConditionList.isEmpty()) {
					for(BillCondition billCondition : billConditionList){
						putValue(bean,billCondition,variables);
					}
				}
				if(variables != null && !variables.isEmpty()){
					String processDefinitionId = list.get(0).getId();
					ProcessInstance processInstance = activityUtil.runtimeService
							.startProcessInstanceById(processDefinitionId,variables);
					completeStepTask(processInstance.getProcessInstanceId(), param);
					return processInstance.getProcessInstanceId();
				}else{
					String processDefinitionId = list.get(0).getId();
					ProcessInstance processInstance = activityUtil.runtimeService
							.startProcessInstanceById(processDefinitionId);
					completeStepTask(processInstance.getProcessInstanceId(), param);
					return processInstance.getProcessInstanceId();
				}
			}
	    }else{
	    	throw new AppException("activity.util.ActivityUtil.bill.notSetAudit.error");
	    }
		return null;
	}
	
	private static void putValue(CommonBean bean,BillCondition billCondition,Map<String, Object> variables) throws AppException{
		if(!billCondition.isSubTable()){
			//主表
			if(bean.getList() != null && !bean.getList().isEmpty()){
				String methodName = "get"+(billCondition.getParamCode()).substring(0, 1).toUpperCase() + (billCondition.getParamCode()).substring(1);
				Class clazz = bean.getList().get(0).getClass();
				switch (billCondition.getDataType()) {
				case "boolean":
					try {
						methodName = "is"+(billCondition.getParamCode()).substring(0, 1).toUpperCase() + (billCondition.getParamCode()).substring(1);
						Method method = clazz.getMethod(methodName);
						boolean value = (boolean)method.invoke(bean.getList().get(0));
						variables.put(billCondition.getParamCode(), value);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				case "int":
					try {
						Method method = clazz.getMethod(methodName);
						int value = (int)method.invoke(bean.getList().get(0));
						variables.put(billCondition.getParamCode(), value);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				case "long":
					try {
						Method method = clazz.getMethod(methodName);
						long value = (long)method.invoke(bean.getList().get(0));
						variables.put(billCondition.getParamCode(), value);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				case "BigDecimal":
					try {
						Method method = clazz.getMethod(methodName);
						BigDecimal value = (BigDecimal)method.invoke(bean.getList().get(0));
						//value = value.setScale(billCondition.getDecimalDigit(),RoundingMode.HALF_UP);
						variables.put(billCondition.getParamCode(), value);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				case "String":
					try {
						Method method = clazz.getMethod(methodName);
						String value = (String)method.invoke(bean.getList().get(0));
						if(billCondition.getParamType() == 3 && StringUtils.isBlank(value)){
							//内置跳过用户
							variables.put(billCondition.getParamCode(), "activityStepUser");
						}else{
							variables.put(billCondition.getParamCode(), value);
						}
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				case "Date":
					try {
						Method method = clazz.getMethod(methodName);
						Date value = (Date)method.invoke(bean.getList().get(0));
						String valueString = FormatUtils.fmtDate(value);
						variables.put(billCondition.getParamCode(), valueString);
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						//e.printStackTrace();
						throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
					}
					break;
				default:
					break;
				}
			}
		}else if(billCondition.isSubTable()){
			//明细
			if(bean.getList2() != null && !bean.getList2().isEmpty()){
				String methodName = "get"+(billCondition.getParamCode()).substring(0, 1).toUpperCase() + (billCondition.getParamCode()).substring(1);
				Class clazz = bean.getList2().get(0).getClass();
				if(billCondition.getParamType() == 1){
					List list = new ArrayList<>();
					for(int i=0;i<bean.getList2().size();i++){
						switch (billCondition.getDataType()) {
						case "boolean":
							try {
								methodName = "is"+(billCondition.getParamCode()).substring(0, 1).toUpperCase() + (billCondition.getParamCode()).substring(1);
								Method method = clazz.getMethod(methodName);
								boolean value = (boolean)method.invoke(bean.getList2().get(i));
								list.add(value);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						case "int":
							try {
								Method method = clazz.getMethod(methodName);
								int value = (int)method.invoke(bean.getList2().get(i));
								list.add(value);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						case "long":
							try {
								Method method = clazz.getMethod(methodName);
								long value = (long)method.invoke(bean.getList2().get(i));
								list.add(value);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						case "BigDecimal":
							try {
								Method method = clazz.getMethod(methodName);
								BigDecimal value = (BigDecimal)method.invoke(bean.getList2().get(i));
								//value = value.setScale(billCondition.getDecimalDigit(),RoundingMode.HALF_UP);
								list.add(value);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						case "String":
							try {
								Method method = clazz.getMethod(methodName);
								String value = (String)method.invoke(bean.getList2().get(i));
								list.add(value);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						case "Date":
							try {
								Method method = clazz.getMethod(methodName);
								Date value = (Date)method.invoke(bean.getList2().get(i));
								String valueString = FormatUtils.fmtDate(value);
								list.add(valueString);
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								//e.printStackTrace();
								throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
							}
							break;
						default:
							break;
						}
					}
					variables.put(billCondition.getParamCode()+"List", list);
				}else{
					switch (billCondition.getDataType()) {
					case "boolean":
						try {
							methodName = "is"+(billCondition.getParamCode()).substring(0, 1).toUpperCase() + (billCondition.getParamCode()).substring(1);
							Method method = clazz.getMethod(methodName);
							boolean value = (boolean)method.invoke(bean.getList2().get(0));
							variables.put(billCondition.getParamCode(), value);
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					case "int":
						try {
							Method method = clazz.getMethod(methodName);
							int value = (int)method.invoke(bean.getList2().get(0));
							variables.put(billCondition.getParamCode(), value);
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					case "long":
						try {
							Method method = clazz.getMethod(methodName);
							long value = (long)method.invoke(bean.getList2().get(0));
							variables.put(billCondition.getParamCode(), value);
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					case "BigDecimal":
						try {
							Method method = clazz.getMethod(methodName);
							BigDecimal value = (BigDecimal)method.invoke(bean.getList2().get(0));
							//value = value.setScale(billCondition.getDecimalDigit(),RoundingMode.HALF_UP);
							variables.put(billCondition.getParamCode(), value);
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					case "String":
						try {
							Method method = clazz.getMethod(methodName);
							String value = (String)method.invoke(bean.getList2().get(0));
							if(billCondition.getParamType() == 3 && StringUtils.isBlank(value)){
								//内置跳过用户
								variables.put(billCondition.getParamCode(), "activityStepUser");
							}else{
								variables.put(billCondition.getParamCode(), value);
							}
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					case "Date":
						try {
							Method method = clazz.getMethod(methodName);
							Date value = (Date)method.invoke(bean.getList2().get(0));
							String valueString = FormatUtils.fmtDate(value);
							variables.put(billCondition.getParamCode(), valueString);
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							//e.printStackTrace();
							throw new AppException("activity.util.ActivityUtil.common.getValue.error", e);
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	/**
	 * @param processInstanceId 单据上保存的实例Id
	 * @return
	 */
	public static String findAssigneeByProcessInstanceId(String processInstanceId,Param param){
		//获取审批人
		TaskQuery query = activityUtil.taskService.createTaskQuery();
		Task task = query.processInstanceId(processInstanceId).singleResult();
    	if(task != null){
    		Map<String, String> assignee = new HashMap<String, String>();
    		composeAssignee(assignee,task);
    		String usrList="";
			if(assignee.containsKey("userCodeList")) {
				usrList=assignee.get("userCodeList");
			}
			if(assignee.containsKey("roleIdList")) {
				String roleids = assignee.get("roleIdList");
				String roleList[] = StringUtils.split(roleids, ",");
				for(String roleId:roleList) {
					String orgUnitNo = "";
					if(roleId.indexOf("_")>0){
						orgUnitNo = roleId.split("_")[0];
						roleId = roleId.split("_")[1];
					}
					List<UsrRole2> usrRoleList = usrRoleBiz.selectByRoleAndCtrl(Long.valueOf(roleId),param.getControlUnitNo(),param);
					if(usrRoleList!=null && !usrRoleList.isEmpty()) {
						for(UsrRole2 usrRole:usrRoleList) {
							if(StringUtils.isNotBlank(orgUnitNo)){
								if(!isRoleManager(orgUnitNo,usrRole.getUsrid(),param))
									continue;
							}
							if(StringUtils.isNotBlank(usrList))
								usrList = usrList+",";
							usrList = usrList+usrRole.getUsrCode();
						}
					}
				}
			}
    		return usrList;
    	}
		return "";
	}
	
	private static boolean isRoleManager(String orgUnitNo,long usrId,Param param){
		List<UsrOrgRange2> usrOrgList = usrOrgRangeBiz.selectByUsr(usrId, param);
		if(usrOrgList != null && !usrOrgList.isEmpty()){
			for(UsrOrgRange2 usrOrgRange : usrOrgList){
				if(usrOrgRange.getOrgUnitNo().equals(orgUnitNo)){
					return true;
				}
			}
		}
		return false;
	}
	
	private static Task getCompleteTaskByAssignee(String processInstanceId,String assignee,Param param) throws AppException{
		//先取处理人，再取候选人，再取候选组
		TaskService taskService = activityUtil.taskService;
    	Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(assignee).singleResult();
    	if(task == null){
    		task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateUser(assignee).singleResult();
    		if(task != null){
    			taskService.claim(task.getId(), assignee);
    		}
    	}
    	if(task == null){
			Usr usr = usrBiz.selectByCode(assignee, param);
			if (usr != null) {
				List<Role> groupList = roleBiz.findAllByUsrId(usr.getId(), param);
				if(groupList != null && !groupList.isEmpty()){
					Map<String, Object> variables = getVariables(processInstanceId);
					for(Role role : groupList){
						task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateGroup((String.valueOf(role.getId()))).singleResult();
						if(task != null){
							break;
						}else{
							if(variables != null && !variables.isEmpty()){
								for (String key : variables.keySet()) {
									if (key.endsWith("UnitNo")) {
										String orgUnitNo = String.valueOf(variables.get(key));
										task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateGroup((orgUnitNo+"_"+String.valueOf(role.getId()))).singleResult();
										if(task != null){
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			if(task != null){
    			taskService.claim(task.getId(), assignee);
    		}
    	}
    	if(task == null){
    		throw new AppException("activity.util.ActivityUtil.bill.complete.assignee.error");
    	}
    	return task;
	}
	
	/**
	 * @param processInstanceId 单据上保存的实例Id
	 * @param assignee 审批人
	 * @param opinion agree或者refuse
	 * @param param
	 * @throws AppException
	 */
	public static void completeTaskByAssigneeAndOpinion(String processInstanceId,String assignee,String opinion,Param param) throws AppException{
		Task nowTask = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		if(nowTask!=null) {
			String assigneeList = findAssigneeByProcessInstanceId(processInstanceId, param);
			if(StringUtils.isNotBlank(assigneeList)){
				String[] candidateUserList = assigneeList.split(",");
				if(candidateUserList != null && candidateUserList.length > 1){
					activityUtil.taskService.setAssignee(nowTask.getId(), null);
				}
			}
		}
		//完成任务
		Task task = getCompleteTaskByAssignee(processInstanceId,assignee,param);
		try {
			if(StringUtils.isBlank(opinion)){
	    		activityUtil.taskService.complete(task.getId());
	    	}else{
	    		Map<String, Object> taskVariables = new HashMap<String, Object>();
	    		taskVariables.put("opinion", opinion);
	    		activityUtil.taskService.complete(task.getId(),taskVariables);
	    	}
	    	completeStepTask(processInstanceId, param);
		} catch (Exception e) {
			if(e.getCause() != null && e.getCause().getCause() != null && e.getCause().getCause() instanceof ActivityCheckException){
				throw new AppException(e.getCause().getCause().getMessage());
			}else{
				throw e;
			}
		}
	}
	
	private static void composeAssignee(Map<String, String> assignee,Task task){
		//组装审批人
		StringBuffer assigneeList = new StringBuffer("");
		StringBuffer groupList = new StringBuffer("");
		List identityLinkList = activityUtil.taskService.getIdentityLinksForTask(task.getId());
		if (identityLinkList != null && identityLinkList.size() > 0) {
			for (Iterator iterator = identityLinkList.iterator(); iterator
					.hasNext();) {
				IdentityLink identityLink = (IdentityLink) iterator.next();
				if (identityLink.getUserId() != null) {
					assigneeList.append(identityLink.getUserId()).append(",");
				}
				if (identityLink.getGroupId() != null) {
					groupList.append(identityLink.getGroupId()).append(",");
				}
			}
		}
    	if(StringUtils.isNotBlank(assigneeList.toString())){
    		assignee.put("userCodeList", (assigneeList.toString()).substring(0,(assigneeList.toString()).length()-1));
    	}
    	if(StringUtils.isNotBlank(groupList.toString())){
    		assignee.put("roleIdList", (groupList.toString()).substring(0,(groupList.toString()).length()-1));
    	}
	}
	
	public static void addCandidateUser(String processInstanceId,String usrCode){
		//添加候选人
		TaskQuery query = activityUtil.taskService.createTaskQuery();
		Task task = query.processInstanceId(processInstanceId).singleResult();
    	if(task != null){
    		activityUtil.taskService.addCandidateUser(task.getId(), usrCode); 
    	}
	}
	
	public static void deleteCandidateUser(String processInstanceId,String usrCode){
		//删除候选人
		TaskQuery query = activityUtil.taskService.createTaskQuery();
		Task task = query.processInstanceId(processInstanceId).singleResult();
    	if(task != null){
    		activityUtil.taskService.deleteCandidateUser(task.getId(), usrCode); 
    	}
	}
	
	public static void addCandidateGroup(String processInstanceId,String roleId){
		//添加候选组
		TaskQuery query = activityUtil.taskService.createTaskQuery();
		Task task = query.processInstanceId(processInstanceId).singleResult();
    	if(task != null){
    		activityUtil.taskService.addCandidateGroup(task.getId(), roleId); 
    	}
	}
	
	public static void deleteCandidateGroup(String processInstanceId,String roleId){
		//删除候选组
		TaskQuery query = activityUtil.taskService.createTaskQuery();
		Task task = query.processInstanceId(processInstanceId).singleResult();
    	if(task != null){
    		activityUtil.taskService.deleteCandidateGroup(task.getId(), roleId); 
    	}
	}
	
	/**
	 * @param processInstanceId 单据上保存的流程实例Id
	 * @return
	 */
    public boolean queryProInstanceStateByProInstanceId(String processInstanceId){
    	//判断当前流程是否结束，true代表结束，false代表未结束
        ProcessInstance processInstance = activityUtil.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(processInstance == null){
        	return true;
        }else{
        	return false;
        }
    }
    
    /**
     * 任务撤回
     * @param processInstanceId 单据上保存的流程实例Id
     * @param assignee 撤回任务的处理人
     * @throws AppException
     */
    public static void taskRollBack(String processInstanceId,String assignee) throws AppException{
    	Task task = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		if(task==null) {
			throw new AppException("activity.util.ActivityUtil.bill.taskRollBack.taskNotExist");
		}
		List<HistoricTaskInstance> htiList = activityUtil.historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.orderByTaskCreateTime()
				.asc()
				.list();
		String historyTaskId = null;
		if(htiList != null && !htiList.isEmpty()){
			for(HistoricTaskInstance hti : htiList) {
				if(StringUtils.isNotBlank(assignee) && assignee.equals(hti.getAssignee()) && task.getId()!=hti.getId()) {
					historyTaskId = hti.getId();
					break;
				}
			}
		}
		if(StringUtils.isBlank(historyTaskId)) {
			throw new AppException("activity.util.ActivityUtil.bill.taskRollBack.historyTaskNotExist");
		}
		Map<String, Object> variables;
        //取得当前任务
        HistoricTaskInstance currTask = activityUtil.historyService
                .createHistoricTaskInstanceQuery()
                .taskId(task.getId())
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();
        //取得历史任务
        HistoricTaskInstance historyTask = activityUtil.historyService
                .createHistoricTaskInstanceQuery()
                .taskId(historyTaskId)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();
        //取得流程实例
        ProcessInstance instance = activityUtil.runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(currTask.getProcessInstanceId())
                .singleResult();
        if (instance == null) {
            //流程结束
        	throw new AppException("activity.util.ActivityUtil.bill.taskRollBack.processEnd");
        }
        variables = instance.getProcessVariables();
        //取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (activityUtil.repositoryService.getProcessDefinition(currTask
                        .getProcessDefinitionId()));
        //清除当前活动的出口
        ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                .findActivity(currTask.getTaskDefinitionKey());
        List<PvmTransition> pvmTransitionList = currActivity
                .getOutgoingTransitions();
        List<PvmTransition> pvmTransitionList2 = new ArrayList<>();
        pvmTransitionList2.addAll(pvmTransitionList);
        currActivity.getOutgoingTransitions().clear();

        //建立新出口
        ActivityImpl hisActivity = ((ProcessDefinitionImpl) definition)
                .findActivity(historyTask.getTaskDefinitionKey());
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        newTransition.setDestination(hisActivity);
        //完成任务
        activityUtil.taskService.complete(task.getId(), variables);
        activityUtil.historyService.deleteHistoricTaskInstance(task.getId());
        activityUtil.historyService.deleteHistoricTaskInstance(historyTaskId);
        //恢复方向
    	currActivity.getOutgoingTransitions().remove(newTransition);
    	currActivity.getOutgoingTransitions().addAll(pvmTransitionList2);
    }
    /**
     * 判断是否首个审批
     * @param processInstanceId 单据上的流程实例Id
     * @param assignee 审批人
     * @param param
     * @return
     */
    public static boolean isFirstTask(String processInstanceId,String assignee,Param param){
    	Task task = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    	List<HistoricTaskInstance> htiList = activityUtil.historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.orderByTaskCreateTime()
				.desc()
				.list();
    	removeEndStepTask(htiList);
    	removeBeginStepTask(htiList);
		if(task != null && htiList != null && !htiList.isEmpty()){
			if (task.getTaskDefinitionKey().equals(htiList.get(0).getTaskDefinitionKey())) {
				return true;
			}
		}
    	return false;
    }
    
    /**
     * 流程撤回，返回流程实例Id，需更新到原有单据
     * @param bean 单据的主表和明细，结束流程后不能修改
     * @param billTypeId 单据类型Id
     * @param processInstanceId 单据上原有的流程实例Id
     * @param assignee 撤回任务的处理人
     * @param param
     * @return
     * @throws AppException
     */
    public static String antiAuditProcess(CommonBean bean, long billTypeId, String processInstanceId, String assignee, Param param) throws AppException{
    	ProcessInstance sourceProcessInstance = activityUtil.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        checkUnAntiAudit(assignee,processInstanceId);
    	if(sourceProcessInstance != null){
        	//流程中
        	taskRollBack(processInstanceId,assignee);
        	return processInstanceId;
        }
    	List<HistoricTaskInstance> htiList = activityUtil.historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.includeProcessVariables()
                .includeTaskLocalVariables()
				.orderByTaskCreateTime()
				.asc()
				.list();
    	String processDefinitionId = null;
		if(htiList != null && !htiList.isEmpty()){
			processDefinitionId = htiList.get(0).getProcessDefinitionId();
		}
		//启动流程
		Map<String, Object> variables = new HashMap<String, Object>();
//		List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId, param);
//		if (billConditionList != null && !billConditionList.isEmpty()) {
//			for(BillCondition billCondition : billConditionList){
//				putValue(bean,billCondition,variables);
//			}
//		}
		variables = getVariables(processInstanceId);
		ProcessInstance processInstance = null;
		if(variables != null && !variables.isEmpty()){
			processInstance = activityUtil.runtimeService
					.startProcessInstanceById(processDefinitionId,variables);
		}else{
			processInstance = activityUtil.runtimeService
					.startProcessInstanceById(processDefinitionId);
		}
		removeEndStepTask(htiList);
		removeBeginStepTask(htiList);
		if (htiList != null && !htiList.isEmpty()) {
			Map<String, Object> taskVariables = new HashMap<String, Object>();
			taskVariables.put("opinion", "agree");
			for(int i=0;i<htiList.size()-1;i++){
				if("activityStepUser".equalsIgnoreCase(htiList.get(i).getAssignee())){
					addCandidateUser(processInstance.getProcessInstanceId(),"activityStepUser");
				}
				Task task = getCompleteTaskByAssignee(processInstance.getProcessInstanceId(),htiList.get(i).getAssignee(),param);
				if(task != null && task.getTaskDefinitionKey().equals(htiList.get(i).getTaskDefinitionKey())){
					activityUtil.taskService.complete(task.getId(),taskVariables);
				}
			}
		}
		return processInstance.getProcessInstanceId();
    }
    
	private static boolean removeBeginStepTask(List<HistoricTaskInstance> htiList) {
		if (htiList != null && !htiList.isEmpty()) {
    		if("activityStepUser".equals(htiList.get(0).getAssignee())){
    			htiList.remove(0);
    			return removeBeginStepTask(htiList);
    		}else{
    			return true;
    		}
    	}
    	return true;
	}

	private static void checkUnAntiAudit(String assignee, String processInstanceId) throws AppException {
		List<HistoricTaskInstance> htiList = activityUtil.historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.includeProcessVariables()
                .includeTaskLocalVariables()
				.orderByHistoricTaskInstanceEndTime()
				.asc()
				.list();
		removeEndStepTask(htiList);
		if (htiList != null && !htiList.isEmpty()) {
//			List<HistoricTaskInstance> sortByMethod = (List<HistoricTaskInstance>) ListSortUtil.sortByMethod(htiList, "getEndTime", ListSortUtil.ASC);
			if (!StringUtils.equals(assignee, htiList.get(htiList.size()-1).getAssignee())) {
				throw new AppException("activity.util.ActivityUtil.bill.antiAuditProcess.error.notUsr");
			}
		}
	}

	public static Map<String, Object> getVariables(String processInstanceId){
    	List<HistoricVariableInstance> list = activityUtil.historyService
				.createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询
				.processInstanceId(processInstanceId)
				.list();
		
		if(list != null && list.size()>0){
			Map<String, Object> variables = new HashMap<String, Object>();
			for(HistoricVariableInstance hvi : list){
				variables.put(hvi.getVariableName(), hvi.getValue());
			}
			return variables;
		}
		return null;
    }
    
    private static boolean completeStepTask(String processInstanceId,Param param){
    	ProcessInstance processInstance = activityUtil.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(processInstance == null){
        	return true;
        }
    	String assignee = findAssigneeByProcessInstanceId(processInstanceId, param);
    	if(StringUtils.isBlank(assignee) || "activityStepUser".equals(assignee)){
    		if(StringUtils.isBlank(assignee)){
    			addCandidateUser(processInstanceId,"activityStepUser");
    		}
    		Task task = getCompleteTaskByAssignee(processInstanceId,"activityStepUser",param);
    		Map<String, Object> taskVariables = new HashMap<String, Object>();
    		taskVariables.put("opinion", "agree");
    		activityUtil.taskService.complete(task.getId(),taskVariables);
    		return completeStepTask(processInstanceId,param);
    	}else{
    		return true;
    	}
    }
    
    private static boolean removeEndStepTask(List<HistoricTaskInstance> htiList){
    	if (htiList != null && !htiList.isEmpty()) {
    		if("activityStepUser".equals(htiList.get(htiList.size()-1).getAssignee())){
    			htiList.remove(htiList.size()-1);
    			return removeEndStepTask(htiList);
    		}else{
    			return true;
    		}
    	}
    	return true;
    }
    
    /**
     * 更新当前流程变量
     * @param bean 最新的单据bean
     * @param billTypeId 单据类型Id
     * @param processInstanceId 单据上保存的实例Id
     * @param param
     */
    public static void updateProcessVariable(CommonBean bean, long billTypeId,String processInstanceId,Param param){
    	Task nowTask = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		if(nowTask!=null) {
			Map<String, Object> variables = new HashMap<String, Object>();
			List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId, param);
			if (billConditionList != null && !billConditionList.isEmpty()) {
				for(BillCondition billCondition : billConditionList){
					putValue(bean,billCondition,variables);
				}
			}
			activityUtil.taskService.setVariables(nowTask.getId(), variables);
		}
    }
    
    /**
     * 获取工作流任务是否执行完成
     * @param processInstanceId 
     */
    public static boolean getJobExecuteStatus(String processInstanceId){
    	List<Job> jobList = activityUtil.managementService.createJobQuery().processInstanceId(processInstanceId).list();
    	if(jobList != null && !jobList.isEmpty()){
    		for(Job job : jobList){
    			if(job instanceof TimerEntity){
    				TimerEntity timerEntity = (TimerEntity)(job);
    				String repeat = timerEntity.getRepeat();
    				if(StringUtils.isNotBlank(repeat) && repeat.startsWith("R1")){
    					return true;
    				}
    			}
    		}
    		return false;
    	}
    	return true;
    }
    
    /**
     * 移除工作流任务
     * @param processInstanceId 
     */
    public static boolean deleteJob(String processInstanceId){
    	try {
    		List<Job> jobList = activityUtil.managementService.createJobQuery().processInstanceId(processInstanceId).list();
        	if(jobList != null && !jobList.isEmpty()){
        		for(Job job : jobList){
        			activityUtil.managementService.deleteJob(job.getId());
            	}
        		return true;
        	}
        	return false;
		} catch (Exception e) {
			throw new AppException("activity.util.ActivityUtil.bill.deleteJob.error");
		}
    }
    
    public static ActivityImpl getActivity(String processDefinitionId,String activityId){
		ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (activityUtil.repositoryService.getProcessDefinition(processDefinitionId));
		if(definition != null){
			ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(activityId);
			if(currActivity != null){
				return currActivity;
			}
		}
		return null;
    }
    
    public static String checkActivity(BaseModel baseModel,String processInstanceId,String otherMsg,HashMap<String,Object> myTaskActivityMap,Param param) throws AppException{
    	ActivityImpl currActivity = null;
    	if(myTaskActivityMap.containsKey(processInstanceId)){
    		currActivity = (ActivityImpl)myTaskActivityMap.get(processInstanceId);
    	}
    	if(currActivity == null){
    		Task task = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    		if(task==null) {
    			return "";
    		}
    		ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (activityUtil.repositoryService.getProcessDefinition(task
                    .getProcessDefinitionId()));
    		currActivity = ((ProcessDefinitionImpl) definition)
    				.findActivity(task.getTaskDefinitionKey());
    		myTaskActivityMap.put(processInstanceId, currActivity);
    	}
		List<PvmTransition> pvmTransitionList = currActivity
                .getOutgoingTransitions();
		PvmTransition checkPvmTransition = null;
		if(pvmTransitionList != null && !pvmTransitionList.isEmpty() && pvmTransitionList.size()>=2){
			for(PvmTransition pvmTransition : pvmTransitionList){
				String type = String.valueOf(pvmTransition.getDestination().getProperty("type"));
				String name = String.valueOf(pvmTransition.getDestination().getProperty("name"));
				if(StringUtils.equalsIgnoreCase("serviceTask", type) && StringUtils.isNotBlank(name)
						&& name.startsWith("checkNode")
						&& (pvmTransition.getDestination().getOutgoingTransitions() == null 
						|| pvmTransition.getDestination().getOutgoingTransitions().isEmpty())){
					checkPvmTransition = pvmTransition;
					break;
				}
			}
			if(checkPvmTransition != null){
				String conditionStr = String.valueOf(checkPvmTransition.getProperty(BpmnParse.PROPERTYNAME_CONDITION_TEXT));
				if(StringUtils.isNotBlank(conditionStr)){
					conditionStr = conditionStr.replace("${activityCondition.value", "");
					if(conditionStr.startsWith("List")){
						conditionStr = conditionStr.replaceAll("List", "");
					}else if(conditionStr.startsWith("ListAll")){
						conditionStr = conditionStr.replaceAll("ListAll", "");
						conditionStr = conditionStr.replaceAll("List", "");
					}
					String billConditionName = conditionStr.substring(conditionStr.indexOf("(")+1,conditionStr.indexOf(","));
					String judgeSymbol = conditionStr.substring(0, conditionStr.indexOf("("));
					String judgeValue = conditionStr.substring(conditionStr.indexOf(",")+1, conditionStr.indexOf(")"));
					if(StringUtils.contains("qty,price,taxprice,amt,taxamt", billConditionName)
							&& StringUtils.contains("Eq,Ne,Ge,Gt,Le,Lt", judgeSymbol)){
						boolean checkFlag = false;
						String methodName = "get"+(billConditionName).substring(0, 1).toUpperCase() + (billConditionName).substring(1);
						Class clazz = baseModel.getClass();
						try {
							Method method = clazz.getMethod(methodName);
							BigDecimal value = (BigDecimal)method.invoke(baseModel);
							if(value != null){
								if(StringUtils.endsWithIgnoreCase("eq", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) == 0){
										checkFlag = true;
									}
								}else if(StringUtils.endsWithIgnoreCase("ne", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) != 0){
										checkFlag = true;
									}
								}else if(StringUtils.endsWithIgnoreCase("ge", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) >= 0){
										checkFlag = true;
									}
								}else if(StringUtils.endsWithIgnoreCase("gt", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) > 0){
										checkFlag = true;
									}
								}else if(StringUtils.endsWithIgnoreCase("le", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) <= 0){
										checkFlag = true;
									}
								}else if(StringUtils.endsWithIgnoreCase("lt", judgeSymbol)){
									if(value.compareTo(new BigDecimal(judgeValue)) < 0){
										checkFlag = true;
									}
								}
							}
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							return "";
						}
						if(checkFlag){
							StringBuffer errMsg = new StringBuffer("");
							errMsg.append(otherMsg);
							errMsg.append(Message.getMessage(param.getLang(), "field."+ClassUtils.getFinalModelSimpleName(baseModel.getClass())+"."+billConditionName));
							if(StringUtils.isNotBlank(judgeSymbol) && StringUtils.isNotBlank(judgeValue)){
								errMsg.append(getJudgeStr(judgeSymbol, judgeValue));
							}
							return errMsg.toString();
						}
					}
				}
			}
		}
		return "";
    }
    
    private static String getJudgeStr(String judgeSymbol, String judgeValue){
		String judgeStr = "";
		switch (StringUtils.lowerCase(judgeSymbol)) {
		case "eq":
			judgeStr = "等于"+judgeValue;
			break;
		case "ne":
			judgeStr = "不等于"+judgeValue;
			break;
		case "ge":
			judgeStr = "大于等于"+judgeValue;
			break;
		case "gt":
			judgeStr = "大于"+judgeValue;
			break;
		case "le":
			judgeStr = "小于等于"+judgeValue;
			break;
		case "lt":
			judgeStr = "小于"+judgeValue;
			break;
		case "in":
			judgeStr = "包含"+judgeValue;
			break;
		case "notin":
			judgeStr = "不包含"+judgeValue;
			break;
		case "like":
			judgeStr = "相似于"+judgeValue;
			break;
		case "between":
			judgeStr = "大于等于"+judgeValue.split(",")[0]+"且小于等于"+judgeValue.split(",")[1];
			break;
		case "between2":
			judgeStr = "大于"+judgeValue.split(",")[0]+"且小于"+judgeValue.split(",")[1];
			break;
		case "between3":
			judgeStr = "大于等于"+judgeValue.split(",")[0]+"且小于"+judgeValue.split(",")[1];
			break;
		case "between4":
			judgeStr = "大于"+judgeValue.split(",")[0]+"且小于等于"+judgeValue.split(",")[1];
			break;
		default:
			break;
		}
		return judgeStr;
	}

	public static boolean isFirstTask(String processInstanceId, String oldProcessInstanceId, String usrCode,Param param) {
		Task task = activityUtil.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    	List<HistoricTaskInstance> htiList = activityUtil.historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(oldProcessInstanceId)
				.orderByTaskCreateTime()
				.asc()
				.list();
    	removeEndStepTask(htiList);
    	removeBeginStepTask(htiList);
		if(task != null && htiList != null && !htiList.isEmpty()){
			if (task.getTaskDefinitionKey().equals(htiList.get(0).getTaskDefinitionKey())) {
				return true;
			}
		}
    	return false;
	}
}
