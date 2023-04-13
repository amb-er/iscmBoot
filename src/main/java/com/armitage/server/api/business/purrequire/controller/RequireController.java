package com.armitage.server.api.business.purrequire.controller;

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

import com.armitage.server.api.business.purrequire.params.PurReqMaterialListParams;
import com.armitage.server.api.business.purrequire.params.PurReqMaterialListParamsApi;
import com.armitage.server.api.business.purrequire.params.PurReqMaterialPriceParams;
import com.armitage.server.api.business.purrequire.params.PurReqMaterialPriceParamsApi;
import com.armitage.server.api.business.purrequire.params.PurReqOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqOrgUnitParamsApi;
import com.armitage.server.api.business.purrequire.params.PurReqPersonParams;
import com.armitage.server.api.business.purrequire.params.PurReqPersonParamsApi;
import com.armitage.server.api.business.purrequire.params.PurReqPurOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqPurOrgUnitParamsApi;
import com.armitage.server.api.business.purrequire.params.PurReqWareHouseParams;
import com.armitage.server.api.business.purrequire.params.PurReqWareHouseParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireAddParams;
import com.armitage.server.api.business.purrequire.params.PurRequireAddParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireAuditDetailParams;
import com.armitage.server.api.business.purrequire.params.PurRequireAuditParams;
import com.armitage.server.api.business.purrequire.params.PurRequireAuditParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireDelParams;
import com.armitage.server.api.business.purrequire.params.PurRequireDelParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireEditParams;
import com.armitage.server.api.business.purrequire.params.PurRequireEditParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireListParams;
import com.armitage.server.api.business.purrequire.params.PurRequireListParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireParams;
import com.armitage.server.api.business.purrequire.params.PurRequireParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireSubmitParams;
import com.armitage.server.api.business.purrequire.params.PurRequireSubmitParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireTemplateListParams;
import com.armitage.server.api.business.purrequire.params.PurRequireTemplateListParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireTemplateParams;
import com.armitage.server.api.business.purrequire.params.PurRequireTemplateParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireUnAuditParams;
import com.armitage.server.api.business.purrequire.params.PurRequireUnAuditParamsApi;
import com.armitage.server.api.business.purrequire.params.PurRequireUnSubmitParams;
import com.armitage.server.api.business.purrequire.params.PurRequireUnSubmitParamsApi;
import com.armitage.server.api.business.purrequire.params.PurchaseTypeParams;
import com.armitage.server.api.business.purrequire.params.PurchaseTypeParamsApi;
import com.armitage.server.api.business.purrequire.result.PurReqMaterialListResult;
import com.armitage.server.api.business.purrequire.result.PurReqMaterialListResultApi;
import com.armitage.server.api.business.purrequire.result.PurReqMaterialPriceResult;
import com.armitage.server.api.business.purrequire.result.PurReqMaterialPriceResultApi;
import com.armitage.server.api.business.purrequire.result.PurReqOrgUnitResult;
import com.armitage.server.api.business.purrequire.result.PurReqOrgUnitResultApi;
import com.armitage.server.api.business.purrequire.result.PurReqPersonResult;
import com.armitage.server.api.business.purrequire.result.PurReqPersonResultApi;
import com.armitage.server.api.business.purrequire.result.PurReqPurOrgUnitResult;
import com.armitage.server.api.business.purrequire.result.PurReqPurOrgUnitResultApi;
import com.armitage.server.api.business.purrequire.result.PurReqWareHouseResult;
import com.armitage.server.api.business.purrequire.result.PurReqWareHouseResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireAddResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireAuditResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireDelResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireDetailResult;
import com.armitage.server.api.business.purrequire.result.PurRequireEditResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireListResult;
import com.armitage.server.api.business.purrequire.result.PurRequireListResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireResult;
import com.armitage.server.api.business.purrequire.result.PurRequireResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireSubmitResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireUnAuditResultApi;
import com.armitage.server.api.business.purrequire.result.PurRequireUnSubmitResultApi;
import com.armitage.server.api.business.purrequire.result.PurchaseTypeResult;
import com.armitage.server.api.business.purrequire.result.PurchaseTypeResultApi;
import com.armitage.server.api.business.purrequire.result.ScmAuditDetailHistoryResult;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.system.model.Employee2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/purRequire")
@Api(tags="请购业务接口")
public class RequireController {
	private static Log log = LogFactory.getLog(RequireController.class);
    private ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
    private ScmMaterialPriceBiz scmMaterialPriceBiz = (ScmMaterialPriceBiz) AppContextUtil.getBean("scmMaterialPriceBiz");
    private OrgUnitRelationBiz orgUnitRelationBiz = (OrgUnitRelationBiz) AppContextUtil.getBean("orgUnitRelationBiz");
    private SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
    private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doDelPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="删除请购单", consumes="application/json",position=5)
    public PurRequireDelResultApi doDelPurRequire(@RequestBody PurRequireDelParamsApi params) {
    	PurRequireDelResultApi result = new PurRequireDelResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireDelParams purRequireDelParams = params.getParams();
	        if(purRequireDelParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireDelParams.getPrNo());
	        scmPurRequireBiz.doDelPurRequire(scmPurRequire, ParamHelper.createParam(integratedRequest,"scmPurRequire"));  
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("请购单删除失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="提交请购单", consumes="application/json",position=6)
    public PurRequireSubmitResultApi doSubmitPurRequire(@RequestBody PurRequireSubmitParamsApi params) {
    	PurRequireSubmitResultApi result = new PurRequireSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireSubmitParams purRequireSubmitParams = params.getParams();
	        if(purRequireSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireSubmitParams.getPrNo());
	        scmPurRequireBiz.doSubmitPurRequire(scmPurRequire, ParamHelper.createParam(integratedRequest,"scmPurRequire"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("请购单提交失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="取消提交请购单", consumes="application/json",position=7)
    public PurRequireUnSubmitResultApi doUnSubmitPurRequire(@RequestBody PurRequireUnSubmitParamsApi params) {
    	PurRequireUnSubmitResultApi result = new PurRequireUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireUnSubmitParams purRequireUnSubmitParams = params.getParams();
	        if(purRequireUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireUnSubmitParams.getPrNo());
	        scmPurRequireBiz.doUnSubmitPurRequire(scmPurRequire, ParamHelper.createParam(integratedRequest,"scmPurRequire"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("请购单取消提交失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurRequireList", method=RequestMethod.POST)
    @ApiOperation(value="获取请购单列表", consumes="application/json",position=1)
    public PurRequireListResultApi queryPurRequireList(@RequestBody PurRequireListParamsApi params) {
    	PurRequireListResultApi result = new PurRequireListResultApi();//显示的结果集
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
        	PurRequireListParams purRequireListParams = params.getParams();
	        if(purRequireListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequireAdvQuery scmPurRequireAdvQuery = new ScmPurRequireAdvQuery();
	        BeanUtils.copyProperties(purRequireListParams, scmPurRequireAdvQuery);
	        scmPurRequireAdvQuery.setFromInterface(true);
	        scmPurRequireAdvQuery.setTemplete(false);
	        List<ScmPurRequire2> scmPurRequireList = scmPurRequireBiz.queryPurRequireList(scmPurRequireAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurRequireList"));
	        if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
	        	List< PurRequireListResult > resultList = new ArrayList<PurRequireListResult>();
	        	for(ScmPurRequire2 scmPurRequire:scmPurRequireList){
	        		PurRequireListResult purRequireListResult = new PurRequireListResult();
	        		BeanUtils.copyProperties(scmPurRequire, purRequireListResult);
	        		resultList.add(purRequireListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取请购单列表失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="获取请购单详情", consumes="application/json",position=2)
    public PurRequireResultApi queryPurRequire(@RequestBody PurRequireParamsApi params) {
    	PurRequireResultApi result = new PurRequireResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireParams purRequireParams = params.getParams();
	        if(purRequireParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireParams.getPrNo());
	        ScmPurRequire2 rtn = scmPurRequireBiz.queryPurRequire(scmPurRequire, ParamHelper.createParam(integratedRequest,"queryPurRequire"));
	        if(rtn!=null){
	        	PurRequireResult purRequireResult = new PurRequireResult();
	        	BeanUtils.copyProperties(rtn, purRequireResult);
	        	purRequireResult.setEditorDate(rtn.getEditDate());
	        	purRequireResult.setReqOrgUnitNo(rtn.getOrgUnitNo());
	        	List<ScmPurRequireEntry2> scmPurRequireEntryList = rtn.getScmPurRequireEntryList();
	        	if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()){
	        		List<PurRequireDetailResult> detailList = new ArrayList<PurRequireDetailResult>();
	        		for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList){
	        			PurRequireDetailResult purRequireDetailResult = new PurRequireDetailResult();
	        			BeanUtils.copyProperties(scmPurRequireEntry, purRequireDetailResult);
	        			purRequireDetailResult.setPurUnit(scmPurRequireEntry.getPurUnitName());
	        			purRequireDetailResult.setVendorCode(scmPurRequireEntry.getVendorNo());
	        			if(scmPurRequireEntry.getAuditDetailHistoryList() != null && !scmPurRequireEntry.getAuditDetailHistoryList().isEmpty()){
	        				List<ScmAuditDetailHistoryResult> auditDetailHistoryResultList = new ArrayList<ScmAuditDetailHistoryResult>();
	        				for(ScmAuditDetailHistory2 scmAuditDetailHistory : scmPurRequireEntry.getAuditDetailHistoryList()){
	        					ScmAuditDetailHistoryResult auditDetailHistoryResult = new ScmAuditDetailHistoryResult();
	        					BeanUtils.copyProperties(scmAuditDetailHistory, auditDetailHistoryResult);
	        					auditDetailHistoryResultList.add(auditDetailHistoryResult);
	        				}
	        				purRequireDetailResult.setAuditDetailHistoryResultList(auditDetailHistoryResultList);
	        			}
	        			detailList.add(purRequireDetailResult);
	        		}
	        		purRequireResult.setDetailList(detailList);
	        	}
	        	result.setResult(purRequireResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取请购单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="新增申购单", consumes="application/json",position=3)
    public PurRequireAddResultApi doAddPurRequire(@RequestBody PurRequireAddParamsApi params) {
		PurRequireAddResultApi result = new PurRequireAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireAddParams purRequireAddParams = params.getParams();
        	if(purRequireAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurRequire2 scmPurRequire2=scmPurRequireBiz.doAddPurRequire(purRequireAddParams, ParamHelper.createParam(integratedRequest,"purPriceAddParams"));
    		    if(scmPurRequire2!=null){
    		    	result.setPrNo(scmPurRequire2.getPrNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("申购单新增失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doEditPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="修改请购单", consumes="application/json",position=4)
    public PurRequireEditResultApi doEditPurRequire(@RequestBody PurRequireEditParamsApi params) {
		PurRequireEditResultApi result = new PurRequireEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireEditParams purRequireEditParams = params.getParams();
        	if(purRequireEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	scmPurRequireBiz.doEditPurRequire(purRequireEditParams, ParamHelper.createParam(integratedRequest,"purPriceEditParams"));
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("请购单修改失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqPurOrgUnit", method=RequestMethod.POST)
    @ApiOperation(value="获取采购组织列表", consumes="application/json",position=4)
    public PurReqPurOrgUnitResultApi queryPurReqPurOrgUnit(@RequestBody PurReqPurOrgUnitParamsApi params) {
		PurReqPurOrgUnitResultApi result = new PurReqPurOrgUnitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReqPurOrgUnitParams purReqPurOrgUnitParams = params.getParams();
        	if(purReqPurOrgUnitParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(purReqPurOrgUnitParams.getReqOrgUnitNo())){
    	    		throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.reqOrgUnitNo.isnull");
    	    	}
    	    	List<OrgPurchase2> orgPurchaseList = scmPurRequireBiz.queryPurReqPurOrgUnit(purReqPurOrgUnitParams, ParamHelper.createParam(integratedRequest,"queryPurReqPurOrgUnit"));
    	    	if(orgPurchaseList!=null && !orgPurchaseList.isEmpty()){
    	    		List<PurReqPurOrgUnitResult> resultList = new ArrayList<>();
    	    		for(OrgPurchase2 orgPurchase:orgPurchaseList){
    	    			PurReqPurOrgUnitResult purReqPurOrgUnitResult = new PurReqPurOrgUnitResult();
    	    			purReqPurOrgUnitResult.setPurOrgUnitNo(orgPurchase.getOrgUnitNo());
    	    			purReqPurOrgUnitResult.setPurOrgUnitName(orgPurchase.getOrgUnitName());
    	    			resultList.add(purReqPurOrgUnitResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取采购组织失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="获取可申购物资列表", consumes="application/json",position=4)
    public PurReqMaterialListResultApi queryPurReqMaterialList(@RequestBody PurReqMaterialListParamsApi params) {
		PurReqMaterialListResultApi result = new PurReqMaterialListResultApi();//显示的结果集
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
        	PurReqMaterialListParams purReqMaterialListParams = params.getParams();
        	if(purReqMaterialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(purReqMaterialListParams.getPurOrgUnitNo())){
    	    		throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.purOrgUnitNo.isnull");
    	    	}
    	    	List<ScmMaterial2> scmMaterialList = scmPurRequireBiz.queryPurReqMaterialList(purReqMaterialListParams, pageIndex,ParamHelper.createParam(integratedRequest,"queryPurReqPurOrgUnit"));
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<PurReqMaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			PurReqMaterialListResult purReqMaterialListResult = new PurReqMaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, purReqMaterialListResult);
    	    			resultList.add(purReqMaterialListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取可申购物资列表失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqOrgUnit", method=RequestMethod.POST)
    @ApiOperation(value="获取申购组织列表", consumes="application/json",position=4)
    public PurReqOrgUnitResultApi queryPurReqOrgUnit(@RequestBody PurReqOrgUnitParamsApi params) {
		PurReqOrgUnitResultApi result = new PurReqOrgUnitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReqOrgUnitParams purReqOrgUnitParams = params.getParams();
        	if(purReqOrgUnitParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<OrgAdmin2> orgAdminList = scmPurRequireBiz.queryPurReqOrgUnit(purReqOrgUnitParams, ParamHelper.createParam(integratedRequest,"queryPurReqOrgUnit"));
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<PurReqOrgUnitResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			PurReqOrgUnitResult purReqOrgUnitResult = new PurReqOrgUnitResult();
    	    			BeanUtils.copyProperties(orgAdmin,purReqOrgUnitResult);
    	    			resultList.add(purReqOrgUnitResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取申购组织失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqPerson", method=RequestMethod.POST)
    @ApiOperation(value="获取申请人列表", consumes="application/json",position=4)
    public PurReqPersonResultApi queryPurReqPerson(@RequestBody PurReqPersonParamsApi params) {
		PurReqPersonResultApi result = new PurReqPersonResultApi();//显示的结果集
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
        	PurReqPersonParams purReqPersonParams = params.getParams();
        	if(purReqPersonParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<Employee2> employeeList = scmPurRequireBiz.queryPurReqPerson(purReqPersonParams,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurReqPerson"));
    	    	if(employeeList!=null && !employeeList.isEmpty()){
    	    		List<PurReqPersonResult> resultList = new ArrayList<>();
    	    		for(Employee2 employee:employeeList){
    	    			PurReqPersonResult purReqPersonResult = new PurReqPersonResult();
    	    			BeanUtils.copyProperties(employee,purReqPersonResult);
    	    			resultList.add(purReqPersonResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取申请人失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqWareHouse", method=RequestMethod.POST)
    @ApiOperation(value="获取存货仓库列表", consumes="application/json",position=4)
    public PurReqWareHouseResultApi queryPurReqWareHouse(@RequestBody PurReqWareHouseParamsApi params) {
		PurReqWareHouseResultApi result = new PurReqWareHouseResultApi();//显示的结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReqWareHouseParams purReqWareHouseParams = params.getParams();
        	if(purReqWareHouseParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<ScmInvWareHouse> scmInvWareHouseList = scmPurRequireBiz.queryPurReqWareHouse(purReqWareHouseParams,ParamHelper.createParam(integratedRequest,"queryPurReqWareHouse"));
    	    	if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
    	    		List<PurReqWareHouseResult> resultList = new ArrayList<>();
    	    		for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
    	    			PurReqWareHouseResult purReqWareHouseResult = new PurReqWareHouseResult();
    	    			purReqWareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
    	    			purReqWareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
    	    			resultList.add(purReqWareHouseResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取存货仓库失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurReqMaterialPrice", method=RequestMethod.POST)
    @ApiOperation(value="查询申购物资价格", consumes="application/json",position=2)
    public PurReqMaterialPriceResultApi queryPurReqMaterialPrice(@RequestBody PurReqMaterialPriceParamsApi params) {
		PurReqMaterialPriceResultApi result = new PurReqMaterialPriceResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurReqMaterialPriceParams purReqMaterialPriceParams = params.getParams();
	        if(purReqMaterialPriceParams==null){
	          throw new AppException("webservice.params.null");
	        }
            ScmPurPriceQuery scmPurPriceQuery= new ScmPurPriceQuery();
	        BeanUtils.copyProperties(purReqMaterialPriceParams, scmPurPriceQuery);
	        Param param = ParamHelper.createParam(integratedRequest,"queryPurReqMaterialPrice");
	        scmPurPriceQuery.setBizDate(purReqMaterialPriceParams.getApplyDate());
	        if(purReqMaterialPriceParams.getReqDate()!=null) {
	        	String priceType = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_SelectType", "1", param);
	        	if(StringUtils.equals("2", priceType))
	        		scmPurPriceQuery.setBizDate(purReqMaterialPriceParams.getReqDate());
	        }
	        if (StringUtils.isNotBlank(purReqMaterialPriceParams.getVendorCode())) {
				Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectByCode(purReqMaterialPriceParams.getVendorCode(), param);
				if (scmsupplier2 != null) {
					scmPurPriceQuery.setVendorId(scmsupplier2.getId());
				}
	        }
	        ScmMaterialPrice scmMaterialPrice = scmPurRequireBiz.queryPurReqMaterialPrice(scmPurPriceQuery, param);
	        if(scmMaterialPrice!=null){
	        	PurReqMaterialPriceResult purReqMaterialPriceResult = new PurReqMaterialPriceResult();
	        	BeanUtils.copyProperties(scmMaterialPrice, purReqMaterialPriceResult);
	    		String invOrgUnitNo = "0";
	    		List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, purReqMaterialPriceParams.getReqOrgUnitNo(), false, null, param);
	    		if(orgStorageList!=null && !orgStorageList.isEmpty()) {
	    			boolean exists=false;
	                for(OrgStorage2 orgStorage:orgStorageList){
	                    if(orgStorage.isDefault()){
	                        invOrgUnitNo = orgStorage.getOrgUnitNo();
	                        exists=true;
	                        break;
	                    }
	                }
	                if(!exists)
	                    invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
	    		}
		        ScmInvPriceQuery scmInvPriceQuery = new ScmInvPriceQuery();
		        scmInvPriceQuery.setInvOrgUnitNo(invOrgUnitNo);
		        scmInvPriceQuery.setItemNo(purReqMaterialPriceParams.getItemNo());
		        ScmMaterialPrice invScmMaterialPrice = scmMaterialPriceBiz.getRecentPriceAndStock(scmInvPriceQuery, param);
	        	purReqMaterialPriceResult.setStockQty(invScmMaterialPrice==null || invScmMaterialPrice.getInvQty()==null?BigDecimal.ZERO:invScmMaterialPrice.getInvQty());
	        	purReqMaterialPriceResult.setRecentPrice(invScmMaterialPrice==null || invScmMaterialPrice.getPrice()==null?BigDecimal.ZERO:invScmMaterialPrice.getPrice());
	        	result.setResult(purReqMaterialPriceResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取物资价格失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="审批请购单", consumes="application/json",position=14)
    public PurRequireAuditResultApi doAuditPurRequire(@RequestBody PurRequireAuditParamsApi params) {
    	PurRequireAuditResultApi result = new PurRequireAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireAuditParams purRequireAuditParams = params.getParams();
	        if(purRequireAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purRequireAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purRequireAuditParams.getPrNo());
	        if(purRequireAuditParams.getDetailList()!=null && !purRequireAuditParams.getDetailList().isEmpty()) {
	        	List<CommonAuditDetailParams> detailList = new ArrayList();
	        	for(PurRequireAuditDetailParams purRequireAuditDetailParams:purRequireAuditParams.getDetailList()) {
	        		CommonAuditDetailParams commonAuditDetailParams = new CommonAuditDetailParams();
	        		BeanUtils.copyProperties(purRequireAuditDetailParams,commonAuditDetailParams);
	        		detailList.add(commonAuditDetailParams);
	        	}
	        	commonAuditParams.setDetailList(detailList);
	        }
	        scmPurRequireBiz.doAuditPurRequire(commonAuditParams, ParamHelper.createParam(integratedRequest,"scmPurRequire"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("请购单审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurRequire", method=RequestMethod.POST)
    @ApiOperation(value="反审批请购单", consumes="application/json",position=15)
    public PurRequireUnAuditResultApi doUnAuditPurRequire(@RequestBody PurRequireUnAuditParamsApi params) {
    	PurRequireUnAuditResultApi result = new PurRequireUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireUnAuditParams purRequireUnAuditParams = params.getParams();
	        if(purRequireUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireUnAuditParams.getPrNo());
	        scmPurRequireBiz.doUnAuditPurRequire(scmPurRequire, ParamHelper.createParam(integratedRequest,"doUnAuditPurRequire"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("请购单审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurchaseType", method=RequestMethod.POST)
    @ApiOperation(value="获取采购类型列表", consumes="application/json",position=16)
    public PurchaseTypeResultApi queryPurchaseType(@RequestBody PurchaseTypeParamsApi params) {
		PurchaseTypeResultApi result = new PurchaseTypeResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurchaseTypeParams purchaseTypeParams = params.getParams();
        	if(purchaseTypeParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<ScmPurchaseType2> scmPurchaseTypeList = scmPurRequireBiz.queryPurchaseType(purchaseTypeParams, ParamHelper.createParam(integratedRequest,"queryPurchaseType"));
    	    	if(scmPurchaseTypeList!=null && !scmPurchaseTypeList.isEmpty()){
    	    		List<PurchaseTypeResult> resultList = new ArrayList<>();
    	    		for(ScmPurchaseType2 scmPurchaseType:scmPurchaseTypeList){
    	    			PurchaseTypeResult purchaseTypeResult = new PurchaseTypeResult();
    	    			BeanUtils.copyProperties(scmPurchaseType,purchaseTypeResult);
    	    			resultList.add(purchaseTypeResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取采购类型列表："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryTempleteList", method=RequestMethod.POST)
    @ApiOperation(value="获取请购模板列表", consumes="application/json",position=1)
    public PurRequireListResultApi queryTempleteList(@RequestBody PurRequireTemplateListParamsApi params) {
    	PurRequireListResultApi result = new PurRequireListResultApi();//显示的结果集
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
        	PurRequireTemplateListParams purRequireListParams = params.getParams();
	        if(purRequireListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequireAdvQuery scmPurRequireAdvQuery = new ScmPurRequireAdvQuery();
	        BeanUtils.copyProperties(purRequireListParams, scmPurRequireAdvQuery);
	        scmPurRequireAdvQuery.setFromInterface(true);
	        scmPurRequireAdvQuery.setTemplete(true);
	        List<ScmPurRequire2> scmPurRequireList = scmPurRequireBiz.queryPurRequireList(scmPurRequireAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurRequireList"));
	        if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
	        	List<PurRequireListResult> resultList = new ArrayList<PurRequireListResult>();
	        	for(ScmPurRequire2 scmPurRequire:scmPurRequireList){
	        		PurRequireListResult purRequireListResult = new PurRequireListResult();
	        		BeanUtils.copyProperties(scmPurRequire, purRequireListResult);
	        		resultList.add(purRequireListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取请购模板列表："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryTempleteDetail", method=RequestMethod.POST)
    @ApiOperation(value="获取模板详细信息", consumes="application/json",position=2)
    public PurRequireResultApi queryTempleteDetail(@RequestBody PurRequireTemplateParamsApi params) {
    	PurRequireResultApi result = new PurRequireResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurRequireTemplateParams purRequireParams = params.getParams();
	        if(purRequireParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
	        scmPurRequire.setPrNo(purRequireParams.getPrNo());
	        ScmPurRequire2 rtn = scmPurRequireBiz.queryTemplete(scmPurRequire, ParamHelper.createParam(integratedRequest,"queryTempleteDetail"));
	        if(rtn!=null){
	        	PurRequireResult purRequireResult = new PurRequireResult();
	        	BeanUtils.copyProperties(rtn, purRequireResult);
	        	purRequireResult.setReqOrgUnitNo(rtn.getOrgUnitNo());
	        	List<ScmPurRequireEntry2> scmPurRequireEntryList = rtn.getScmPurRequireEntryList();
	        	if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()){
	        		List<PurRequireDetailResult> detailList = new ArrayList<PurRequireDetailResult>();
	        		for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList){
	        			PurRequireDetailResult purRequireDetailResult = new PurRequireDetailResult();
	        			BeanUtils.copyProperties(scmPurRequireEntry, purRequireDetailResult);
	        			purRequireDetailResult.setPurUnit(scmPurRequireEntry.getPurUnitName());
	        			if(scmPurRequireEntry.getAuditDetailHistoryList() != null && !scmPurRequireEntry.getAuditDetailHistoryList().isEmpty()){
	        				List<ScmAuditDetailHistoryResult> auditDetailHistoryResultList = new ArrayList<ScmAuditDetailHistoryResult>();
	        				for(ScmAuditDetailHistory2 scmAuditDetailHistory : scmPurRequireEntry.getAuditDetailHistoryList()){
	        					ScmAuditDetailHistoryResult auditDetailHistoryResult = new ScmAuditDetailHistoryResult();
	        					BeanUtils.copyProperties(scmAuditDetailHistory, auditDetailHistoryResult);
	        					auditDetailHistoryResultList.add(auditDetailHistoryResult);
	        				}
	        				purRequireDetailResult.setAuditDetailHistoryResultList(auditDetailHistoryResultList);
	        			}
	        			detailList.add(purRequireDetailResult);
	        		}
	        		purRequireResult.setDetailList(detailList);
	        	}
	        	result.setResult(purRequireResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取模板详细信息失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
}
