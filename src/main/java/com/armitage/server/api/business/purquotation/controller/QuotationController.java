package com.armitage.server.api.business.purquotation.controller;

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

import com.armitage.server.api.business.purquotation.params.PurQuotationAddParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationAddParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationAuditParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationAuditParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationDelParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationDelParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationEditParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationEditParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationListParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationListParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationSubmitParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationSubmitParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationUnAuditParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationUnAuditParamsApi;
import com.armitage.server.api.business.purquotation.params.PurQuotationUnSubmitParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationUnSubmitParamsApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationAddResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationAuditResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationDelResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationDetailResult;
import com.armitage.server.api.business.purquotation.result.PurQuotationEditResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationListResult;
import com.armitage.server.api.business.purquotation.result.PurQuotationListResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationResult;
import com.armitage.server.api.business.purquotation.result.PurQuotationResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationSubmitResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationUnAuditResultApi;
import com.armitage.server.api.business.purquotation.result.PurQuotationUnSubmitResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/purQuotation")
@Api(tags="报价业务接口")
public class QuotationController {
	private static Log log = LogFactory.getLog(QuotationController.class);
    private ScmPurQuotationBiz scmPurQuotationBiz = (ScmPurQuotationBiz) AppContextUtil.getBean("scmPurQuotationBiz");
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doDelPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="删除报价单", consumes="application/json")
    public PurQuotationDelResultApi doDelPurQuotation(@RequestBody PurQuotationDelParamsApi params) {
    	PurQuotationDelResultApi result = new PurQuotationDelResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationDelParams purQuotationDelParams = params.getParams();
	        if(purQuotationDelParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurQuotation scmPurQuotation= new ScmPurQuotation();
	        scmPurQuotation.setPqNo(purQuotationDelParams.getPqNo());
	        scmPurQuotationBiz.doDelPurQuotation(scmPurQuotation, ParamHelper.createParam(integratedRequest,"scmPurQuotation"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单删除失败"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="提交报价单", consumes="application/json")
    public PurQuotationSubmitResultApi doSubmitPurQuotation(@RequestBody PurQuotationSubmitParamsApi params) {
    	PurQuotationSubmitResultApi result = new PurQuotationSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationSubmitParams purQuotationSubmitParams = params.getParams();
	        if(purQuotationSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurQuotation scmPurQuotation= new ScmPurQuotation();
	        scmPurQuotation.setPqNo(purQuotationSubmitParams.getPqNo());
	        scmPurQuotationBiz.doSubmitPurQuotation(scmPurQuotation, ParamHelper.createParam(integratedRequest,"scmPurQuotation"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单提交失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="取消提交报价单", consumes="application/json")
    public PurQuotationUnSubmitResultApi doUnSubmitPurQuotation(@RequestBody PurQuotationUnSubmitParamsApi params) {
    	PurQuotationUnSubmitResultApi result = new PurQuotationUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationUnSubmitParams purQuotationUnSubmitParams = params.getParams();
	        if(purQuotationUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurQuotation scmPurQuotation= new ScmPurQuotation();
	        scmPurQuotation.setPqNo(purQuotationUnSubmitParams.getPqNo());
	        scmPurQuotationBiz.doUnSubmitPurQuotation(scmPurQuotation, ParamHelper.createParam(integratedRequest,"scmPurQuotation"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单取消提交失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurQuotationList", method=RequestMethod.POST)
    @ApiOperation(value="查询报价单列表", consumes="application/json")
    public PurQuotationListResultApi queryPurQuotationList(@RequestBody PurQuotationListParamsApi params) {
    	PurQuotationListResultApi result = new PurQuotationListResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationListParams purQuotationListParams = params.getParams();
	        if(purQuotationListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurQuotation scmPurQuotation= new ScmPurQuotation();
	        scmPurQuotation.setPqNo(purQuotationListParams.getPqNo());
	        List<ScmPurQuotation2> list=scmPurQuotationBiz.queryPurQuotationList(purQuotationListParams, ParamHelper.createParam(integratedRequest,"purQuotationListParams"));
	        if(list!=null){
	        	result.setErrCode("1");
	    		result.setErrText("报价单列表查询成功");
	    		List<PurQuotationListResult> purList = new ArrayList<PurQuotationListResult>();
	            for (ScmPurQuotation2 scm : list) {
	            	PurQuotationListResult pur = new PurQuotationListResult();
	            	pur.setVendorCode(String.valueOf(scm.getVendorId()));
	            	pur.setBuyerCode(String.valueOf(scm.getBuyerId()));
	                pur.setBegDate(scm.getBegDate());
	                pur.setCheckDate(scm.getCheckDate());
	                pur.setChecker(scm.getChecker());
	                pur.setCreateDate(scm.getCreateDate());
	                pur.setCreator(scm.getCreator());
	                pur.setEndDate(scm.getEndDate());
	                pur.setPqDate(scm.getPqDate());
	                pur.setPqNo(scm.getPqNo());
	                pur.setRemarks(scm.getRemarks());
	                pur.setStatus(scm.getStatus());
	                purList.add(pur);
	            }
	    		result.setResultList(purList);
	    		return result;
	        }else{
	    		result.setErrCode("-1");
	    		result.setErrText("报价单列表查询失败!");
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单列表查询失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return null;
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="获取报价单详情", consumes="application/json",position=1)
    public PurQuotationResultApi queryPurQuotation(@RequestBody PurQuotationParamsApi params) {
		PurQuotationResultApi result = new PurQuotationResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationParams purQuotationParams = params.getParams();
	        if(purQuotationParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurQuotation2 scmPurQuotation= new ScmPurQuotation2();
	        scmPurQuotation.setPqNo(purQuotationParams.getPqNo());
	        ScmPurQuotation2 rtn = scmPurQuotationBiz.queryPurQuotation(scmPurQuotation, ParamHelper.createParam(integratedRequest,"queryPurQuotation"));
	        if(rtn!=null){
	        	PurQuotationResult purQuotationResult = new PurQuotationResult();
	        	BeanUtils.copyProperties(rtn, purQuotationResult);
	        	List<ScmPurQuotationEntry2> scmPurQuotationEntryList = rtn.getScmPurQuotationEntryList();
	        	if(scmPurQuotationEntryList!=null && !scmPurQuotationEntryList.isEmpty()){
	        		List<PurQuotationDetailResult> detailList = new ArrayList<PurQuotationDetailResult>();
	        		for(ScmPurQuotationEntry2 scmPurQuotationEntry : scmPurQuotationEntryList){
	        			PurQuotationDetailResult purQuotationDetailResult = new PurQuotationDetailResult();
	        			BeanUtils.copyProperties(scmPurQuotationEntry, purQuotationDetailResult);
	        			purQuotationDetailResult.setPurUnit(scmPurQuotationEntry.getPurUnitName());
	        			detailList.add(purQuotationDetailResult);
	        		}
	        		purQuotationResult.setDetailList(detailList);
	        	}
	        	result.setResult(purQuotationResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取报价单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
    }
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="新增报价单", consumes="application/json")
    public PurQuotationAddResultApi doAddPurQuotation(@RequestBody PurQuotationAddParamsApi params) {
    	PurQuotationAddResultApi result = new PurQuotationAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        String pqNo=null;
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationAddParams purQuotationAddParams = params.getParams();
        	if(purQuotationAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    		    ScmPurQuotation2 scmPurQuotation2=scmPurQuotationBiz.doAddPurQuotation(purQuotationAddParams, ParamHelper.createParam(integratedRequest,"purQuotationAddParams"));
    		    if(scmPurQuotation2!=null){
    	        	pqNo=scmPurQuotation2.getPqNo();
    	        }    
    	    }
        result.setPqNo(pqNo);
        } catch (Exception e) {
	    	log.error(e);
	    	result.setPqNo(pqNo);
	    	result.setErrCode("-1");
	    	result.setErrText("报价单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    	
    }
    
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doEditPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="修改报价单", consumes="application/json")
    public PurQuotationEditResultApi doEditPurQuotation(@RequestBody PurQuotationEditParamsApi params) {
    	PurQuotationEditResultApi result = new PurQuotationEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        String pqNo=null;
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationEditParams purQuotationEditParams = params.getParams();
        	if(purQuotationEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    		    ScmPurQuotation2 scmPurQuotation2=scmPurQuotationBiz.doEditPurQuotation(purQuotationEditParams, ParamHelper.createParam(integratedRequest,"purQuotationEditParams"));
    		    if(scmPurQuotation2!=null){
    	        	pqNo=scmPurQuotation2.getPqNo();
    	        }    
    	    }
        result.setPqNo(pqNo);
        } catch (Exception e) {
	    	log.error(e);
	    	result.setPqNo(pqNo);
	    	result.setErrCode("-1");
	    	result.setErrText("报价单修改失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    	
    }
    
    @ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="审批报价单", consumes="application/json",position=2)
    public PurQuotationAuditResultApi doAuditPurQuotation(@RequestBody PurQuotationAuditParamsApi params) {
    	PurQuotationAuditResultApi result = new PurQuotationAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationAuditParams purQuotationAuditParams = params.getParams();
	        if(purQuotationAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purQuotationAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purQuotationAuditParams.getPqNo());
	        scmPurQuotationBiz.doAuditPurQuotation(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditPurQuotation"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
    @ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurQuotation", method=RequestMethod.POST)
    @ApiOperation(value="取消审批报价单", consumes="application/json",position=3)
    public PurQuotationUnAuditResultApi doUnAuditPurQuotation(@RequestBody PurQuotationUnAuditParamsApi params) {
    	PurQuotationUnAuditResultApi result = new PurQuotationUnAuditResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurQuotationUnAuditParams purQuotationUnAuditParams = params.getParams();
	        if(purQuotationUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurQuotation2 scmPurQuotation = new ScmPurQuotation2();
	        scmPurQuotation.setPqNo(purQuotationUnAuditParams.getPqNo());
	        scmPurQuotationBiz.doUnAuditPurQuotation(scmPurQuotation, ParamHelper.createParam(integratedRequest,"doUnAuditPurQuotation"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("报价单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
    	return result;
    }
}
