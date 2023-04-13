package com.armitage.server.activity.util;

import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.armitage.server.activity.common.model.BillCondition;
import com.armitage.server.activity.common.service.BillConditionBiz;
import com.armitage.server.activity.model.ActivityCheckException;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.service.BillTypeBiz;

@Component
public class ActivityCheckUtil implements JavaDelegate{
	
private static Log log = LogFactory.getLog(ActivityCheckUtil.class);
	
private static BillConditionBiz billConditionBiz = (BillConditionBiz) AppContextUtil.getBean("billConditionBiz");
private static BillTypeBiz billTypeBiz = (BillTypeBiz) AppContextUtil.getBean("billTypeBiz");

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		
	}
	
	public void executeCheck(DelegateExecution delegateExecution) throws Exception{
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
		long billTypeId = 0;
		if (map.containsKey("billTypeId")) {
			billTypeId = Long.parseLong(String.valueOf(map.get("billTypeId")));
		} else {
			log.info("校验失败：单据类型为空！");
			return;
		}
		String currentOrgUnitNo = "";
		if (map.containsKey("currentOrgUnitNo")) {
			currentOrgUnitNo = String.valueOf(map.get("currentOrgUnitNo"));
		} else {
			log.info("校验失败：组织为空！");
			return;
		}
		String opinion = "";
		if (map.containsKey("opinion")) {
			opinion = String.valueOf(map.get("opinion"));
		}
		if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)){
			return;
		}
		IntegratedRequest integratedRequest = new IntegratedRequest();
		integratedRequest.setOrgUnitNo(currentOrgUnitNo);
		Param param = ParamHelper.createParam(integratedRequest, "");
		
		ActivityImpl currActivity = ActivityUtil.getActivity(delegateExecution.getProcessDefinitionId(), delegateExecution.getCurrentActivityId());
		if(currActivity != null){
			List<PvmTransition> pvmTransitionList = currActivity.getIncomingTransitions();
			if(pvmTransitionList == null || pvmTransitionList.isEmpty()){
				return;
			}
			Object condition = pvmTransitionList.get(0).getProperty(BpmnParse.PROPERTYNAME_CONDITION_TEXT);
			if(condition == null){
				log.info("条件为空！");
				return;
			}
			String conditionStr = String.valueOf(condition);
			//String conditionStr = "${activityCondition.valueListEq(priceList,0)}";
			if(StringUtils.isNotBlank(conditionStr)){
				//错误消息暂支持中文
				BillType2 billType = billTypeBiz.selectDirect(billTypeId, param);
				StringBuffer errMsg = new StringBuffer("");
				if(billType != null){
					errMsg.append(billType.getBillName());
				}
				conditionStr = conditionStr.replace("${activityCondition.value", "");
				if(conditionStr.startsWith("List")){
					errMsg.append("明细有");
					conditionStr = conditionStr.replaceAll("List", "");
				}else if(conditionStr.startsWith("ListAll")){
					errMsg.append("全部明细");
					conditionStr = conditionStr.replaceAll("ListAll", "");
					conditionStr = conditionStr.replaceAll("List", "");
				}
				String billConditionName = conditionStr.substring(conditionStr.indexOf("(")+1,conditionStr.indexOf(","));
				if(StringUtils.isNotBlank(billConditionName)){
					List<BillCondition> billConditionList = billConditionBiz.selectCondition(billTypeId, param);
					if (billConditionList != null && !billConditionList.isEmpty()) {
						for(BillCondition billCondition : billConditionList){
							if(StringUtils.equalsIgnoreCase(billCondition.getParamCode(), billConditionName)){
								errMsg.append(billCondition.getParamName());
							}
						}
					}
				}
				String judgeSymbol = conditionStr.substring(0, conditionStr.indexOf("("));
				String judgeValue = conditionStr.substring(conditionStr.indexOf(",")+1, conditionStr.indexOf(")"));
				if(StringUtils.isNotBlank(judgeSymbol) && StringUtils.isNotBlank(judgeValue)){
					errMsg.append(getJudgeStr(judgeSymbol, judgeValue));
				}
				throw new ActivityCheckException(errMsg.toString());
			}
		}
	}

	private String getJudgeStr(String judgeSymbol, String judgeValue){
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
}
