package com.armitage.server.api.business.apinvoice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.api.business.apinvoice.params.ApinvoiceQueryAccountDetailParams;
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

import com.armitage.server.api.business.apinvoice.params.ApinvoiceLockBillParams;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceLockBillParamsApi;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceOAParams;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceOAParamsApi;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceQueryAccountDetailParamsApi;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceQueryAccountParams;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceQueryAccountParamsApi;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceUpdateStatusParams;
import com.armitage.server.api.business.apinvoice.params.ApinvoiceUpdateStatusParamsApi;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceDetailOAResult;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceLockBillResultApi;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceOAResult;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceOAResultApi;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceQueryAccountDetailResult;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceQueryAccountDetailResultApi;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceQueryAccountResult;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceQueryAccountResultApi;
import com.armitage.server.api.business.apinvoice.result.ApinvoiceUpdateStatusResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iaps.daily.model.Apinvoice2;
import com.armitage.server.iaps.daily.model.ApinvoiceEntry2;
import com.armitage.server.iaps.daily.service.ApinvoiceBiz;
import com.armitage.server.iaps.report.apInvoice.model.ApsApInvoiceAccount;
import com.armitage.server.iaps.report.apInvoice.model.ApsApInvoiceContactsDetail;
import com.armitage.server.iaps.report.apInvoice.service.ApsApInvoiceReportBiz;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/apinvoice")
@Api(tags="应付业务接口")
public class ApinvoiceController {
	private static Log log = LogFactory.getLog(ApinvoiceController.class);
	private ApinvoiceBiz apinvoiceBiz = (ApinvoiceBiz) AppContextUtil.getBean("apinvoiceBiz");
	private ApsApInvoiceReportBiz apsApInvoiceReportBiz = (ApsApInvoiceReportBiz) AppContextUtil.getBean("apsApInvoiceReportBiz");
	private static ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz = (ScmSupplierRegInvitationBiz) AppContextUtil.getBean("scmSupplierRegInvitationBiz");
	private static ScmSupplierDemanderBiz scmSupplierDemanderBiz = (ScmSupplierDemanderBiz) AppContextUtil.getBean("scmSupplierDemanderBiz");
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doLockApinvoice", method=RequestMethod.POST)
    @ApiOperation(value="锁单", consumes="application/json",position=3)
    public ApinvoiceLockBillResultApi doLockApinvoice(@RequestBody ApinvoiceLockBillParamsApi params) {
		ApinvoiceLockBillResultApi result = new ApinvoiceLockBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApinvoiceLockBillParams apinvoiceLockBillParams = params.getParams();
        	if(apinvoiceLockBillParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Apinvoice2 apinvoice= new Apinvoice2();
    	        apinvoice.setBillNo(apinvoiceLockBillParams.getBillNo());
    	        apinvoice.setStatus(apinvoiceLockBillParams.getBillStatus());
    	        apinvoice.setLockStatus(apinvoiceLockBillParams.getLockType());
    	    	apinvoiceBiz.doLockApinvoice(apinvoice, ParamHelper.createParam(integratedRequest,"doLockApinvoice"));
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("锁单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doUpdateApinvoiceStatus", method=RequestMethod.POST)
    @ApiOperation(value="更新单据状态", consumes="application/json",position=3)
    public ApinvoiceUpdateStatusResultApi doUpdateApinvoiceStatus(@RequestBody ApinvoiceUpdateStatusParamsApi params) {
		ApinvoiceUpdateStatusResultApi result = new ApinvoiceUpdateStatusResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApinvoiceUpdateStatusParams apinvoiceUpdateStatusParams = params.getParams();
        	if(apinvoiceUpdateStatusParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Apinvoice2 apinvoice= new Apinvoice2();
    	        apinvoice.setBillNo(apinvoiceUpdateStatusParams.getBillNo());
    	        apinvoice.setStatus(apinvoiceUpdateStatusParams.getBillStatus());
    	    	apinvoiceBiz.doUpdateApinvoiceStatus(apinvoice, ParamHelper.createParam(integratedRequest,"doUpdateApinvoiceStatus"));    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("更新单据状态失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/queryApinvoiceOA", method=RequestMethod.POST)
    @ApiOperation(value="查询应付单", consumes="application/json",position=2)
    public ApinvoiceOAResultApi queryApinvoiceOA(@RequestBody ApinvoiceOAParamsApi params) {
		ApinvoiceOAResultApi result = new ApinvoiceOAResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApinvoiceOAParams apinvoiceOAParams = params.getParams();
	        if(apinvoiceOAParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Apinvoice2 apinvoice= new Apinvoice2();
	        apinvoice.setBillNo(apinvoiceOAParams.getBillNo());
	        apinvoice.setStatus(apinvoiceOAParams.getBillStatus());
	        apinvoice.setQueryType(apinvoiceOAParams.getType());
	        List<Apinvoice2> apinvoiceList = apinvoiceBiz.queryApinvoiceOA(apinvoice, ParamHelper.createParam(integratedRequest,"queryApinvoiceOA"));
	        List< ApinvoiceOAResult > resultList = new ArrayList<>();
	        if(apinvoiceList!=null && !apinvoiceList.isEmpty()){
	        	for(Apinvoice2 apinvoice2 : apinvoiceList){
	        		ApinvoiceOAResult apinvoiceOAResult = new ApinvoiceOAResult();
		        	BeanUtils.copyProperties(apinvoice2, apinvoiceOAResult);
		        	apinvoiceOAResult.setPayment(apinvoice2.getPaymentName());
		        	apinvoiceOAResult.setPaycomment(apinvoice2.getComment());
		        	apinvoiceOAResult.setCreator(apinvoice2.getCreatorName());
		        	apinvoiceOAResult.setEditor(apinvoice2.getEditorName());
		        	apinvoiceOAResult.setChecker(apinvoice2.getCheckerName());
		        	List<ApinvoiceEntry2> apinvoiceEntryList = apinvoice2.getApinvoiceEntryList();
		        	if(apinvoiceEntryList!=null && !apinvoiceEntryList.isEmpty()){
		        		List< ApinvoiceDetailOAResult > detailList = new ArrayList<ApinvoiceDetailOAResult>();
		        		for(ApinvoiceEntry2 apinvoiceEntry:apinvoiceEntryList){
		        			ApinvoiceDetailOAResult apinvoiceDetailOAResult = new ApinvoiceDetailOAResult();
		        			BeanUtils.copyProperties(apinvoiceEntry, apinvoiceDetailOAResult);
		        			apinvoiceDetailOAResult.setUnit(apinvoiceEntry.getUnitName());
		        			apinvoiceDetailOAResult.setBaseUnit(apinvoiceEntry.getBaseUnitName());
		        			detailList.add(apinvoiceDetailOAResult);
		        		}
		        		apinvoiceOAResult.setDetailList(detailList);
		        	}
		        	resultList.add(apinvoiceOAResult);
	        	}
	        }
	        if(resultList != null && !resultList.isEmpty()){
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询应付单失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="supplierPlatApi")
    @ResponseBody
    @RequestMapping(value="/queryApinvoiceAccount", method=RequestMethod.POST)
    @ApiOperation(value="查询应付往来对账", consumes="application/json",position=2)
    public ApinvoiceQueryAccountResultApi queryApinvoiceAccount(@RequestBody ApinvoiceQueryAccountParamsApi params) {
		ApinvoiceQueryAccountResultApi result = new ApinvoiceQueryAccountResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApinvoiceQueryAccountParams apinvoiceQueryAccountParams = params.getParams();
	        if(apinvoiceQueryAccountParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryApinvoiceAccount");
	        ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByDemanderId(apinvoiceQueryAccountParams.getDemanderId(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
				param.setOrgUnitNo(scmSupplierDemander.getControlUnitNo());
				param.setControlUnitNo(scmSupplierDemander.getControlUnitNo());
				ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(apinvoiceQueryAccountParams.getPlatVendorId(), param.getControlUnitNo(), param);
		        if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getVendorId() > 0){
		        	HashMap<String,Object> map = new HashMap<String,Object>();
			        map.put("orgUnitNo", scmSupplierRegInvitation.getControlUnitNo());
			        map.put("controlUnitNo", scmSupplierRegInvitation.getControlUnitNo());
			        map.put("beginDate", FormatUtils.fmtDate(apinvoiceQueryAccountParams.getBeginDate()));
			        map.put("endDate", FormatUtils.fmtDate(apinvoiceQueryAccountParams.getEndDate()));
			        map.put("vendorId",scmSupplierRegInvitation.getVendorId());
			        List<ApsApInvoiceAccount> apsApInvoiceAccountList = apsApInvoiceReportBiz.selectApInvoiceAccountForSupplierPlat(map);
			        List<ApinvoiceQueryAccountResult> resultList = new ArrayList<>();
			        if(apsApInvoiceAccountList!=null && !apsApInvoiceAccountList.isEmpty()){
			        	for(ApsApInvoiceAccount apsApInvoiceAccount : apsApInvoiceAccountList){
			        		ApinvoiceQueryAccountResult apinvoiceQueryAccountResult = new ApinvoiceQueryAccountResult();
				        	BeanUtils.copyProperties(apsApInvoiceAccount, apinvoiceQueryAccountResult);
				        	resultList.add(apinvoiceQueryAccountResult);
			        	}
			        }
			        if(resultList != null && !resultList.isEmpty()){
			        	result.setResultList(resultList);
			        }
		        }
			}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询应付往来对账："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="supplierPlatApi")
    @ResponseBody
    @RequestMapping(value="/queryApinvoiceAccountDetail", method=RequestMethod.POST)
    @ApiOperation(value="查询应付往来对账明细", consumes="application/json",position=2)
    public ApinvoiceQueryAccountDetailResultApi queryApinvoiceAccountDetail(@RequestBody ApinvoiceQueryAccountDetailParamsApi params) {
		ApinvoiceQueryAccountDetailResultApi result = new ApinvoiceQueryAccountDetailResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApinvoiceQueryAccountDetailParams apinvoiceQueryAccountDetailParams = params.getParams();
	        if(apinvoiceQueryAccountDetailParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryApinvoiceAccountDetail");
	        ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.selectByDemanderId(apinvoiceQueryAccountDetailParams.getDemanderId(), param);
			if(scmSupplierDemander != null && scmSupplierDemander.getDemanderId() > 0 && StringUtils.isNotBlank(scmSupplierDemander.getUniqueNo())){
				param.setOrgUnitNo(scmSupplierDemander.getControlUnitNo());
				param.setControlUnitNo(scmSupplierDemander.getControlUnitNo());
				ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByPlatVendorIdAndCtrl(apinvoiceQueryAccountDetailParams.getPlatVendorId(), param.getControlUnitNo(), param);
		        if(scmSupplierRegInvitation != null && scmSupplierRegInvitation.getVendorId() > 0){
		        	HashMap<String,Object> map = new HashMap<String,Object>();
			        map.put("orgUnitNo", scmSupplierRegInvitation.getControlUnitNo());
			        map.put("controlUnitNo", scmSupplierRegInvitation.getControlUnitNo());
			        map.put("beginDate", FormatUtils.fmtDate(apinvoiceQueryAccountDetailParams.getBeginDate()));
			        map.put("endDate", FormatUtils.fmtDate(apinvoiceQueryAccountDetailParams.getEndDate()));
			        map.put("vendorId",scmSupplierRegInvitation.getVendorId());
			        map.put("sourceWrNos",apinvoiceQueryAccountDetailParams.getSourceWrNos());
			        map.put("finOrgUnitNo",apinvoiceQueryAccountDetailParams.getFinOrgUnitNo());
			        List<ApsApInvoiceContactsDetail> apsApInvoiceContactsDetailList = apsApInvoiceReportBiz.selectApInvoiceContactsDetailForSupplierPlat(map);
			        List<ApinvoiceQueryAccountDetailResult> resultList = new ArrayList<>();
			        if(apsApInvoiceContactsDetailList!=null && !apsApInvoiceContactsDetailList.isEmpty()){
			        	for(ApsApInvoiceContactsDetail apsApInvoiceContactsDetail : apsApInvoiceContactsDetailList){
			        		ApinvoiceQueryAccountDetailResult apinvoiceQueryAccountDetailResult = new ApinvoiceQueryAccountDetailResult();
				        	BeanUtils.copyProperties(apsApInvoiceContactsDetail, apinvoiceQueryAccountDetailResult);
				        	resultList.add(apinvoiceQueryAccountDetailResult);
			        	}
			        }
			        if(resultList != null && !resultList.isEmpty()){
			        	result.setResultList(resultList);
			        }
		        }
			}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询应付往来对账明细："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
}
