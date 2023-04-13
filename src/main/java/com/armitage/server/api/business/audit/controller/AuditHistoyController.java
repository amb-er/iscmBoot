package com.armitage.server.api.business.audit.controller;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.audit.params.AuditStatusParams;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.armitage.server.api.business.audit.params.AuditStatusParamsApi;
import com.armitage.server.api.business.audit.result.AuditStatusResult;
import com.armitage.server.api.business.audit.result.AuditStatusResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.CommonEventHistory2;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/audit")
@Api(tags="审批历史接口")
public class AuditHistoyController {
	private static Log log = LogFactory.getLog(AuditHistoyController.class);
	private CommonEventHistoryBiz commonEventHistoryBiz = (CommonEventHistoryBiz) AppContextUtil.getBean("commonEventHistoryBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/loadAuditStatus", method=RequestMethod.POST)
    @ApiOperation(value="审批状态查询", consumes="application/json",position=3)
    public AuditStatusResultApi loadAuditStatus(@RequestBody AuditStatusParamsApi params) {
		AuditStatusResultApi result = new AuditStatusResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	AuditStatusParams auditStatusParams = params.getParams();
        	if(auditStatusParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<CommonEventHistory2> commonEventHistoryList = commonEventHistoryBiz.loadAuditStatus(auditStatusParams.getBillTypeCode(), auditStatusParams.getBillNo(), ParamHelper.createParam(integratedRequest,"loadAuditStatus"));
    	    	if(commonEventHistoryList!=null && !commonEventHistoryList.isEmpty()) {
    	    		List<AuditStatusResult> resultList = new ArrayList();
    	    		for(CommonEventHistory2 commonEventHistory:commonEventHistoryList) {
    	    			AuditStatusResult auditStatusResult = new AuditStatusResult();
    	    			BeanUtils.copyProperties(commonEventHistory,auditStatusResult);
    	    			resultList.add(auditStatusResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("审批状态查询失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }

}
