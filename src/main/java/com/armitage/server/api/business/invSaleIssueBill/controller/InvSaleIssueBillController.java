package com.armitage.server.api.business.invSaleIssueBill.controller;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillAddParams;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillAddParamsApi;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillParamsApi;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueReturnBillAddParamsApi;
import com.armitage.server.api.business.invSaleIssueBill.result.InvSaleIssueBillItemAddResult;
import com.armitage.server.api.business.invSaleIssueBill.result.InvSaleIssueBillResult;
import com.armitage.server.api.business.invSaleIssueBill.result.InvSaleIssueBillResultApi;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueBillParams;
import com.armitage.server.api.business.invSaleIssueBill.params.InvSaleIssueReturnBillAddParams;
import com.armitage.server.api.business.invSaleIssueBill.result.InvSaleIssueBillAddResultApi;
import com.armitage.server.api.business.invSaleIssueBill.result.InvSaleIssueReturnBillAddResultApi;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvSaleIssueBillBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/InvSaleIssueBill")
@Api(tags="销售出库单接口")
public class InvSaleIssueBillController {
	private static Log log = LogFactory.getLog(InvSaleIssueBillController.class);
	private ScmInvSaleIssueBillBiz scmInvSaleIssueBillBiz = (ScmInvSaleIssueBillBiz) AppContextUtil.getBean("scmInvSaleIssueBillBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvSaleIssueBill", method=RequestMethod.POST)
    @ApiOperation(value="新增销售出库单", consumes="application/json",position=3)
    public InvSaleIssueBillAddResultApi doAddInvSaleIssueBill(@RequestBody InvSaleIssueBillAddParamsApi params) {
		InvSaleIssueBillAddResultApi result = new InvSaleIssueBillAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleIssueBillAddParams invSaleIssueBillAddParams = params.getParams();
        	if(invSaleIssueBillAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(invSaleIssueBillAddParams.getDetailList() == null || invSaleIssueBillAddParams.getDetailList().isEmpty()){
    	    		throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
    	    	}
    	    	Param param = ParamHelper.createParam(integratedRequest,"doAddInvSaleIssueBill");
    	    	ScmInvSaleIssueBill2 scmInvSaleIssueBill=scmInvSaleIssueBillBiz.doAddSaleIssueBill(invSaleIssueBillAddParams, param);
    	    	List<ScmInvSaleIssueBillEntry2> msgDetailList = scmInvSaleIssueBill.getScmInvSaleIssueBillEntryList();
    	    	if(msgDetailList != null && !msgDetailList.isEmpty()){
    	    		List< InvSaleIssueBillItemAddResult > detailList = new ArrayList<InvSaleIssueBillItemAddResult>();
    	    		for(ScmInvSaleIssueBillEntry2 msg : msgDetailList){
    	    			InvSaleIssueBillItemAddResult invSaleIssueBillItemAddResult = new InvSaleIssueBillItemAddResult();
	        			BeanUtils.copyProperties(msg, invSaleIssueBillItemAddResult);
	        			detailList.add(invSaleIssueBillItemAddResult);
	        		}
    	    		result.setDetailList(detailList);
    	    	}
    		    if(scmInvSaleIssueBill!=null){
    		    	result.setOtNo(scmInvSaleIssueBill.getOtNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("销售出库单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvSaleIssueReturnBill", method=RequestMethod.POST)
    @ApiOperation(value="新增销售退库单", consumes="application/json",position=3)
    public InvSaleIssueReturnBillAddResultApi doAddInvSaleIssueBill(@RequestBody InvSaleIssueReturnBillAddParamsApi params) {
		InvSaleIssueReturnBillAddResultApi result = new InvSaleIssueReturnBillAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleIssueReturnBillAddParams invSaleIssueReturnBillAddParams = params.getParams();
        	if(invSaleIssueReturnBillAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(invSaleIssueReturnBillAddParams.getDetailList() == null || invSaleIssueReturnBillAddParams.getDetailList().isEmpty()){
    	    		throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
    	    	}
    	    	Param param = ParamHelper.createParam(integratedRequest,"doAddInvSaleIssueReturnBill");
    	    	ScmInvSaleIssueBill2 scmInvSaleIssueBill=scmInvSaleIssueBillBiz.doAddSaleIssueReturnBill(invSaleIssueReturnBillAddParams, param);
    	    	List<ScmInvSaleIssueBillEntry2> msgDetailList = scmInvSaleIssueBill.getScmInvSaleIssueBillEntryList();
    	    	if(msgDetailList != null && !msgDetailList.isEmpty()){
    	    		List<InvSaleIssueBillItemAddResult> detailList = new ArrayList<InvSaleIssueBillItemAddResult>();
    	    		for(ScmInvSaleIssueBillEntry2 msg : msgDetailList){
    	    			InvSaleIssueBillItemAddResult invSaleIssueBillItemAddResult = new InvSaleIssueBillItemAddResult();
	        			BeanUtils.copyProperties(msg, invSaleIssueBillItemAddResult);
	        			detailList.add(invSaleIssueBillItemAddResult);
	        		}
    	    		result.setDetailList(detailList);
    	    	}
    		    if(scmInvSaleIssueBill!=null){
    		    	result.setOtNo(scmInvSaleIssueBill.getOtNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("销售退库单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvSaleIssueBill", method=RequestMethod.POST)
    @ApiOperation(value="根据第三方单号获取销售出库单详情", consumes="application/json",position=2)
    public InvSaleIssueBillResultApi queryInvSaleIssueBill(@RequestBody InvSaleIssueBillParamsApi params) {
		InvSaleIssueBillResultApi result = new InvSaleIssueBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleIssueBillParams invSaleIssueBillParams = params.getParams();
	        if(invSaleIssueBillParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvSaleIssueBill2 scmInvSaleIssueBill = new ScmInvSaleIssueBill2();
	        scmInvSaleIssueBill.setOtherNo(invSaleIssueBillParams.getOtherNo());
	        ScmInvSaleIssueBill2 rtn = scmInvSaleIssueBillBiz.queryInvSaleIssueBill(scmInvSaleIssueBill, ParamHelper.createParam(integratedRequest,"queryInvSaleIssueBill"));
	        if(rtn!=null){
	        	InvSaleIssueBillResult invSaleIssueBillResult = new InvSaleIssueBillResult();
	        	BeanUtils.copyProperties(rtn, invSaleIssueBillResult);
	        	
	        	result.setResult(invSaleIssueBillResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("根据第三方单号获取销售出库单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
}
