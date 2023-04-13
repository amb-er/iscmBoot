package com.armitage.server.api.business.purreturns.controller;

import com.armitage.server.api.business.purreturns.params.PurReturnsParams;
import com.armitage.server.api.business.purreturns.params.PurReturnsUnAuditParams;
import com.armitage.server.api.business.purreturns.result.PurReturnsDetailResult;
import com.armitage.server.api.business.purreturns.result.PurReturnsUnAuditResultApi;
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

import com.armitage.server.api.business.purreturns.params.PurReturnsAuditParams;
import com.armitage.server.api.business.purreturns.params.PurReturnsAuditParamsApi;
import com.armitage.server.api.business.purreturns.params.PurReturnsParamsApi;
import com.armitage.server.api.business.purreturns.params.PurReturnsUnAuditParamsApi;
import com.armitage.server.api.business.purreturns.result.PurReturnsAuditResultApi;
import com.armitage.server.api.business.purreturns.result.PurReturnsResult;
import com.armitage.server.api.business.purreturns.result.PurReturnsResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsBiz;

@Controller
@RequestMapping("/api/purReturns")
@Api(tags="退货申请业务接口")
public class ReturnsController {
	private static Log log = LogFactory.getLog(ReturnsController.class);
	private ScmPurReturnsBiz scmPurReturnsBiz = (ScmPurReturnsBiz) AppContextUtil.getBean("scmPurReturnsBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReturns", method=RequestMethod.POST)
    @ApiOperation(value="获取退货申请单详情", consumes="application/json",position=1)
    public PurReturnsResultApi queryPurOrder(@RequestBody PurReturnsParamsApi params) {
		PurReturnsResultApi result = new PurReturnsResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReturnsParams purReturnsParams = params.getParams();
	        if(purReturnsParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReturns2 scmPurReturns= new ScmPurReturns2();
	        scmPurReturns.setRtNo(purReturnsParams.getRtNo());
	        ScmPurReturns2 rtn = scmPurReturnsBiz.queryPurReturns(scmPurReturns, ParamHelper.createParam(integratedRequest,"queryPurReturns"));
	        if(rtn!=null){
	        	PurReturnsResult purReturnsResult = new PurReturnsResult();
	        	BeanUtils.copyProperties(rtn, purReturnsResult);
	        	List<ScmPurReturnsEntry2> scmPurReturnsEntryList = rtn.getScmPurReturnsEntryList();
	        	if(scmPurReturnsEntryList!=null && !scmPurReturnsEntryList.isEmpty()){
	        		List< PurReturnsDetailResult > detailList = new ArrayList<PurReturnsDetailResult>();
	        		for(ScmPurReturnsEntry2 scmPurReturnsEntry:scmPurReturnsEntryList){
	        			PurReturnsDetailResult purReturnsDetailResult = new PurReturnsDetailResult();
	        			BeanUtils.copyProperties(scmPurReturnsEntry, purReturnsDetailResult);
	        			purReturnsDetailResult.setPurUnit(scmPurReturnsEntry.getPurUnitName());
	        			detailList.add(purReturnsDetailResult);
	        		}
	        		purReturnsResult.setDetailList(detailList);
	        	}
	        	result.setResult(purReturnsResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取退货申请单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurReturns", method=RequestMethod.POST)
    @ApiOperation(value="审批退货申请单", consumes="application/json",position=2)
    public PurReturnsAuditResultApi doAuditPurReturns(@RequestBody PurReturnsAuditParamsApi params) {
    	PurReturnsAuditResultApi result = new PurReturnsAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReturnsAuditParams purReturnsAuditParams = params.getParams();
	        if(purReturnsAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purReturnsAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purReturnsAuditParams.getRtNo());
	        scmPurReturnsBiz.doAuditPurReturns(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditPurReturns"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("退货申请单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurReturns", method=RequestMethod.POST)
    @ApiOperation(value="取消审批退货申请单", consumes="application/json",position=3)
    public PurReturnsUnAuditResultApi doUnAuditPurReturns(@RequestBody PurReturnsUnAuditParamsApi params) {
    	PurReturnsUnAuditResultApi result = new PurReturnsUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReturnsUnAuditParams purReturnsUnAuditParams = params.getParams();
	        if(purReturnsUnAuditParams == null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurReturns2 scmPurReturns = new ScmPurReturns2();
	        scmPurReturns.setRtNo(purReturnsUnAuditParams.getRtNo());
	        scmPurReturnsBiz.doUnAuditPurReturns(scmPurReturns, ParamHelper.createParam(integratedRequest,"doUnAuditPurReturns"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("退货申请单取消审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
