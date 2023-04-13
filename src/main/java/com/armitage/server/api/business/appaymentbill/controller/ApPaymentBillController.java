package com.armitage.server.api.business.appaymentbill.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.armitage.server.api.business.appaymentbill.result.ApPaymentBillDetailOAResult;
import com.armitage.server.api.business.appaymentbill.result.ApPaymentBillOAResult;
import com.armitage.server.api.business.appaymentbill.result.ApPaymentLockBillResultApi;
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

import com.armitage.server.api.business.appaymentbill.params.ApPaymentBillOAParams;
import com.armitage.server.api.business.appaymentbill.params.ApPaymentBillOAParamsApi;
import com.armitage.server.api.business.appaymentbill.params.ApPaymentBillUpdateStatusParams;
import com.armitage.server.api.business.appaymentbill.params.ApPaymentBillUpdateStatusParamsApi;
import com.armitage.server.api.business.appaymentbill.params.ApPaymentLockBillParams;
import com.armitage.server.api.business.appaymentbill.params.ApPaymentLockBillParamsApi;
import com.armitage.server.api.business.appaymentbill.result.ApPaymentBillOAResultApi;
import com.armitage.server.api.business.appaymentbill.result.ApPaymentBillUpdateStatusResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iaps.daily.model.ApPaymentRequestBill2;
import com.armitage.server.iaps.daily.model.ApPaymentRequestBillEntry2;
import com.armitage.server.iaps.daily.service.ApPaymentRequestBillBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/appaymentbill")
@Api(tags="付款业务接口")
public class ApPaymentBillController {
	private static Log log = LogFactory.getLog(ApPaymentBillController.class);
	private ApPaymentRequestBillBiz apPaymentRequestBillBiz = (ApPaymentRequestBillBiz) AppContextUtil.getBean("apPaymentRequestBillBiz");
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doLockApPaymentBill", method=RequestMethod.POST)
    @ApiOperation(value="锁单", consumes="application/json",position=3)
    public ApPaymentLockBillResultApi doLockApPaymentBill(@RequestBody ApPaymentLockBillParamsApi params) {
		ApPaymentLockBillResultApi result = new ApPaymentLockBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApPaymentLockBillParams apPaymentLockBillParams = params.getParams();
        	if(apPaymentLockBillParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ApPaymentRequestBill2 apPaymentRequestBill= new ApPaymentRequestBill2();
    	        apPaymentRequestBill.setBillNo(apPaymentLockBillParams.getBillNo());
    	        apPaymentRequestBill.setStatus(apPaymentLockBillParams.getBillStatus());
    	        apPaymentRequestBill.setLockStatus(apPaymentLockBillParams.getLockType());
    	    	apPaymentRequestBillBiz.doLockApPaymentBill(apPaymentRequestBill, ParamHelper.createParam(integratedRequest,"doLockApPaymentBill"));
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
    @RequestMapping(value="/doUpdateApPaymentBillStatus", method=RequestMethod.POST)
    @ApiOperation(value="更新单据状态", consumes="application/json",position=3)
    public ApPaymentBillUpdateStatusResultApi doUpdateApPaymentBillStatus(@RequestBody ApPaymentBillUpdateStatusParamsApi params) {
		ApPaymentBillUpdateStatusResultApi result = new ApPaymentBillUpdateStatusResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApPaymentBillUpdateStatusParams apPaymentBillUpdateStatusParams = params.getParams();
        	if(apPaymentBillUpdateStatusParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
				log.info("OA调更新单据状态接口时间："+FormatUtils.fmtDateTime(new Date()));
    	    	long beginTime = System.currentTimeMillis();
    	    	ApPaymentRequestBill2 apPaymentRequestBill= new ApPaymentRequestBill2();
    	        apPaymentRequestBill.setBillNo(apPaymentBillUpdateStatusParams.getBillNo());
    	        apPaymentRequestBill.setStatus(apPaymentBillUpdateStatusParams.getBillStatus());
    	    	apPaymentRequestBillBiz.doUpdateApPaymentBillStatus(apPaymentRequestBill, ParamHelper.createParam(integratedRequest,"doUpdateApPaymentBillStatus"));    
				long endTime = System.currentTimeMillis();
    	    	log.info("OA调更新单据状态接口处理耗时："+((endTime-beginTime)/1000)+"秒");
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
    @RequestMapping(value="/queryApPaymentBillOA", method=RequestMethod.POST)
    @ApiOperation(value="查询付款单", consumes="application/json",position=2)
    public ApPaymentBillOAResultApi queryApPaymentBillOA(@RequestBody ApPaymentBillOAParamsApi params) {
		ApPaymentBillOAResultApi result = new ApPaymentBillOAResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	ApPaymentBillOAParams apPaymentBillOAParams = params.getParams();
	        if(apPaymentBillOAParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ApPaymentRequestBill2 apPaymentRequestBill= new ApPaymentRequestBill2();
	        apPaymentRequestBill.setBillNo(apPaymentBillOAParams.getBillNo());
	        apPaymentRequestBill.setStatus(apPaymentBillOAParams.getBillStatus());
	        apPaymentRequestBill.setQueryType(apPaymentBillOAParams.getType());
	        List<ApPaymentRequestBill2> apPaymentRequestBillList = apPaymentRequestBillBiz.queryApPaymentBillOA(apPaymentRequestBill, ParamHelper.createParam(integratedRequest,"queryApPaymentBillOA"));
	        List< ApPaymentBillOAResult > resultList = new ArrayList<>();
	        if(apPaymentRequestBillList!=null && !apPaymentRequestBillList.isEmpty()){
	        	for(ApPaymentRequestBill2 apPaymentRequestBill2 : apPaymentRequestBillList){
	        		ApPaymentBillOAResult apPaymentBillOAResult = new ApPaymentBillOAResult();
		        	BeanUtils.copyProperties(apPaymentRequestBill2, apPaymentBillOAResult);
		        	apPaymentBillOAResult.setPaycomment(apPaymentRequestBill2.getComment());
		        	apPaymentBillOAResult.setCreator(apPaymentRequestBill2.getCreatorName());
		        	apPaymentBillOAResult.setEditor(apPaymentRequestBill2.getEditorName());
		        	apPaymentBillOAResult.setChecker(apPaymentRequestBill2.getCheckerName());
		        	apPaymentBillOAResult.setPayment(apPaymentRequestBill2.getSettleTypeName());
		        	List<ApPaymentRequestBillEntry2> apPaymentRequestBillEntryList = apPaymentRequestBill2.getApPaymentRequestBillEntryList();
		        	if(apPaymentRequestBillEntryList!=null && !apPaymentRequestBillEntryList.isEmpty()){
		        		List< ApPaymentBillDetailOAResult > detailList = new ArrayList<ApPaymentBillDetailOAResult>();
		        		for(ApPaymentRequestBillEntry2 apPaymentBillEntry:apPaymentRequestBillEntryList){
		        			ApPaymentBillDetailOAResult apPaymentBillDetailOAResult = new ApPaymentBillDetailOAResult();
		        			BeanUtils.copyProperties(apPaymentBillEntry, apPaymentBillDetailOAResult);
		        			detailList.add(apPaymentBillDetailOAResult);
		        		}
		        		apPaymentBillOAResult.setDetailList(detailList);
		        	}
		        	resultList.add(apPaymentBillOAResult);
	        	}
	        }
	        if(resultList != null && !resultList.isEmpty()){
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询付款单失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
}
