package com.armitage.server.api.business.purorder.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.purorder.params.PurOrderAuditDetailParams;
import com.armitage.server.api.business.purorder.params.PurOrderAuditParams;
import com.armitage.server.api.business.purorder.params.PurOrderAuditParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderListParams;
import com.armitage.server.api.business.purorder.params.PurOrderListParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderLockBillParams;
import com.armitage.server.api.business.purorder.params.PurOrderLockBillParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderOAParams;
import com.armitage.server.api.business.purorder.params.PurOrderOAParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderParams;
import com.armitage.server.api.business.purorder.params.PurOrderParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderUnAuditParams;
import com.armitage.server.api.business.purorder.params.PurOrderUnAuditParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderUpdateContractNoParams;
import com.armitage.server.api.business.purorder.params.PurOrderUpdateContractNoParamsApi;
import com.armitage.server.api.business.purorder.params.PurOrderUpdateStatusParams;
import com.armitage.server.api.business.purorder.params.PurOrderUpdateStatusParamsApi;
import com.armitage.server.api.business.purorder.result.PurOrderAuditResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderDetailOAResult;
import com.armitage.server.api.business.purorder.result.PurOrderDetailResult;
import com.armitage.server.api.business.purorder.result.PurOrderListResult;
import com.armitage.server.api.business.purorder.result.PurOrderListResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderLockBillResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderOAResult;
import com.armitage.server.api.business.purorder.result.PurOrderOAResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderResult;
import com.armitage.server.api.business.purorder.result.PurOrderResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderUnAuditResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderUpdateContractNoResultApi;
import com.armitage.server.api.business.purorder.result.PurOrderUpdateStatusResultApi;
import com.armitage.server.api.business.purrequire.result.ScmAuditDetailHistoryResult;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/purOrder")
@Api(tags="订货业务接口")
public class OrderController {
	private static Log log = LogFactory.getLog(OrderController.class);
	private ScmPurOrderBiz scmPurOrderBiz = (ScmPurOrderBiz) AppContextUtil.getBean("scmPurOrderBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurOrderList", method=RequestMethod.POST)
    @ApiOperation(value="获取订货单列表", consumes="application/json",position=1)
    public PurOrderListResultApi queryPurOrderList(@RequestBody PurOrderListParamsApi params) {
		PurOrderListResultApi result = new PurOrderListResultApi(); 	//显示结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum != null && pageNum.getPageNum() != 0) {
        	pageIndex = pageNum.getPageNum();
        }
        
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderListParams purOrderListParams = params.getParams();
        	if (purOrderListParams == null) {
        		throw new AppException("webservice.params.null");
        	}
        	
        	ScmPurOrderAdvQuery scmPurOrderAdvQuery = new ScmPurOrderAdvQuery();
        	BeanUtils.copyProperties(purOrderListParams, scmPurOrderAdvQuery);
        	scmPurOrderAdvQuery.setFromInterface(true);
        	List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.queryPurOrderList(scmPurOrderAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurOrderList"));
        	
        	if (scmPurOrderList != null && !scmPurOrderList.isEmpty()) {
        		List<PurOrderListResult> resultList = new ArrayList<> ();
        		for(ScmPurOrder2 scmPurOrder : scmPurOrderList) {
        			PurOrderListResult purOrderListResult = new PurOrderListResult();
        			BeanUtils.copyProperties(scmPurOrder, purOrderListResult);
	        		resultList.add(purOrderListResult);
        		}
        		result.setResultList(resultList);
        	}
        } catch (Exception e) {
        	log.error(e);
        	result.setErrCode("-1");
        	result.setErrText("获取订货单列表失败：" + Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
        }
		
		return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurOrder", method=RequestMethod.POST)
    @ApiOperation(value="获取订货单详情", consumes="application/json",position=2)
    public PurOrderResultApi queryPurOrder(@RequestBody PurOrderParamsApi params) {
		PurOrderResultApi result = new PurOrderResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderParams purOrderParams = params.getParams();
	        if(purOrderParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
	        scmPurOrder.setPoNo(purOrderParams.getPoNo());
	        ScmPurOrder2 rtn = scmPurOrderBiz.queryPurOrder(scmPurOrder, ParamHelper.createParam(integratedRequest,"queryPurOrder"));
	        if(rtn!=null){
	        	PurOrderResult purOrderResult = new PurOrderResult();
	        	BeanUtils.copyProperties(rtn, purOrderResult);
	        	List<ScmPurOrderEntry2> scmPurOrderEntryList = rtn.getScmPurOrderEntryList();
	        	if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
	        		List< PurOrderDetailResult > detailList = new ArrayList<PurOrderDetailResult>();
	        		for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList){
	        			PurOrderDetailResult purOrderDetailResult = new PurOrderDetailResult();
	        			BeanUtils.copyProperties(scmPurOrderEntry, purOrderDetailResult);
	        			purOrderDetailResult.setPurUnit(scmPurOrderEntry.getPurUnitName());
	        			if(scmPurOrderEntry.getAuditDetailHistoryList() != null && !scmPurOrderEntry.getAuditDetailHistoryList().isEmpty()){
	        				List<ScmAuditDetailHistoryResult> auditDetailHistoryResultList = new ArrayList<ScmAuditDetailHistoryResult>();
	        				for(ScmAuditDetailHistory2 scmAuditDetailHistory : scmPurOrderEntry.getAuditDetailHistoryList()){
	        					ScmAuditDetailHistoryResult auditDetailHistoryResult = new ScmAuditDetailHistoryResult();
	        					BeanUtils.copyProperties(scmAuditDetailHistory, auditDetailHistoryResult);
	        					auditDetailHistoryResultList.add(auditDetailHistoryResult);
	        				}
	        				purOrderDetailResult.setAuditDetailHistoryResultList(auditDetailHistoryResultList);
	        			}
	        			detailList.add(purOrderDetailResult);
	        		}
	        		purOrderResult.setDetailList(detailList);
	        	}
	        	result.setResult(purOrderResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取订货单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurOrder", method=RequestMethod.POST)
    @ApiOperation(value="审批订货单", consumes="application/json",position=3)
    public PurOrderAuditResultApi doAuditPurOrder(@RequestBody PurOrderAuditParamsApi params) {
    	PurOrderAuditResultApi result = new PurOrderAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderAuditParams purOrderAuditParams = params.getParams();
	        if(purOrderAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purOrderAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purOrderAuditParams.getPoNo());
	        if(purOrderAuditParams.getDetailList()!=null && !purOrderAuditParams.getDetailList().isEmpty()) {
	        	List<CommonAuditDetailParams> detailList = new ArrayList<>();
	        	for(PurOrderAuditDetailParams purOrderAuditDetailParams:purOrderAuditParams.getDetailList()) {
	        		CommonAuditDetailParams commonAuditDetailParams = new CommonAuditDetailParams();
	        		BeanUtils.copyProperties(purOrderAuditDetailParams,commonAuditDetailParams);
	        		detailList.add(commonAuditDetailParams);
	        	}
	        	commonAuditParams.setDetailList(detailList);
	        }
	        scmPurOrderBiz.doAuditPurOrder(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditPurOrder"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("订货单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurOrder", method=RequestMethod.POST)
    @ApiOperation(value="取消审批订货单", consumes="application/json",position=4)
    public PurOrderUnAuditResultApi doUnAuditPurOrder(@RequestBody PurOrderUnAuditParamsApi params) {
    	PurOrderUnAuditResultApi result = new PurOrderUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderUnAuditParams purOrderUnAuditParams = params.getParams();
	        if(purOrderUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
	        scmPurOrder.setPoNo(purOrderUnAuditParams.getPoNo());
	        scmPurOrderBiz.doUnAuditPurOrder(scmPurOrder, ParamHelper.createParam(integratedRequest,"doUnAuditPurOrder"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("订货单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doLockPurOrder", method=RequestMethod.POST)
    @ApiOperation(value="锁单", consumes="application/json",position=3)
    public PurOrderLockBillResultApi doLockPurOrder(@RequestBody PurOrderLockBillParamsApi params) {
		PurOrderLockBillResultApi result = new PurOrderLockBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderLockBillParams purOrderLockBillParams = params.getParams();
        	if(purOrderLockBillParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
    	        scmPurOrder.setPoNo(purOrderLockBillParams.getBillNo());
    	        scmPurOrder.setStatus(purOrderLockBillParams.getBillStatus());
    	        scmPurOrder.setLockStatus(purOrderLockBillParams.getLockType());
    	    	scmPurOrderBiz.doLockPurOrder(scmPurOrder, ParamHelper.createParam(integratedRequest,"doLockPurOrder"));
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
    @RequestMapping(value="/doUpdatePurOrderContractNo", method=RequestMethod.POST)
    @ApiOperation(value="更新合同编号", consumes="application/json",position=3)
    public PurOrderUpdateContractNoResultApi doUpdatePurOrderContractNo(@RequestBody PurOrderUpdateContractNoParamsApi params) {
		PurOrderUpdateContractNoResultApi result = new PurOrderUpdateContractNoResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderUpdateContractNoParams purOrderUpdateContractNoParams = params.getParams();
        	if(purOrderUpdateContractNoParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
    	        scmPurOrder.setPoNo(purOrderUpdateContractNoParams.getBillNo());
    	        scmPurOrder.setContractNo(purOrderUpdateContractNoParams.getContractNo());
    	    	scmPurOrderBiz.doUpdatePurOrderContractNo(scmPurOrder, ParamHelper.createParam(integratedRequest,"doUpdatePurOrderContractNo"));  
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("更新合同编号失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doUpdatePurOrderStatus", method=RequestMethod.POST)
    @ApiOperation(value="更新单据状态", consumes="application/json",position=3)
    public PurOrderUpdateStatusResultApi doUpdatePurOrderStatus(@RequestBody PurOrderUpdateStatusParamsApi params) {
		PurOrderUpdateStatusResultApi result = new PurOrderUpdateStatusResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderUpdateStatusParams purOrderUpdateStatusParams = params.getParams();
        	if(purOrderUpdateStatusParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
    	        scmPurOrder.setPoNo(purOrderUpdateStatusParams.getBillNo());
    	        scmPurOrder.setStatus(purOrderUpdateStatusParams.getBillStatus());
    	    	scmPurOrderBiz.doUpdatePurOrderStatus(scmPurOrder, ParamHelper.createParam(integratedRequest,"doUpdatePurOrderStatus"));    
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
    @RequestMapping(value="/queryPurOrderOA", method=RequestMethod.POST)
    @ApiOperation(value="查询订货单", consumes="application/json",position=2)
    public PurOrderOAResultApi queryPurOrderOA(@RequestBody PurOrderOAParamsApi params) {
		PurOrderOAResultApi result = new PurOrderOAResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurOrderOAParams purOrderOAParams = params.getParams();
	        if(purOrderOAParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
	        scmPurOrder.setPoNo(purOrderOAParams.getBillNo());
	        scmPurOrder.setStatus(purOrderOAParams.getBillStatus());
	        scmPurOrder.setQueryType(purOrderOAParams.getType());
	        List<ScmPurOrder2> scmPurOrderList = scmPurOrderBiz.queryPurOrderOA(scmPurOrder, ParamHelper.createParam(integratedRequest,"queryPurOrderOA"));
	        List<PurOrderOAResult> resultList = new ArrayList<>();
	        if(scmPurOrderList != null && !scmPurOrderList.isEmpty()){
	        	for(ScmPurOrder2 scmPurOrder2 : scmPurOrderList){
	        		PurOrderOAResult purOrderOAResult = new PurOrderOAResult();
		        	BeanUtils.copyProperties(scmPurOrder2, purOrderOAResult);
		        	purOrderOAResult.setDhId(scmPurOrder2.getId());
		        	purOrderOAResult.setBizType(scmPurOrder2.getBizTypeName());
		        	purOrderOAResult.setOrgUnitName(scmPurOrder2.getPurOrgUnitName());
		        	purOrderOAResult.setPayment(scmPurOrder2.getPaymentName());
		        	purOrderOAResult.setSettlement(scmPurOrder2.getSettlementName());
		        	purOrderOAResult.setBuyer(scmPurOrder2.getBuyerName());
		        	purOrderOAResult.setCreator(scmPurOrder2.getCreatorName());
		        	purOrderOAResult.setEditor(scmPurOrder2.getEditorName());
		        	purOrderOAResult.setChecker(scmPurOrder2.getCheckerName());
		        	purOrderOAResult.setStatus(scmPurOrder2.getStatusName());
		        	BigDecimal totalAmt = BigDecimal.ZERO;
		        	List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrder2.getScmPurOrderEntryList();
		        	if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()){
		        		List<PurOrderDetailOAResult> detailList = new ArrayList<PurOrderDetailOAResult>();
		        		for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList){
		        			PurOrderDetailOAResult purOrderDetailOAResult = new PurOrderDetailOAResult();
		        			BeanUtils.copyProperties(scmPurOrderEntry, purOrderDetailOAResult);
		        			purOrderDetailOAResult.setDhId(scmPurOrderEntry.getId());
		        			purOrderDetailOAResult.setPurUnit(scmPurOrderEntry.getPurUnitName());
		        			purOrderDetailOAResult.setBaseUnit(scmPurOrderEntry.getBaseUnitName());
		        			purOrderDetailOAResult.setBuyer(scmPurOrderEntry.getBuyerName());
		        			purOrderDetailOAResult.setRowStatus(scmPurOrderEntry.getRowStatusName());
		        			purOrderDetailOAResult.setChecker(scmPurOrderEntry.getCheckerName());
		        			totalAmt = totalAmt.add(scmPurOrderEntry.getAmt());
		        			detailList.add(purOrderDetailOAResult);
		        		}
		        		purOrderOAResult.setDetailList(detailList);
		        		purOrderOAResult.setTotalAmt(totalAmt);
		        	}
		        	resultList.add(purOrderOAResult);
	        	}
	        }
	        if(resultList != null && !resultList.isEmpty()){
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询订货单失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
}
