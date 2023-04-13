package com.armitage.server.iscm.webservice.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.JSONUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.webservice.ISCMBusinessService;
import com.armitage.server.iscm.webservice.model.UpdateRequireStatusParams;
import com.armitage.server.iscm.webservice.model.UpdateRequireStatusResult;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ISCMBusinessServiceImpl implements ISCMBusinessService {
	private Gson gson = JSONUtils.newGson();
	private static Log log = LogFactory.getLog(ISCMBusinessServiceImpl.class);
	
	private ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
	private EmployeeBiz employeeBiz = (EmployeeBiz) AppContextUtil.getBean("employeeBiz");
	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	
	@Override
	public String doUpdateRequireStatus(String jIntegratedRequest, String jParams) throws AppException {
		
		String RESULT_REJECT = "11";	//驳回--供应链新增状态
		String RESULT_END = "30";		//结束--供应链审核中/通过状态
		String RESULT_ABANDON = "00";	//废弃--供应链未通过状态
		
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		UpdateRequireStatusResult result = new UpdateRequireStatusResult();
		
		String resultStr = "";
		if (StringUtils.isBlank(jIntegratedRequest) || StringUtils.isBlank(jParams)) {
			result.setErrCode("-1");
			result.setErrText("请求参数不符合规范");
			resultStr = gson.toJson(result);
			return resultStr;
		}
		
		IntegratedRequest integratedRequest = gson.fromJson(jIntegratedRequest,
				IntegratedRequest.class);
		
		UpdateRequireStatusParams updateRequireStatusParams = gson.fromJson(jParams,UpdateRequireStatusParams.class);
		
		if(updateRequireStatusParams == null){
			result.setErrCode("-1");
			result.setErrText("params参数格式不对");
			resultStr = gson.toJson(result);
			return resultStr;
		}
		
		if (StringUtils.isBlank(updateRequireStatusParams.getPurRequireId())) {
			result.setErrCode("-1");
			result.setErrText("更新请购单状态接口请购单号不允许为空");
			resultStr = gson.toJson(result);
			return resultStr;
		}
		
		String resultParm = updateRequireStatusParams.getResult();
		if (StringUtils.isBlank(resultParm)) {
			result.setErrCode("-1");
			result.setErrText("更新请购单状态接口审批结果不允许为空");
			resultStr = gson.toJson(result);
			return resultStr;
		} else {
			if (!(RESULT_REJECT.equals(resultParm) || RESULT_END.equals(resultParm) || RESULT_ABANDON.equals(resultParm))) {
				result.setErrCode("-1");
				result.setErrText("更新请购单状态接口审批结果无效:" + resultParm);
				resultStr = gson.toJson(result);
				return resultStr;
			}
		}
		
		try {
			
			SecurityUtils.verify(integratedRequest);
			
			CommonAuditParams commonAuditParams = new CommonAuditParams();
		    commonAuditParams.setBillNo(updateRequireStatusParams.getPurRequireId());
		    String opinion = "";
		    if(RESULT_REJECT.equals(resultParm)){
		    	opinion = "R";
		    }else if(RESULT_END.equals(resultParm)){
		    	opinion = "Y";
		    }
		    else if(RESULT_ABANDON.equals(resultParm)){
		    	opinion = "N";
		    }
		    commonAuditParams.setOpinion(opinion);
		    
		    Param param= ParamHelper.createParam(integratedRequest,"scmPurRequire");
		    
		    /*String empNo = integratedRequest.getUserCode();
		    Employee employee = employeeBiz.selectByKey(empNo, param);
		    if(employee != null){
		    	Page page = new Page();
				page.setModelClass(Usr2.class);
				page.setPagePos(1);
				page.setShowCount(Integer.MAX_VALUE);
				if(employee.getId() > 0){
					page.setSqlCondition("Usr.empid = "+employee.getId());
					List<Usr2> usrList = usrBiz.findPage(page, param);
					if(usrList != null && !usrList.isEmpty()){
						param.setUsrCode(usrList.get(0).getCode());
						param.setUsrName(usrList.get(0).getName());
					}
				}
		    }*/
		    
		    HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurRequire2.FN_OTHERAUDITNO,
					new QueryParam(ScmPurRequire2.FN_OTHERAUDITNO, QueryParam.QUERY_EQ,
							commonAuditParams.getBillNo()));
			map.put(ScmPurRequire2.FN_CONTROLUNITNO, new QueryParam(ScmPurRequire2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			
			List<ScmPurRequire2> scmPurRequireList =scmPurRequireBiz.findAll(map, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				ScmPurRequire2 require=scmPurRequireList.get(0);
				commonAuditParams.setBillNo(require.getPrNo());
				if(StringUtils.isBlank(require.getOutAuditType()) || !StringUtils.contains("3,7", require.getOutAuditType())){
					result.setErrCode("-1");
					result.setErrText("当前单据需OA流程启动或重启成功才可审批");
					resultStr = gson.toJson(result);
					return resultStr;
				}else{
					require.setOutAuditType(null);
					scmPurRequireBiz.updateOutAudit(require, param);
					param.setUsrCode(require.getCreator());
				}
			}
			scmPurRequireBiz.doAuditPurRequire(commonAuditParams, param);
			
			result.setErrCode("0");
			result.setErrText("");

		} catch (Exception e) {
			Message.inMessage(result, integratedRequest.getLang(), e,
					"doUpdateRequireStatus");
		}
		String jResult = gson.toJson(result);
		return jResult;
	}
}
