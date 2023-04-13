package com.armitage.server.api.business.invmaterialreqbill.controller;

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

import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAddParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAddParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAuditParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillAuditParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillDeptParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillDeptParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillEditParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillEditParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillListParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillListParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillPersonParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillPersonParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillSubmitParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillSubmitParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillUnAuditParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillUnAuditParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillUnSubmitParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillUnSubmitParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillWareHouseParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvMaterialReqBillWareHouseParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillLotListParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillLotListParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillMaterialListParams;
import com.armitage.server.api.business.invmaterialreqbill.params.InvReqBillMaterialListParamsApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillAddResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillAuditResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillDeptResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillDeptResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillDetailResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillEditResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillItemAddResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillItemEditResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillListResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillListResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillPersonResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillPersonResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillSubmitResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillUnAuditResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillUnSubmitResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillWareHouseApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvMaterialReqBillWareHouseResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvReqBillLotListResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvReqBillLotListResultApi;
import com.armitage.server.api.business.invmaterialreqbill.result.InvReqBillMaterialListResult;
import com.armitage.server.api.business.invmaterialreqbill.result.InvReqBillMaterialListResultApi;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/invMaterialReqBill")
@Api(tags="领料出库单接口")
public class InvMaterialReqBillController {
	private static Log log = LogFactory.getLog(InvMaterialReqBillController.class);
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz = (ScmInvMaterialReqBillBiz) AppContextUtil.getBean("scmInvMaterialReqBillBiz");
	private BillTypeBiz billTypeBiz = (BillTypeBiz) AppContextUtil.getBean("billTypeBiz");
	private OrgCompanyBiz orgCompanyBiz = (OrgCompanyBiz) AppContextUtil.getBean("orgCompanyBiz");
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="新增领料出库单", consumes="application/json",position=3)
    public InvMaterialReqBillAddResultApi doAddInvMaterialReqBill(@RequestBody InvMaterialReqBillAddParamsApi params) {
		InvMaterialReqBillAddResultApi result = new InvMaterialReqBillAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillAddParams InvMaterialReqBillAddParams = params.getParams();
        	if(InvMaterialReqBillAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(InvMaterialReqBillAddParams.getDetailList() == null || InvMaterialReqBillAddParams.getDetailList().isEmpty()){
    	    		throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
    	    	}
    	    	Param param = ParamHelper.createParam(integratedRequest,"doAddInvMaterialReqBill");
    	    	ScmInvMaterialReqBill2 scmInvMaterialReqBill=scmInvMaterialReqBillBiz.doAddMaterialReqBill(InvMaterialReqBillAddParams, param);
    	    	List<ScmInvMaterialReqBillEntry2> msgDetailList = scmInvMaterialReqBill.getScmInvMaterialReqBillEntryList();
    	    	if(msgDetailList != null && !msgDetailList.isEmpty()){
    	    		List<InvMaterialReqBillItemAddResult> detailList = new ArrayList<InvMaterialReqBillItemAddResult>();
    	    		for(ScmInvMaterialReqBillEntry2 msg : msgDetailList){
    	    			InvMaterialReqBillItemAddResult invMaterialReqBillItemAddResult = new InvMaterialReqBillItemAddResult();
	        			BeanUtils.copyProperties(msg, invMaterialReqBillItemAddResult);
	        			detailList.add(invMaterialReqBillItemAddResult);
	        		}
    	    		result.setDetailList(detailList);
    	    	}
    	    	if (scmInvMaterialReqBill!=null && !scmInvMaterialReqBill.isPost() && InvMaterialReqBillAddParams.isPosted()) {
    	    		try {
    	    			Page page = new Page();
        				page.setModelClass(OrgCompany2.class);
        				page.setShowCount(Integer.MAX_VALUE);
        				List<String> arglist = new ArrayList<>();
        				arglist.add("type=to");
        				arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
        				arglist.add("fromOrgUnitNo="+scmInvMaterialReqBill.getOrgUnitNo());
        				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", ParamHelper.createParam(integratedRequest,"doAddInvMaterialReqBill"));
        				if(orgCompanyList==null || orgCompanyList.isEmpty())
        					throw new AppException("iscm.inventorymanage.common.invOrgUnit.notfinorg");
        	    		BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "InvMatReqout", param);
        	    		if(billType!=null && billType.isAutoRelease()) {
        	    			scmInvMaterialReqBillBiz.doSubmitInvMaterialReq(scmInvMaterialReqBill, param);
        	    		} else {
        	    			scmInvMaterialReqBill = scmInvMaterialReqBillBiz.doSubmitInvMaterialReq(scmInvMaterialReqBill, param);
        	    			List<String> msgList = scmInvMaterialReqBillBiz.postBillCheck(scmInvMaterialReqBill, param);
    						if (msgList != null && !msgList.isEmpty()) {
    							StringBuilder detailInfo = new StringBuilder("");
    	                        detailInfo.append(Message.getMessage(integratedRequest.getLang(),"iscm.inventorymanage.scminvmaterialreqbill.post.errorTitle"));

    							for (String str : msgList) {
    	                            detailInfo.append(str).append("\n");
    	                        }
    							
    							throw new AppException(detailInfo.toString(), new String[]{scmInvMaterialReqBill.getOtNo()});
    						}
        	    			scmInvMaterialReqBill = scmInvMaterialReqBillBiz.postBill(scmInvMaterialReqBill, param);
        	    		}
					} catch (Exception e) {
						//单据过账失败仍然新增
						scmInvMaterialReqBill.setChecker(null);
						scmInvMaterialReqBill.setSubmitter(null);
						scmInvMaterialReqBill.setCheckDate(null);
						scmInvMaterialReqBill.setSubmitDate(null);
						scmInvMaterialReqBill.setStatus("I");
						scmInvMaterialReqBillBiz.updateDirect(scmInvMaterialReqBill, param);
						result.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					}
    	    	}
    		    if(scmInvMaterialReqBill!=null){
    		    	result.setOtNo(scmInvMaterialReqBill.getOtNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("领料出库单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/doEditInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="修改领料出库单", consumes="application/json",position=4)
    public InvMaterialReqBillEditResultApi doEditInvMaterialReq(@RequestBody InvMaterialReqBillEditParamsApi params) {
		InvMaterialReqBillEditResultApi result = new InvMaterialReqBillEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillEditParams invMaterialReqBillEditParams = params.getParams();
        	if(invMaterialReqBillEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmInvMaterialReqBill2 scmInvMaterialReqBill = scmInvMaterialReqBillBiz.doEditMaterialReqBill(invMaterialReqBillEditParams, ParamHelper.createParam(integratedRequest,"doEditInvMaterialReqBill"));
    	    	List<ScmInvMaterialReqBillEntry2> msgDetailList = scmInvMaterialReqBill.getScmInvMaterialReqBillEntryList();
    	    	if(msgDetailList != null && !msgDetailList.isEmpty()){
    	    		List<InvMaterialReqBillItemEditResult> detailList = new ArrayList<InvMaterialReqBillItemEditResult>();
    	    		for(ScmInvMaterialReqBillEntry2 msg : msgDetailList){
    	    			InvMaterialReqBillItemEditResult invMaterialReqBillItemEditResult = new InvMaterialReqBillItemEditResult();
	        			BeanUtils.copyProperties(msg, invMaterialReqBillItemEditResult);
	        			detailList.add(invMaterialReqBillItemEditResult);
	        		}
    	    		result.setDetailList(detailList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("领料出库单修改失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi,iespApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="获取领料出库单详情", consumes="application/json",position=2)
    public InvMaterialReqBillResultApi queryInvMaterialReqBill(@RequestBody InvMaterialReqBillParamsApi params) {
		InvMaterialReqBillResultApi result = new InvMaterialReqBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillParams invMaterialReqBillParams = params.getParams();
	        if(invMaterialReqBillParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialReqBill2 scmInvMaterialReqBill = new ScmInvMaterialReqBill2();
	        scmInvMaterialReqBill.setOtNo(invMaterialReqBillParams.getOtNo());
	        ScmInvMaterialReqBill2 rtn = scmInvMaterialReqBillBiz.queryInvMaterialReqBill(scmInvMaterialReqBill, ParamHelper.createParam(integratedRequest,"queryInvMaterialReqBill"));
	        if(rtn!=null){
	        	InvMaterialReqBillResult invMaterialReqBillResult = new InvMaterialReqBillResult();
	        	BeanUtils.copyProperties(rtn, invMaterialReqBillResult);
	        	List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList = rtn.getScmInvMaterialReqBillEntryList();
	        	if(scmInvMaterialReqBillEntryList != null && !scmInvMaterialReqBillEntryList.isEmpty()){
	        		List<InvMaterialReqBillDetailResult> detailList = new ArrayList<InvMaterialReqBillDetailResult>();
	        		for(ScmInvMaterialReqBillEntry2 scmInvMaterialReqBillEntry : scmInvMaterialReqBillEntryList){
	        			InvMaterialReqBillDetailResult invMaterialReqBillDetailResult = new InvMaterialReqBillDetailResult();
	        			BeanUtils.copyProperties(scmInvMaterialReqBillEntry, invMaterialReqBillDetailResult);
	        			invMaterialReqBillDetailResult.setUnit(scmInvMaterialReqBillEntry.getUnitName());
	        			invMaterialReqBillDetailResult.setPieUnit(scmInvMaterialReqBillEntry.getPieUnitName());
	        			detailList.add(invMaterialReqBillDetailResult);
	        		}
	        		invMaterialReqBillResult.setDetailList(detailList);
	        	}
	        	result.setResult(invMaterialReqBillResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取领料出库单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="审批领料出库单", consumes="application/json",position=3)
    public InvMaterialReqBillAuditResultApi doAuditInvMaterialReqBill(@RequestBody InvMaterialReqBillAuditParamsApi params) {
    	InvMaterialReqBillAuditResultApi result = new InvMaterialReqBillAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillAuditParams invMaterialReqBillAuditParams = params.getParams();
	        if(invMaterialReqBillAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invMaterialReqBillAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invMaterialReqBillAuditParams.getOtNo());
	        scmInvMaterialReqBillBiz.doAuditInvMaterialReqBill(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvMaterialReqBill"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料出库单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="取消审批领料出库单", consumes="application/json",position=4)
    public InvMaterialReqBillUnAuditResultApi doUnAuditInvMaterialReqBill(@RequestBody InvMaterialReqBillUnAuditParamsApi params) {
    	InvMaterialReqBillUnAuditResultApi result = new InvMaterialReqBillUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillUnAuditParams invMaterialReqBillUnAuditParams = params.getParams();
	        if(invMaterialReqBillUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvMaterialReqBill2 scmInvMaterialReqBill = new ScmInvMaterialReqBill2();
	        scmInvMaterialReqBill.setOtNo(invMaterialReqBillUnAuditParams.getOtNo());
	        scmInvMaterialReqBillBiz.doUnAuditInvMaterialReqBill(scmInvMaterialReqBill, ParamHelper.createParam(integratedRequest,"doUnAuditInvMaterialReqBill"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料出库单取消审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqBillList", method=RequestMethod.POST)
    @ApiOperation(value="查询领料出库单列表", consumes="application/json",position=3)
    public InvMaterialReqBillListResultApi queryInvMaterialReqBillList(@RequestBody InvMaterialReqBillListParamsApi params) {
		InvMaterialReqBillListResultApi result = new InvMaterialReqBillListResultApi();//显示的结果集
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
        	InvMaterialReqBillListParams invMaterialReqBillAuditParams = params.getParams();
	        if(invMaterialReqBillAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMaterialReqBillAdvQuery scmInvMaterialReqBillAdvQuery = new ScmInvMaterialReqBillAdvQuery();
	        BeanUtils.copyProperties(invMaterialReqBillAuditParams, scmInvMaterialReqBillAdvQuery);
	        List<ScmInvMaterialReqBill2> scmInvMaterialReqBillList = scmInvMaterialReqBillBiz.queryInvMaterialReqBillList(scmInvMaterialReqBillAdvQuery, pageIndex,ParamHelper.createParam(integratedRequest,"queryInvMaterialReqBillList"));
	        if (scmInvMaterialReqBillList != null && !scmInvMaterialReqBillList.isEmpty()) {
	        	List< InvMaterialReqBillListResult > resultList= new ArrayList<InvMaterialReqBillListResult>();
				for (ScmInvMaterialReqBill2 scmInvMaterialReqBill2 : scmInvMaterialReqBillList) {
					InvMaterialReqBillListResult invMaterialReqBillListResult = new InvMaterialReqBillListResult();
					BeanUtils.copyProperties(scmInvMaterialReqBill2, invMaterialReqBillListResult);
					invMaterialReqBillListResult.setReqBillNo(scmInvMaterialReqBill2.getOtNo());
					invMaterialReqBillListResult.setUseDeptName(scmInvMaterialReqBill2.getUseOrgUnitName());
					invMaterialReqBillListResult.setInvOrgUnitName(scmInvMaterialReqBill2.getOrgUnitName());
					resultList.add(invMaterialReqBillListResult);
				}
				result.setResultList(resultList);
			}
	        
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询领料出库单列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqBillDept", method=RequestMethod.POST)
    @ApiOperation(value="获取领料部门列表", consumes="application/json",position=3)
    public InvMaterialReqBillDeptResultApi queryInvMaterialReqBillDept(@RequestBody InvMaterialReqBillDeptParamsApi params) {
		InvMaterialReqBillDeptResultApi result = new InvMaterialReqBillDeptResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillDeptParams invMaterialReqBillDeptParams = params.getParams();
	        if(invMaterialReqBillDeptParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        List<OrgAdmin2> orgAdminList = scmInvMaterialReqBillBiz.queryInvMaterialReqBillDept(invMaterialReqBillDeptParams,ParamHelper.createParam(integratedRequest,"queryInvMaterialReqBillDept"));
	        if(orgAdminList!=null && !orgAdminList.isEmpty()){
	        	List<InvMaterialReqBillDeptResult> resultList = new ArrayList<>();
	        	for(OrgAdmin2 orgAdmin:orgAdminList){
	        		InvMaterialReqBillDeptResult invMaterialReqBillDeptResult = new InvMaterialReqBillDeptResult();
	        		invMaterialReqBillDeptResult.setDeptNo(orgAdmin.getOrgUnitNo());
	        		invMaterialReqBillDeptResult.setDeptName(orgAdmin.getOrgUnitName());
	        		resultList.add(invMaterialReqBillDeptResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取领料部门失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqBillWareHouse", method=RequestMethod.POST)
    @ApiOperation(value="获取出库仓库列表", consumes="application/json",position=3)
    public InvMaterialReqBillWareHouseApi queryInvMaterialReqBillWareHouse(@RequestBody InvMaterialReqBillWareHouseParamsApi params) {
		InvMaterialReqBillWareHouseApi result = new InvMaterialReqBillWareHouseApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillWareHouseParams invMaterialReqBillWareHouseParams = params.getParams();
	        if(invMaterialReqBillWareHouseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        List<ScmInvWareHouse> scmInvWareHouseList = scmInvMaterialReqBillBiz.queryInvMaterialReqBillWareHouse(invMaterialReqBillWareHouseParams,ParamHelper.createParam(integratedRequest,"queryInvMaterialReqBillWareHouse"));
	        if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
	    		List< InvMaterialReqBillWareHouseResult > resultList = new ArrayList<>();
	    		for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
	    			InvMaterialReqBillWareHouseResult invMaterialReqBillWareHouseResult = new InvMaterialReqBillWareHouseResult();
	    			invMaterialReqBillWareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
	    			invMaterialReqBillWareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
	    			resultList.add(invMaterialReqBillWareHouseResult);
	    		}
	    		result.setResultList(resultList);
	    	}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取出库仓库失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvReqBillMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="查询可用领料物资", consumes="application/json",position=3)
    public InvReqBillMaterialListResultApi queryInvReqBillMaterialList(@RequestBody InvReqBillMaterialListParamsApi params) {
		InvReqBillMaterialListResultApi result = new InvReqBillMaterialListResultApi();//显示的结果集
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
        	InvReqBillMaterialListParams invReqBillMaterialListParams = params.getParams();
	        if(invReqBillMaterialListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        List<ScmMaterial2> scmMaterialList = scmInvMaterialReqBillBiz.queryInvReqBillMaterialList(invReqBillMaterialListParams,pageIndex,ParamHelper.createParam(integratedRequest,"queryInvReqBillMaterialList"));
	        if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
	    		List<InvReqBillMaterialListResult> resultList = new ArrayList<>();
	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
	    			InvReqBillMaterialListResult invReqMaterialListResult = new InvReqBillMaterialListResult();
	    			BeanUtils.copyProperties(scmMaterial, invReqMaterialListResult);
	    			invReqMaterialListResult.setUnit(scmMaterial.getUnitId());
	    			invReqMaterialListResult.setGroupName(scmMaterial.getClassName());
	    			resultList.add(invReqMaterialListResult);
	    		}
	    		result.setResultList(resultList);
	    	}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询可用领料物资失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvReqBillLotList", method=RequestMethod.POST)
    @ApiOperation(value="获取物资批次列表", consumes="application/json",position=3)
    public InvReqBillLotListResultApi queryInvReqBillLotList(@RequestBody InvReqBillLotListParamsApi params) {
		InvReqBillLotListResultApi result = new InvReqBillLotListResultApi();//显示的结果集
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
        	InvReqBillLotListParams invReqBillLotListParams = params.getParams();
	        if(invReqBillLotListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        List<ScmInvStock2> scmInvStock2List = scmInvMaterialReqBillBiz.queryInvReqBillLotList(invReqBillLotListParams,pageIndex,ParamHelper.createParam(integratedRequest,"queryInvReqBillLotList"));
	        if (scmInvStock2List !=null && !scmInvStock2List.isEmpty()) {
	        	List< InvReqBillLotListResult > resultList = new ArrayList<>();
	        	for (ScmInvStock2 scmInvStock2 : scmInvStock2List) {
	        		InvReqBillLotListResult invReqBillLotListResult2 = new InvReqBillLotListResult();
	        		BeanUtils.copyProperties(scmInvStock2, invReqBillLotListResult2);
	        		resultList.add(invReqBillLotListResult2);
				}
	        	result.setResultList(resultList);
			}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取物资批次列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqBillPerson", method=RequestMethod.POST)
    @ApiOperation(value="查询领料人", consumes="application/json",position=3)
    public InvMaterialReqBillPersonResultApi queryInvMaterialReqBillPerson(@RequestBody InvMaterialReqBillPersonParamsApi params) {
		InvMaterialReqBillPersonResultApi result = new InvMaterialReqBillPersonResultApi();//显示的结果集
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
        	InvMaterialReqBillPersonParams invMaterialReqBillPersonParams = params.getParams();
	        if(invMaterialReqBillPersonParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        List<Usr> usrList = scmInvMaterialReqBillBiz.queryInvMaterialReqBillPerson(invMaterialReqBillPersonParams,pageIndex,ParamHelper.createParam(integratedRequest,"queryInvMaterialReqBillPerson"));
	        if (usrList != null && !usrList.isEmpty()) {
	        	List<InvMaterialReqBillPersonResult> resultList = new ArrayList<>();
	        	for (Usr usr : usrList) {
					InvMaterialReqBillPersonResult invMaterialReqBillPersonResult2 = new InvMaterialReqBillPersonResult();
					BeanUtils.copyProperties(usr, invMaterialReqBillPersonResult2);
					invMaterialReqBillPersonResult2.setUsrCode(usr.getCode());
					invMaterialReqBillPersonResult2.setUsrName(usr.getName());
					resultList.add(invMaterialReqBillPersonResult2);
	        	}
	        	result.setResultList(resultList);
			}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询领料人失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="提交领料出库单", consumes="application/json",position=3)
    public InvMaterialReqBillSubmitResultApi doSubmitInvMaterialReqBill(@RequestBody InvMaterialReqBillSubmitParamsApi params) {
		InvMaterialReqBillSubmitResultApi result = new InvMaterialReqBillSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillSubmitParams invMaterialReqBillSubmitParams = params.getParams();
	        if(invMaterialReqBillSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        if (StringUtils.isEmpty(invMaterialReqBillSubmitParams.getReqBillNo())) {
	        	throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.doSubmitInvMaterialReqBill.notNullBillNo");
			}
	        ScmInvMaterialReqBill2 scmInvMaterialReqBill2 = new ScmInvMaterialReqBill2();
	        scmInvMaterialReqBill2.setOtNo(invMaterialReqBillSubmitParams.getReqBillNo());
	        scmInvMaterialReqBillBiz.doSubmitInvMaterialReq(scmInvMaterialReqBill2, ParamHelper.createParam(integratedRequest,"doSubmitInvMaterialReqBill"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("提交领料出库单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitInvMaterialReqBill", method=RequestMethod.POST)
    @ApiOperation(value="取消提交领料出库单", consumes="application/json",position=3)
    public InvMaterialReqBillUnSubmitResultApi doUnSubmitInvMaterialReqBill(@RequestBody InvMaterialReqBillUnSubmitParamsApi params) {
		InvMaterialReqBillUnSubmitResultApi result = new InvMaterialReqBillUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMaterialReqBillUnSubmitParams invMaterialReqBillUnSubmitParams = params.getParams();
	        if(invMaterialReqBillUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        if (StringUtils.isEmpty(invMaterialReqBillUnSubmitParams.getReqBillNo())) {
	        	throw new AppException("iscm.inventorymanage.scminvmaterialreqbill.doSubmitInvMaterialReqBill.notNullBillNo");
			}
	        ScmInvMaterialReqBill2 scmInvMaterialReqBill2 = new ScmInvMaterialReqBill2();
	        scmInvMaterialReqBill2.setOtNo(invMaterialReqBillUnSubmitParams.getReqBillNo());
	        scmInvMaterialReqBillBiz.doUnSubmitInvMaterialReq(scmInvMaterialReqBill2, ParamHelper.createParam(integratedRequest,"doUnSubmitInvMaterialReqBill"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("取消提交领料出库单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
