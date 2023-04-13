package com.armitage.server.iscm.activityTask.audit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;

@Component
public class ScmPurRequireUploadOATask implements JavaDelegate{
	
private static Log log = LogFactory.getLog(ScmPurRequireUploadOATask.class);
	
	private static UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private static EmployeeBiz employeeBiz = (EmployeeBiz) AppContextUtil.getBean("employeeBiz");
	private static ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		
	}
	
	public void executeUploadOA(DelegateExecution delegateExecution) {
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e1) {
			log.error(e1);
		}
		Map<String, Object> map = delegateExecution.getVariables();
		if(map == null || map.isEmpty()){
			log.info("未设置流程变量！");
			return;
		}
		String processInstanceId = delegateExecution.getProcessInstanceId();
		if(StringUtils.isBlank(processInstanceId)){
			log.info("流程不存在！");
			return;
		}
		String prNo = "";
		if (map.containsKey("prNo")) {
			prNo = String.valueOf(map.get("prNo"));
		} else {
			log.info("请购单上传OA失败：单据编号为空！");
			return;
		}
		String orgUnitNo = "";
		if (map.containsKey("orgUnitNo")) {
			orgUnitNo = String.valueOf(map.get("orgUnitNo"));
		} else {
			log.info("请购单上传OA失败：申请组织为空！");
			return;
		}
		IntegratedRequest integratedRequest = new IntegratedRequest();
		integratedRequest.setOrgUnitNo(orgUnitNo);
		Param param = ParamHelper.createParam(integratedRequest, "");
		String usrCode = "";
		String usrListStr = ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId, param);
		if(StringUtils.isBlank(usrListStr)){
			log.info("当前任务未设置审批人！");
			return;
		}
		String[] usrList = usrListStr.split(",");
		for(String str : usrList){
			Usr usr = usrBiz.selectByCode(str, param);
			if(usr != null){
				Employee employee= employeeBiz.selectDirect(usr.getEmpid(), param);
				if(employee != null && StringUtils.isNotBlank(employee.getEmpNo())){
					usrCode = usr.getCode();
					break;
				}
			}
		}
		if(StringUtils.isBlank(usrCode)){
			log.info("未找到当前审批人或当前审批人未设置工号！");
			return;
		}
		param.setUsrCode(usrCode);
		Page page=new Page();
		page.setModelClass(ScmPurRequire2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(
				ScmPurRequire2.FN_PRNO,
				new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
						prNo));
		
		List<ScmPurRequire2> scmPurRequireList =scmPurRequireBiz.findPage(page, param);
		if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
			ScmPurRequire2 require=scmPurRequireList.get(0);
			if(require == null){
				log.info("未找到当前请购单据！");
				return;
			}
			try {
				if(StringUtils.contains("1,2,5,6", require.getOutAuditType())){
					IndentityUtil.identity(require.getId(), usrCode, delegateExecution.getProcessInstanceId(), param);
				}
				ScmPurRequire2 tempRequire = scmPurRequireBiz.select(require.getId(), param);
				if(tempRequire != null && StringUtils.isNotBlank(tempRequire.getOutAuditType()) && StringUtils.contains("3,7", tempRequire.getOutAuditType())){
					if(StringUtils.isNotBlank(processInstanceId)){
						DeleteJobThread deleteJobThread = new DeleteJobThread();
						deleteJobThread.setProcessInstanceId(processInstanceId);
						deleteJobThread.setName("Execute_Sync_"+processInstanceId);
						deleteJobThread.start();
					}
				}
			} catch (Exception e) {
				log.error(e);
			}finally {
				boolean exeuteFinish = ActivityUtil.getJobExecuteStatus(processInstanceId);
				String outAuditType = "";
				ScmPurRequire2 require2 = scmPurRequireBiz.select(require.getId(), param);
				if(require2 != null && StringUtils.isNotBlank(require2.getOutAuditType()) && !StringUtils.contains("3,7", require2.getOutAuditType())){
					if (StringUtils.isBlank(require2.getOtherAuditNo())) {
						if(!exeuteFinish){
							outAuditType = "2";
							require2.setOutAuditType(outAuditType);
							scmPurRequireBiz.updateOutAudit(require2, param);
						}else if(exeuteFinish){
							outAuditType = "4";
							require2.setOutAuditType(outAuditType);
							//ActivityUtil.deleteJob(processInstanceId);
							scmPurRequireBiz.updateOutAudit(require2, param);
						}
					} else {
						if(!exeuteFinish){
							outAuditType = "6";
							require2.setOutAuditType(outAuditType);
							scmPurRequireBiz.updateOutAudit(require2, param);
						}else if(exeuteFinish){
							outAuditType = "8";
							require2.setOutAuditType(outAuditType);
							//ActivityUtil.deleteJob(processInstanceId);
							scmPurRequireBiz.updateOutAudit(require2, param);
						}
					}
				}
			}
		}
	}

}
