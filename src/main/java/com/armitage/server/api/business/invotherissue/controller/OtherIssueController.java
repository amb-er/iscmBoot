package com.armitage.server.api.business.invotherissue.controller;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.invotherissue.params.InvOtherIssueAuditParams;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueAuditParamsApi;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueParams;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueParamsApi;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueUnAuditParams;
import com.armitage.server.api.business.invotherissue.params.InvOtherIssueUnAuditParamsApi;
import com.armitage.server.api.business.invotherissue.result.InvOtherIssueAuditResultApi;
import com.armitage.server.api.business.invotherissue.result.InvOtherIssueDetailResult;
import com.armitage.server.api.business.invotherissue.result.InvOtherIssueResult;
import com.armitage.server.api.business.invotherissue.result.InvOtherIssueResultApi;
import com.armitage.server.api.business.invotherissue.result.InvOtherIssueUnAuditResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/invOtherIssue")
@Api(tags="其他出库业务接口")
public class OtherIssueController {
	private static Log log = LogFactory.getLog(OtherIssueController.class);
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz = (ScmInvOtherIssueBillBiz) AppContextUtil.getBean("scmInvOtherIssueBillBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvOtherIssue", method=RequestMethod.POST)
    @ApiOperation(value="获取其他出库单详情", consumes="application/json",position=1)
    public InvOtherIssueResultApi queryInvOtherIssue(@RequestBody InvOtherIssueParamsApi params) {
		InvOtherIssueResultApi result = new InvOtherIssueResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvOtherIssueParams invOtherIssueParams = params.getParams();
	        if(invOtherIssueParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvOtherIssueBill2 scmInvOtherIssue= new ScmInvOtherIssueBill2();
	        scmInvOtherIssue.setOtNo(invOtherIssueParams.getOtNo());
	        ScmInvOtherIssueBill2 rtn = scmInvOtherIssueBillBiz.queryInvOtherIssue(scmInvOtherIssue, ParamHelper.createParam(integratedRequest,"queryInvOtherIssue"));
	        if(rtn!=null){
	        	InvOtherIssueResult invOtherIssueResult = new InvOtherIssueResult();
	        	BeanUtils.copyProperties(rtn, invOtherIssueResult);
	        	List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueEntryList = rtn.getScmInvOtherIssueBillEntryList();
	        	if(scmInvOtherIssueEntryList!=null && !scmInvOtherIssueEntryList.isEmpty()){
	        		List<InvOtherIssueDetailResult> detailList = new ArrayList<InvOtherIssueDetailResult>();
	        		for(ScmInvOtherIssueBillEntry2 scmInvOtherIssueEntry:scmInvOtherIssueEntryList){
	        			InvOtherIssueDetailResult invOtherIssueDetailResult = new InvOtherIssueDetailResult();
	        			BeanUtils.copyProperties(scmInvOtherIssueEntry, invOtherIssueDetailResult);
	        			invOtherIssueDetailResult.setUnit(scmInvOtherIssueEntry.getUnitName());
	        			invOtherIssueDetailResult.setPieUnit(scmInvOtherIssueEntry.getPieUnitName());
	        			detailList.add(invOtherIssueDetailResult);
	        		}
	        		invOtherIssueResult.setDetailList(detailList);
	        	}
	        	result.setResult(invOtherIssueResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取其他出库单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvOtherIssue", method=RequestMethod.POST)
    @ApiOperation(value="审批其他出库单", consumes="application/json",position=2)
    public InvOtherIssueAuditResultApi doAuditInvOtherIssue(@RequestBody InvOtherIssueAuditParamsApi params) {
    	InvOtherIssueAuditResultApi result = new InvOtherIssueAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvOtherIssueAuditParams invOtherIssueAuditParams = params.getParams();
	        if(invOtherIssueAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invOtherIssueAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invOtherIssueAuditParams.getOtNo());
	        scmInvOtherIssueBillBiz.doAuditInvOtherIssue(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvOtherIssue"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("其他出库单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvOtherIssue", method=RequestMethod.POST)
    @ApiOperation(value="取消审批其他出库单", consumes="application/json",position=3)
    public InvOtherIssueUnAuditResultApi doUnAuditInvOtherIssue(@RequestBody InvOtherIssueUnAuditParamsApi params) {
    	InvOtherIssueUnAuditResultApi result = new InvOtherIssueUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvOtherIssueUnAuditParams invOtherIssueUnAuditParams = params.getParams();
	        if(invOtherIssueUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvOtherIssueBill2 scmInvOtherIssueBill = new ScmInvOtherIssueBill2();
	        scmInvOtherIssueBill.setOtNo(invOtherIssueUnAuditParams.getOtNo());
	        scmInvOtherIssueBillBiz.doUnAuditInvOtherIssue(scmInvOtherIssueBill, ParamHelper.createParam(integratedRequest,"doUnAuditInvOtherIssue"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("其他出库单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
