package com.armitage.server.api.business.invmaterialreq.controller;

import com.armitage.server.api.business.purrequire.result.ScmAuditDetailHistoryResult;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
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

import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAddParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAddParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAuditDetailParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAuditParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqAuditParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDelParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDelParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDeptParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqDeptParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqEditParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqEditParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqInvOrgUnitParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqInvOrgUnitParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqListParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqListParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqPersonParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqPersonParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqReleaseParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqReleaseParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqSubmitParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqSubmitParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnAuditParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnAuditParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnReleaseParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnReleaseParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnSubmitParams;
import com.armitage.server.api.business.invmaterialreq.params.InvMaterialReqUnSubmitParamsApi;
import com.armitage.server.api.business.invmaterialreq.params.InvReqMaterialListParams;
import com.armitage.server.api.business.invmaterialreq.params.InvReqMaterialListParamsApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqAddResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqAuditResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqDelResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqDeptResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqDeptResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqDetailResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqEditResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqInvOrgUnitResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqInvOrgUnitResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqListResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqListResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqPersonResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqPersonResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqReleaseResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqResult;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqSubmitResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqUnAuditResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqUnReleaseResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvMaterialReqUnSubmitResultApi;
import com.armitage.server.api.business.invmaterialreq.result.InvReqMaterialListResult;
import com.armitage.server.api.business.invmaterialreq.result.InvReqMaterialListResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialRequestBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.user.model.Usr2;

@Controller
@RequestMapping("/api/invMaterialReq")
@Api(tags="领料申请接口")
public class InvMaterialReqController {
	private static Log log = LogFactory.getLog(InvMaterialReqController.class);
	private ScmInvMaterialRequestBillBiz scmInvMaterialRequestBillBiz = (ScmInvMaterialRequestBillBiz) AppContextUtil.getBean("scmInvMaterialRequestBillBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqList", method=RequestMethod.POST)
    @ApiOperation(value="获取领料申请单列表", consumes="application/json",position=1)
    public InvMaterialReqListResultApi queryInvMaterialReqList(@RequestBody InvMaterialReqListParamsApi params) {
		InvMaterialReqListResultApi result = new InvMaterialReqListResultApi();//显示的结果集
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
        	InvMaterialReqListParams invMaterialReqListParams = params.getParams();
	        if(invMaterialReqListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBillAdvQuery scmInvMaterialRequestBillAdvQuery = new ScmInvMaterialRequestBillAdvQuery();
	        BeanUtils.copyProperties(invMaterialReqListParams, scmInvMaterialRequestBillAdvQuery);
	        scmInvMaterialRequestBillAdvQuery.setFromInterface(true);
	        List<ScmInvMaterialRequestBill2> scmInvMaterialRequestBillList = scmInvMaterialRequestBillBiz.queryInvMaterialReqList(scmInvMaterialRequestBillAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryInvMaterialReqList"));
	        if(scmInvMaterialRequestBillList!=null && !scmInvMaterialRequestBillList.isEmpty()){
	        	List< InvMaterialReqListResult > resultList = new ArrayList<InvMaterialReqListResult>();
	        	for(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill:scmInvMaterialRequestBillList){
	        		InvMaterialReqListResult invMaterialReqListResult = new InvMaterialReqListResult();
	        		BeanUtils.copyProperties(scmInvMaterialRequestBill, invMaterialReqListResult);
	        		resultList.add(invMaterialReqListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取领料申请单列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="获取领料申请详情", consumes="application/json",position=2)
    public InvMaterialReqResultApi queryInvMaterialReq(@RequestBody InvMaterialReqParamsApi params) {
		InvMaterialReqResultApi result = new InvMaterialReqResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqParams invMaterialReqParams = params.getParams();
	        if(invMaterialReqParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqParams.getReqNo());
	        ScmInvMaterialRequestBill2 rtn = scmInvMaterialRequestBillBiz.queryInvMaterialReq(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"queryInvMaterialReq"));
	        if(rtn!=null){
	        	InvMaterialReqResult invMaterialReqResult = new InvMaterialReqResult();
	        	BeanUtils.copyProperties(rtn, invMaterialReqResult);
	        	invMaterialReqResult.setUseDeptNo(rtn.getOrgUnitNo());
	        	List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList = rtn.getScmInvMaterialRequestBillEntryList();
	        	if(scmInvMaterialRequestBillEntryList!=null && !scmInvMaterialRequestBillEntryList.isEmpty()){
	        		List< InvMaterialReqDetailResult > detailList = new ArrayList<InvMaterialReqDetailResult>();
	        		for(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry:scmInvMaterialRequestBillEntryList){
	        			InvMaterialReqDetailResult invMaterialReqDetailResult = new InvMaterialReqDetailResult();
	        			BeanUtils.copyProperties(scmInvMaterialRequestBillEntry, invMaterialReqDetailResult);
	        			if(scmInvMaterialRequestBillEntry.getAuditDetailHistoryList() != null && !scmInvMaterialRequestBillEntry.getAuditDetailHistoryList().isEmpty()){
	        				List< ScmAuditDetailHistoryResult > auditDetailHistoryResultList = new ArrayList<ScmAuditDetailHistoryResult>();
	        				for(ScmAuditDetailHistory2 scmAuditDetailHistory : scmInvMaterialRequestBillEntry.getAuditDetailHistoryList()){
	        					ScmAuditDetailHistoryResult auditDetailHistoryResult = new ScmAuditDetailHistoryResult();
	        					BeanUtils.copyProperties(scmAuditDetailHistory, auditDetailHistoryResult);
	        					auditDetailHistoryResultList.add(auditDetailHistoryResult);
	        				}
	        				invMaterialReqDetailResult.setAuditDetailHistoryResultList(auditDetailHistoryResultList);
	        			}
	        			detailList.add(invMaterialReqDetailResult);
	        		}
	        		invMaterialReqResult.setDetailList(detailList);
	        	}
	        	result.setResult(invMaterialReqResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取领料申请详情失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doDelInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="删除领料申请单", consumes="application/json",position=5)
    public InvMaterialReqDelResultApi doDelInvMaterialReq(@RequestBody InvMaterialReqDelParamsApi params) {
		InvMaterialReqDelResultApi result = new InvMaterialReqDelResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqDelParams invMaterialReqDelParams = params.getParams();
	        if(invMaterialReqDelParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqDelParams.getReqNo());
	        scmInvMaterialRequestBillBiz.doDelInvMaterialReq(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"doDelInvMaterialReq"));  
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料申请单删除失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="提交领料申请单", consumes="application/json",position=6)
    public InvMaterialReqSubmitResultApi doSubmitInvMaterialReq(@RequestBody InvMaterialReqSubmitParamsApi params) {
		InvMaterialReqSubmitResultApi result = new InvMaterialReqSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqSubmitParams invMaterialReqSubmitParams = params.getParams();
	        if(invMaterialReqSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqSubmitParams.getReqNo());
	        scmInvMaterialRequestBillBiz.doSubmit(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"doSubmitInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("提交领料申请单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="取消提交领料申请单", consumes="application/json",position=7)
    public InvMaterialReqUnSubmitResultApi doUnSubmitInvMaterialReq(@RequestBody InvMaterialReqUnSubmitParamsApi params) {
		InvMaterialReqUnSubmitResultApi result = new InvMaterialReqUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqUnSubmitParams invMaterialReqUnSubmitParams = params.getParams();
	        if(invMaterialReqUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqUnSubmitParams.getReqNo());
	        scmInvMaterialRequestBillBiz.doUnSubmit(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"doUnSubmitInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("取消提交领料申请单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="新增领料申请单", consumes="application/json",position=3)
    public InvMaterialReqAddResultApi doAddInvMaterialReq(@RequestBody InvMaterialReqAddParamsApi params) {
		InvMaterialReqAddResultApi result = new InvMaterialReqAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqAddParams invMaterialReqAddParams = params.getParams();
        	if(invMaterialReqAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(invMaterialReqAddParams.getDetailList() == null || invMaterialReqAddParams.getDetailList().isEmpty()){
    	    		throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
    	    	}
    	    	ScmInvMaterialRequestBill2 scmInvMaterialRequestBill=scmInvMaterialRequestBillBiz.doAddMaterialReq(invMaterialReqAddParams, ParamHelper.createParam(integratedRequest,"doAddInvMaterialReq"));
    		    if(scmInvMaterialRequestBill!=null){
    		    	result.setReqNo(scmInvMaterialRequestBill.getReqNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("领料申请单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doEditInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="修改领料申请单", consumes="application/json",position=4)
    public InvMaterialReqEditResultApi doEditInvMaterialReq(@RequestBody InvMaterialReqEditParamsApi params) {
		InvMaterialReqEditResultApi result = new InvMaterialReqEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqEditParams invMaterialReqEditParams = params.getParams();
        	if(invMaterialReqEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	scmInvMaterialRequestBillBiz.doEditMaterialReq(invMaterialReqEditParams, ParamHelper.createParam(integratedRequest,"doEditInvMaterialReq"));
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("领料申请单修改失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialInvOrgUnit", method=RequestMethod.POST)
    @ApiOperation(value="获取库存组织列表", consumes="application/json",position=4)
    public InvMaterialReqInvOrgUnitResultApi queryInvMaterialInvOrgUnit(@RequestBody InvMaterialReqInvOrgUnitParamsApi params) {
		InvMaterialReqInvOrgUnitResultApi result = new InvMaterialReqInvOrgUnitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqInvOrgUnitParams invMaterialReqInvOrgUnitParams = params.getParams();
        	if(invMaterialReqInvOrgUnitParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(invMaterialReqInvOrgUnitParams.getDeptNo())){
    	    		throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.reqOrgUnitNo.isnull");
    	    	}
    	    	List<OrgStorage2> orgStorageList = scmInvMaterialRequestBillBiz.queryPurReqPurOrgUnit(invMaterialReqInvOrgUnitParams, ParamHelper.createParam(integratedRequest,"queryInvMaterialInvOrgUnit"));
    	    	if(orgStorageList!=null && !orgStorageList.isEmpty()){
    	    		List<InvMaterialReqInvOrgUnitResult> resultList = new ArrayList<>();
    	    		for(OrgStorage2 orgStorage:orgStorageList){
    	    			InvMaterialReqInvOrgUnitResult invMaterialReqInvOrgUnitResult = new InvMaterialReqInvOrgUnitResult();
    	    			invMaterialReqInvOrgUnitResult.setInvOrgUnitNo(orgStorage.getOrgUnitNo());
    	    			invMaterialReqInvOrgUnitResult.setInvOrgUnitName(orgStorage.getOrgUnitName());
    	    			resultList.add(invMaterialReqInvOrgUnitResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取库存组织失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvReqMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="获取可领料物资列表", consumes="application/json",position=4)
    public InvReqMaterialListResultApi queryInvReqMaterialList(@RequestBody InvReqMaterialListParamsApi params) {
		InvReqMaterialListResultApi result = new InvReqMaterialListResultApi();//显示的结果集
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
        	InvReqMaterialListParams invReqMaterialListParams = params.getParams();
        	if(invReqMaterialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(invReqMaterialListParams.getInvOrgUnitNo())){
    	    		throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.purOrgUnitNo.isnull");
    	    	}
    	    	List<ScmMaterial2> scmMaterialList = scmInvMaterialRequestBillBiz.queryInvReqMaterialList(invReqMaterialListParams, pageIndex,ParamHelper.createParam(integratedRequest,"queryInvReqMaterialList"));
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<InvReqMaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			InvReqMaterialListResult invReqMaterialListResult = new InvReqMaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, invReqMaterialListResult);
    	    			resultList.add(invReqMaterialListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取可申请物资列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqDept", method=RequestMethod.POST)
    @ApiOperation(value="获取领料部门列表", consumes="application/json",position=4)
    public InvMaterialReqDeptResultApi queryInvMaterialReqDept(@RequestBody InvMaterialReqDeptParamsApi params) {
		InvMaterialReqDeptResultApi result = new InvMaterialReqDeptResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqDeptParams invMaterialReqDeptParams = params.getParams();
        	if(invMaterialReqDeptParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<OrgAdmin2> orgAdminList = scmInvMaterialRequestBillBiz.queryInvMaterialReqDept(invMaterialReqDeptParams, ParamHelper.createParam(integratedRequest,"queryInvMaterialReqDept"));
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<InvMaterialReqDeptResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			InvMaterialReqDeptResult invMaterialReqDeptResult = new InvMaterialReqDeptResult();
    	    			invMaterialReqDeptResult.setDeptNo(orgAdmin.getOrgUnitNo());
    	    			invMaterialReqDeptResult.setDeptName(orgAdmin.getOrgUnitName());
    	    			resultList.add(invMaterialReqDeptResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取领部门失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqPerson", method=RequestMethod.POST)
    @ApiOperation(value="获取申请人列表", consumes="application/json",position=4)
    public InvMaterialReqPersonResultApi queryInvMaterialReqPerson(@RequestBody InvMaterialReqPersonParamsApi params) {
		InvMaterialReqPersonResultApi result = new InvMaterialReqPersonResultApi();//显示的结果集
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
        	InvMaterialReqPersonParams invMaterialReqPersonParams = params.getParams();
        	if(invMaterialReqPersonParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<Usr2> usrist = scmInvMaterialRequestBillBiz.queryInvMaterialReqPerson(invMaterialReqPersonParams,pageIndex, ParamHelper.createParam(integratedRequest,"queryInvMaterialReqPerson"));
    	    	if(usrist!=null && !usrist.isEmpty()){
    	    		List<InvMaterialReqPersonResult> resultList = new ArrayList<>();
    	    		for(Usr2 usr:usrist){
    	    			InvMaterialReqPersonResult invMaterialReqPersonResult = new InvMaterialReqPersonResult();
    	    			invMaterialReqPersonResult.setEmpNo(usr.getCode());
    	    			invMaterialReqPersonResult.setEmpName(usr.getName());
    	    			resultList.add(invMaterialReqPersonResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取申请人失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doReleaseInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="下达领料申请单", consumes="application/json",position=6)
    public InvMaterialReqReleaseResultApi doReleaseInvMaterialReq(@RequestBody InvMaterialReqReleaseParamsApi params) {
		InvMaterialReqReleaseResultApi result = new InvMaterialReqReleaseResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqReleaseParams invMaterialReqReleaseParams = params.getParams();
	        if(invMaterialReqReleaseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqReleaseParams.getReqNo());
	        scmInvMaterialRequestBillBiz.release(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"doSubmitInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("下达领料申请单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnReleaseInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="取消下达领料申请单", consumes="application/json",position=7)
    public InvMaterialReqUnReleaseResultApi doUnReleaseInvMaterialReq(@RequestBody InvMaterialReqUnReleaseParamsApi params) {
		InvMaterialReqUnReleaseResultApi result = new InvMaterialReqUnReleaseResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqUnReleaseParams invMaterialReqUnReleaseParams = params.getParams();
	        if(invMaterialReqUnReleaseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialRequestBill2 scmInvMaterialRequestBill= new ScmInvMaterialRequestBill2();
	        scmInvMaterialRequestBill.setReqNo(invMaterialReqUnReleaseParams.getReqNo());
	        scmInvMaterialRequestBillBiz.undoRelease(scmInvMaterialRequestBill, ParamHelper.createParam(integratedRequest,"doUnSubmitInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("取消下达领料申请单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="审批领料申请单", consumes="application/json",position=14)
    public InvMaterialReqAuditResultApi doAuditInvMaterialReq(@RequestBody InvMaterialReqAuditParamsApi params) {
		InvMaterialReqAuditResultApi result = new InvMaterialReqAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqAuditParams invMaterialReqAuditParams = params.getParams();
	        if(invMaterialReqAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invMaterialReqAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invMaterialReqAuditParams.getReqNo());
	        if(invMaterialReqAuditParams.getDetailList()!=null && !invMaterialReqAuditParams.getDetailList().isEmpty()) {
	        	List<CommonAuditDetailParams> detailList = new ArrayList();
	        	for(InvMaterialReqAuditDetailParams invMaterialReqAuditDetailParams:invMaterialReqAuditParams.getDetailList()) {
	        		CommonAuditDetailParams commonAuditDetailParams = new CommonAuditDetailParams();
	        		BeanUtils.copyProperties(invMaterialReqAuditDetailParams,commonAuditDetailParams);
	        		detailList.add(commonAuditDetailParams);
	        	}
	        	commonAuditParams.setDetailList(detailList);
	        }
	        scmInvMaterialRequestBillBiz.doAuditInvMaterialReq(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料申请单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvMaterialReq", method=RequestMethod.POST)
    @ApiOperation(value="取消审批领料申请单", consumes="application/json",position=15)
    public InvMaterialReqUnAuditResultApi doUnAuditInvMaterialReq(@RequestBody InvMaterialReqUnAuditParamsApi params) {
    	InvMaterialReqUnAuditResultApi result = new InvMaterialReqUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqUnAuditParams invMaterialReqUnAuditParams = params.getParams();
	        if(invMaterialReqUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvMaterialRequestBill2 scmInvMaterialReq= new ScmInvMaterialRequestBill2();
	        scmInvMaterialReq.setReqNo(invMaterialReqUnAuditParams.getReqNo());
	        scmInvMaterialRequestBillBiz.doUnAuditInvMaterialReq(scmInvMaterialReq, ParamHelper.createParam(integratedRequest,"doUnAuditInvMaterialReq"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料申请单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
