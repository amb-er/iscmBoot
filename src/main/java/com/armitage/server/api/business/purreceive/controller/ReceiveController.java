package com.armitage.server.api.business.purreceive.controller;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.armitage.server.api.business.purreceive.params.PurRecPurOrgUnitParams;
import com.armitage.server.api.business.purreceive.params.PurRecPurOrgUnitParamsApi;
import com.armitage.server.api.business.purreceive.params.PurRecRateListParamsApi;
import com.armitage.server.api.business.purreceive.params.PurRecSupplierListParams;
import com.armitage.server.api.business.purreceive.params.PurRecSupplierListParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveAddParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveAddParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveAuditParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveAuditParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveDelParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveDelParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveDeptParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveDeptParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveEditParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveEditParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveGenerateWrReceiptsParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveGenerateWrReceiptsParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveListParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveListParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveSubmitParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveSubmitParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveUnAuditParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveUnAuditParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveUnSubmitParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveUnSubmitParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiveWareHousesParams;
import com.armitage.server.api.business.purreceive.params.PurReceiveWareHousesParamsApi;
import com.armitage.server.api.business.purreceive.params.PurReceiverListParams;
import com.armitage.server.api.business.purreceive.params.PurReceiverListParamsApi;
import com.armitage.server.api.business.purreceive.result.PurRecPurOrgUnitResult;
import com.armitage.server.api.business.purreceive.result.PurRecPurOrgUnitResultApi;
import com.armitage.server.api.business.purreceive.result.PurRecRateListResult;
import com.armitage.server.api.business.purreceive.result.PurRecRateListResultApi;
import com.armitage.server.api.business.purreceive.result.PurRecSupplierListResult;
import com.armitage.server.api.business.purreceive.result.PurRecSupplierListResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveAddResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveAuditResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveDelResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveDeptResult;
import com.armitage.server.api.business.purreceive.result.PurReceiveDeptResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveDetailResult;
import com.armitage.server.api.business.purreceive.result.PurReceiveEditResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveListResult;
import com.armitage.server.api.business.purreceive.result.PurReceiveListResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveResult;
import com.armitage.server.api.business.purreceive.result.PurReceiveResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveSubmitResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveUnAuditResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveUnSubmitResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiveWareHousesResult;
import com.armitage.server.api.business.purreceive.result.PurReceiveWareHousesResultApi;
import com.armitage.server.api.business.purreceive.result.PurReceiverListResult;
import com.armitage.server.api.business.purreceive.result.PurReceiverListResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.ScmsupplierAdvQuery;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmDeptReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;

@Controller
@RequestMapping("/api/purReceive")
@Api(tags="收货业务接口")
public class ReceiveController {
	private static Log log = LogFactory.getLog(ReceiveController.class);
    private ScmPurReceiveBiz scmPurReceiveBiz = (ScmPurReceiveBiz) AppContextUtil.getBean("scmPurReceiveBiz");
    private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
    private PubSysBasicInfoBiz pubSysBasicInfoBiz = (PubSysBasicInfoBiz) AppContextUtil.getBean("pubSysBasicInfoBiz");
    private ScmDeptReceiveBiz scmDeptReceiveBiz = (ScmDeptReceiveBiz) AppContextUtil.getBean("scmDeptReceiveBiz");
    private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
    
    @ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReceiveList", method=RequestMethod.POST)
    @ApiOperation(value="获取收货单列表", consumes="application/json",position=1)
    public PurReceiveListResultApi queryPurReceiveList(@RequestBody PurReceiveListParamsApi params) {
    	PurReceiveListResultApi result = new PurReceiveListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveListParams purReceiveListParams = params.getParams();
	        if(purReceiveListParams
	        		==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceiveAdvQuery scmPurReceiveAdvQuery = new ScmPurReceiveAdvQuery();
	        BeanUtils.copyProperties(purReceiveListParams, scmPurReceiveAdvQuery);
	        scmPurReceiveAdvQuery.setFromInterface(true);
	        List<ScmPurReceive2> scmPurReceiveList = scmPurReceiveBiz.queryPurReceiveList(scmPurReceiveAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurReceiveList"));
	        if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
	        	List< PurReceiveListResult > resultList = new ArrayList<PurReceiveListResult>();
	        	for(ScmPurReceive2 scmPurReceive:scmPurReceiveList){
	        		PurReceiveListResult purReceiveListResult = new PurReceiveListResult();
	        		BeanUtils.copyProperties(scmPurReceive, purReceiveListResult);
	        		resultList.add(purReceiveListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取收货单列表:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="获取收货单详情", consumes="application/json",position=2)
    public PurReceiveResultApi queryPurReceive(@RequestBody PurReceiveParamsApi params) {
		PurReceiveResultApi result = new PurReceiveResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveParams purReceiveParams = params.getParams();
	        if(purReceiveParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveParams.getPvNo());
	        ScmPurReceive2 rtn = scmPurReceiveBiz.queryPurReceive(scmPurReceive, ParamHelper.createParam(integratedRequest,"queryPurReceive"));
	        if(rtn!=null){
	        	PurReceiveResult purReceiveResult = new PurReceiveResult();
	        	BeanUtils.copyProperties(rtn, purReceiveResult);
	        	purReceiveResult.setEditorDate(rtn.getEditDate());
	        	List<ScmPurReceiveEntry2> scmPurReceiveEntryList = rtn.getScmPurReceiveEntryList();
	        	if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()){
	        		List< PurReceiveDetailResult > detailList = new ArrayList<PurReceiveDetailResult>();
	        		for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList){
	        			if(scmPurReceiveEntry.getReceiveTopRatio() == null){
	        				scmPurReceiveEntry.setReceiveTopRatio(BigDecimal.ZERO);
	        			}
	        			PurReceiveDetailResult purReceiveDetailResult = new PurReceiveDetailResult();
	        			BeanUtils.copyProperties(scmPurReceiveEntry, purReceiveDetailResult);
	        			purReceiveDetailResult.setReceiveTopRatio(purReceiveDetailResult.getReceiveTopRatio().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
	        			detailList.add(purReceiveDetailResult);
	        		}
	        		purReceiveResult.setDetailList(detailList);
	        	}
	        	result.setResult(purReceiveResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取收货单详情:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="新增收货单", consumes="application/json",position=3)
    public PurReceiveAddResultApi doAddPurReceive(@RequestBody PurReceiveAddParamsApi params) {
		PurReceiveAddResultApi result = new PurReceiveAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveAddParams purReceiveAddParams = params.getParams();
        	if(purReceiveAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurReceive2 scmPurReceive2=scmPurReceiveBiz.doAddPurReceive(purReceiveAddParams, ParamHelper.createParam(integratedRequest,"doAddPurReceive"));
    		    if(scmPurReceive2!=null){
    		    	result.setPvNo(scmPurReceive2.getPvNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("收货单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doEditPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="修改收货单", consumes="application/json",position=3)
    public PurReceiveEditResultApi doEditPurReceive(@RequestBody PurReceiveEditParamsApi params) {
		PurReceiveEditResultApi result = new PurReceiveEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveEditParams purReceiveEditParams = params.getParams();
        	if(purReceiveEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	scmPurReceiveBiz.doEditPurReceive(purReceiveEditParams, ParamHelper.createParam(integratedRequest,"doEditPurReceive"));
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("收货单修改失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
//	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doDelPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="删除收货单", consumes="application/json")
    public PurReceiveDelResultApi doDelPurReceive(@RequestBody PurReceiveDelParamsApi params) {
    	PurReceiveDelResultApi result = new PurReceiveDelResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveDelParams purReceiveDelParams = params.getParams();
	        if(purReceiveDelParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveDelParams.getPvNo());
	        scmPurReceiveBiz.doDelPurReceive(scmPurReceive, ParamHelper.createParam(integratedRequest,"doDelPurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单删除失败"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="提交收货单", consumes="application/json")
    public PurReceiveSubmitResultApi doSubmitPurReceive(@RequestBody PurReceiveSubmitParamsApi params) {
    	PurReceiveSubmitResultApi result = new PurReceiveSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveSubmitParams purReceiveSubmitParams = params.getParams();
	        if(purReceiveSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveSubmitParams.getPvNo());
	        scmPurReceiveBiz.doSubmitPurReceive(scmPurReceive, ParamHelper.createParam(integratedRequest,"doSubmitPurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单提交失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="取消提交收货单", consumes="application/json")
    public PurReceiveUnSubmitResultApi doUnSubmitPurReceive(@RequestBody PurReceiveUnSubmitParamsApi params) {
    	PurReceiveUnSubmitResultApi result = new PurReceiveUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveUnSubmitParams purReceiveUnSubmitParams = params.getParams();
	        if(purReceiveUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveUnSubmitParams.getPvNo());
	        scmPurReceiveBiz.doUnSubmitPurReceive(scmPurReceive, ParamHelper.createParam(integratedRequest,"doUnSubmitPurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单取消提交失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurRecPurOrgUnit", method=RequestMethod.POST)
    @ApiOperation(value="获取收货采购组织列表", consumes="application/json",position=4)
    public PurRecPurOrgUnitResultApi queryPurRecPurOrgUnit(@RequestBody PurRecPurOrgUnitParamsApi params) {
		PurRecPurOrgUnitResultApi result = new PurRecPurOrgUnitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRecPurOrgUnitParams purRecPurOrgUnitParams = params.getParams();
        	if(purRecPurOrgUnitParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<OrgPurchase2> orgPurchaseList = scmPurReceiveBiz.queryPurRecPurOrgUnit(purRecPurOrgUnitParams, ParamHelper.createParam(integratedRequest,"queryPurRecPurOrgUnit"));
    	    	if(orgPurchaseList!=null && !orgPurchaseList.isEmpty()){
    	    		List<PurRecPurOrgUnitResult> resultList = new ArrayList<>();
    	    		for(OrgPurchase2 orgPurchase:orgPurchaseList){
    	    			PurRecPurOrgUnitResult purRecPurOrgUnitResult = new PurRecPurOrgUnitResult();
    	    			purRecPurOrgUnitResult.setPurOrgUnitNo(orgPurchase.getOrgUnitNo());
    	    			purRecPurOrgUnitResult.setPurOrgUnitName(orgPurchase.getOrgUnitName());
    	    			resultList.add(purRecPurOrgUnitResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货采购组织失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurRecSupplierList", method=RequestMethod.POST)
    @ApiOperation(value="获取收货供应商列表", consumes="application/json",position=4)
    public PurRecSupplierListResultApi queryPurRecSupplierList(@RequestBody PurRecSupplierListParamsApi params) {
		PurRecSupplierListResultApi result = new PurRecSupplierListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRecSupplierListParams purRecSupplierListParams = params.getParams();
        	if(purRecSupplierListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmsupplierAdvQuery scmsupplierAdvQuery = new ScmsupplierAdvQuery();
    	    	BeanUtils.copyProperties(purRecSupplierListParams, scmsupplierAdvQuery);
    	    	scmsupplierAdvQuery.setMixParam(purRecSupplierListParams.getVendorName());
    	    	scmsupplierAdvQuery.setVendorName(null);
    	    	List<Scmsupplier2> scmsupplierList = scmsupplierBiz.querySupplierList(scmsupplierAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurRecSupplierList"));
    	    	if(scmsupplierList!=null && !scmsupplierList.isEmpty()){
    	    		List<PurRecSupplierListResult> resultList = new ArrayList<>();
    	    		for(Scmsupplier2 scmsupplier:scmsupplierList){
    	    			PurRecSupplierListResult purRecSupplierListResult = new PurRecSupplierListResult();
    	    			BeanUtils.copyProperties(scmsupplier,purRecSupplierListResult);
    	    			purRecSupplierListResult.setVendorCode(scmsupplier.getVendorNo());
    	    			resultList.add(purRecSupplierListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货供应商失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReceiverList", method=RequestMethod.POST)
    @ApiOperation(value="获取收货人列表", consumes="application/json",position=4)
    public PurReceiverListResultApi queryPurReceiverList(@RequestBody PurReceiverListParamsApi params) {
		PurReceiverListResultApi result = new PurReceiverListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiverListParams purReceiverListParams = params.getParams();
        	if(purReceiverListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<Usr2> employeeList = scmPurReceiveBiz.queryPurReceiverList(purReceiverListParams,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurReceiverList"));
    	    	if(employeeList!=null && !employeeList.isEmpty()){
    	    		List<PurReceiverListResult> resultList = new ArrayList<>();
    	    		for(Usr2 employee:employeeList){
    	    			PurReceiverListResult purReceiverListResult = new PurReceiverListResult();
    	    			purReceiverListResult.setReceiver(employee.getCode());
    	    			purReceiverListResult.setReceiverName(employee.getName());
    	    			resultList.add(purReceiverListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货人列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurRecRateList", method=RequestMethod.POST)
    @ApiOperation(value="获取收货人列表", consumes="application/json",position=4)
    public PurRecRateListResultApi queryPurRecRateList(@RequestBody PurRecRateListParamsApi params) {
		PurRecRateListResultApi result = new PurRecRateListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

        try {
        	SecurityUtils.verify(integratedRequest);
	    	List<PubSysBasicInfo> pubSysBasicInfoList = pubSysBasicInfoBiz.queryPurRecRateList(ParamHelper.createParam(integratedRequest,"queryPurRecRateList"));
	    	if(pubSysBasicInfoList!=null && !pubSysBasicInfoList.isEmpty()){
	    		List<PurRecRateListResult> resultList = new ArrayList<>();
	    		for(PubSysBasicInfo pubSysBasicInfo:pubSysBasicInfoList){
	    			PurRecRateListResult purRecRateListResult = new PurRecRateListResult();
	    			purRecRateListResult.setCode(pubSysBasicInfo.getFInfoNo());
	    			purRecRateListResult.setName(pubSysBasicInfo.getFInfoName());
	    			purRecRateListResult.setValues(new BigDecimal(pubSysBasicInfo.getFValue()));
	    			resultList.add(purRecRateListResult);
	    		}
	    		result.setResultList(resultList);
	    	}
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货人列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReceiveDept", method=RequestMethod.POST)
    @ApiOperation(value="获取收货部门列表", consumes="application/json",position=4)
    public PurReceiveDeptResultApi queryPurReceiveDept(@RequestBody PurReceiveDeptParamsApi params) {
		PurReceiveDeptResultApi result = new PurReceiveDeptResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveDeptParams purReceiveDeptParams = params.getParams();
        	if(purReceiveDeptParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<OrgAdmin2> orgAdminList = scmPurReceiveBiz.queryPurReceiveDept(purReceiveDeptParams.getInvOrgUnitNo(), pageIndex,ParamHelper.createParam(integratedRequest,"queryPurRecPurOrgUnit"));
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<PurReceiveDeptResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			PurReceiveDeptResult purReceiveDeptResult = new PurReceiveDeptResult();
    	    			purReceiveDeptResult.setDeptNo(orgAdmin.getOrgUnitNo());
    	    			purReceiveDeptResult.setDeptName(orgAdmin.getOrgUnitName());
    	    			resultList.add(purReceiveDeptResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货部门列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReceiveWareHouses", method=RequestMethod.POST)
    @ApiOperation(value="获取收货仓库列表", consumes="application/json",position=4)
    public PurReceiveWareHousesResultApi queryPurReceiveWareHouses(@RequestBody PurReceiveWareHousesParamsApi params) {
		PurReceiveWareHousesResultApi result = new PurReceiveWareHousesResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveWareHousesParams purReceiveWareHousesParams = params.getParams();
        	if(purReceiveWareHousesParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<ScmInvWareHouse> scmInvWareHouseList = scmPurReceiveBiz.queryPurReceiveWareHouses(purReceiveWareHousesParams.getInvOrgUnitNo(),pageIndex, ParamHelper.createParam(integratedRequest,"queryPurRecPurOrgUnit"));
    	    	if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
    	    		List<PurReceiveWareHousesResult> resultList = new ArrayList<>();
    	    		for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
    	    			PurReceiveWareHousesResult purReceiveWareHousesResult = new PurReceiveWareHousesResult();
    	    			purReceiveWareHousesResult.setWhNo(scmInvWareHouse.getWhNo());
    	    			purReceiveWareHousesResult.setWhName(scmInvWareHouse.getWhName());
    	    			resultList.add(purReceiveWareHousesResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货仓库列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="审批收货单", consumes="application/json",position=12)
    public PurReceiveAuditResultApi doAuditPurReceive(@RequestBody PurReceiveAuditParamsApi params) {
    	PurReceiveAuditResultApi result = new PurReceiveAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveAuditParams purReceiveAuditParams = params.getParams();
	        if(purReceiveAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purReceiveAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purReceiveAuditParams.getPvNo());
	        scmPurReceiveBiz.doAuditPurReceive(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditPurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurReceive", method=RequestMethod.POST)
    @ApiOperation(value="取消审批收货单", consumes="application/json",position=13)
    public PurReceiveUnAuditResultApi doUnAuditPurReceive(@RequestBody PurReceiveUnAuditParamsApi params) {
    	PurReceiveUnAuditResultApi result = new PurReceiveUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveUnAuditParams purReceiveUnAuditParams = params.getParams();
	        if(purReceiveUnAuditParams == null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveUnAuditParams.getPvNo());
	        scmPurReceiveBiz.doUnAuditPurReceive(scmPurReceive, ParamHelper.createParam(integratedRequest,"doUnAuditPurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/mobileGenerateWrReceipts", method=RequestMethod.POST)
    @ApiOperation(value="生成入库单", consumes="application/json",position=14)
	public PurReceiveUnAuditResultApi mobileGenerateWrReceipts(@RequestBody PurReceiveGenerateWrReceiptsParamsApi params) {
		PurReceiveUnAuditResultApi result = new PurReceiveUnAuditResultApi();//显示的结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveGenerateWrReceiptsParams purReceiveGenerateWrReceiptsParams = params.getParams();
        	if(purReceiveGenerateWrReceiptsParams == null){
  	          throw new AppException("webservice.params.null");
  	        }
        	String pvNos = purReceiveGenerateWrReceiptsParams.getPvNo();
        	scmPurReceiveBiz.mobileGenerateWrReceipts(pvNos,ParamHelper.createParam(integratedRequest,"mobileGenerateWrReceipts"));
		} catch (Exception e) {
			log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("生成入库单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
		return result;
	}

	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryDeptReceiveList", method=RequestMethod.POST)
    @ApiOperation(value="获取部门收货单列表", consumes="application/json",position=1)
    public PurReceiveListResultApi queryDeptReceiveList(@RequestBody PurReceiveListParamsApi params) {
    	PurReceiveListResultApi result = new PurReceiveListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveListParams purReceiveListParams = params.getParams();
	        if(purReceiveListParams
	        		==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceiveAdvQuery scmPurReceiveAdvQuery = new ScmPurReceiveAdvQuery();
	        BeanUtils.copyProperties(purReceiveListParams, scmPurReceiveAdvQuery);
	        scmPurReceiveAdvQuery.setFromInterface(true);
	        List<ScmPurReceive2> scmPurReceiveList = scmDeptReceiveBiz.queryPurReceiveList(scmPurReceiveAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurReceiveList"));
	        if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
	        	List<PurReceiveListResult> resultList = new ArrayList<PurReceiveListResult>();
	        	for(ScmPurReceive2 scmPurReceive:scmPurReceiveList){
	        		PurReceiveListResult purReceiveListResult = new PurReceiveListResult();
	        		BeanUtils.copyProperties(scmPurReceive, purReceiveListResult);
	        		resultList.add(purReceiveListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取收货单列表:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doReleasePurReceive", method=RequestMethod.POST)
    @ApiOperation(value="下达收货单", consumes="application/json")
    public PurReceiveSubmitResultApi doReleasePurReceive(@RequestBody PurReceiveSubmitParamsApi params) {
    	PurReceiveSubmitResultApi result = new PurReceiveSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReceiveSubmitParams purReceiveSubmitParams = params.getParams();
	        if(purReceiveSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurReceive2 scmPurReceive= new ScmPurReceive2();
	        scmPurReceive.setPvNo(purReceiveSubmitParams.getPvNo());
	        scmPurReceiveBiz.doRelease(scmPurReceive, ParamHelper.createParam(integratedRequest,"doReleasePurReceive"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("收货单下达失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
