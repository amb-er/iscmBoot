package com.armitage.server.api.business.pursuppliersource.controller;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceAuditParams;
import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceParams;
import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceParamsApi;
import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceUnAuditParams;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceAuditResultApi;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceRecOrgResult;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceResult;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceResultApi;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceAuditParamsApi;
import com.armitage.server.api.business.pursuppliersource.params.SupplierSourceUnAuditParamsApi;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceDetailResult;
import com.armitage.server.api.business.pursuppliersource.result.SupplierSourceUnAuditResultApi;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/supplierSource")
@Api(tags="供应商寻源业务接口")
public class SupplierSourceController {
	private static Log log = LogFactory.getLog(SupplierSourceController.class);
	private ScmPurSupplierSourceBiz scmPurSupplierSourceBiz = (ScmPurSupplierSourceBiz) AppContextUtil.getBean("scmPurSupplierSourceBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/querySupplierSource", method=RequestMethod.POST)
    @ApiOperation(value="获取供应商寻源单详情", consumes="application/json",position=1)
    public SupplierSourceResultApi querySupplierSource(@RequestBody SupplierSourceParamsApi params) {
		SupplierSourceResultApi result = new SupplierSourceResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierSourceParams supplierSourceParams = params.getParams();
	        if(supplierSourceParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurSupplierSource2 scmPurSupplierSource= new ScmPurSupplierSource2();
	        scmPurSupplierSource.setBillNo(supplierSourceParams.getBillNo());
	        ScmPurSupplierSource2 rtn = scmPurSupplierSourceBiz.queryPurSupplierSource(scmPurSupplierSource, ParamHelper.createParam(integratedRequest,"queryPurReturns"));
	        if(rtn!=null){
	        	SupplierSourceResult supplierSourceResult = new SupplierSourceResult();
	        	BeanUtils.copyProperties(rtn, supplierSourceResult);
	        	List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList = rtn.getScmPurSupplierSourceEntryList();
	        	if(scmPurSupplierSourceEntryList!=null && !scmPurSupplierSourceEntryList.isEmpty()){
	        		List<SupplierSourceDetailResult> detailList = new ArrayList<SupplierSourceDetailResult>();
	        		for(ScmPurSupplierSourceEntry2 scmPurSupplierSourceEntry:scmPurSupplierSourceEntryList){
	        			SupplierSourceDetailResult supplierSourceDetailResult = new SupplierSourceDetailResult();
	        			BeanUtils.copyProperties(scmPurSupplierSourceEntry, supplierSourceDetailResult);
	        			detailList.add(supplierSourceDetailResult);
	        		}
	        		supplierSourceResult.setDetailList(detailList);
	        	}
	        	List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList = rtn.getScmPurSupplierSourceRecOrgList();
	        	if(scmPurSupplierSourceRecOrgList!=null && !scmPurSupplierSourceRecOrgList.isEmpty()) {
	        		List< SupplierSourceRecOrgResult > orgList = new ArrayList<SupplierSourceRecOrgResult>();
	        		for(ScmPurSupplierSourceRecOrg2 scmPurSupplierSourceRecOrg:scmPurSupplierSourceRecOrgList) {
	        			SupplierSourceRecOrgResult supplierSourceRecOrgResult = new SupplierSourceRecOrgResult();
	        			supplierSourceRecOrgResult.setOrgUnitNo(scmPurSupplierSourceRecOrg.getReceiveOrgUnitNo());
	        			supplierSourceRecOrgResult.setOrgUnitName(scmPurSupplierSourceRecOrg.getOrgUnitName());
	        			orgList.add(supplierSourceRecOrgResult);
	        		}
	        		supplierSourceResult.setOrgList(orgList);
	        	}
	        	result.setResult(supplierSourceResult);
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
    @RequestMapping(value="/doAuditSupplierSource", method=RequestMethod.POST)
    @ApiOperation(value="审批供应商寻源单", consumes="application/json",position=2)
    public SupplierSourceAuditResultApi doAuditSupplierSource(@RequestBody SupplierSourceAuditParamsApi params) {
		SupplierSourceAuditResultApi result = new SupplierSourceAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierSourceAuditParams supplierSourceAuditParams = params.getParams();
	        if(supplierSourceAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(supplierSourceAuditParams, commonAuditParams);
	        scmPurSupplierSourceBiz.doAudit(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditSupplierSource"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("应商寻源单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditSupplierSource", method=RequestMethod.POST)
    @ApiOperation(value="取消审批供应商寻源单", consumes="application/json",position=3)
    public SupplierSourceUnAuditResultApi doUnAuditSupplierSource(@RequestBody SupplierSourceUnAuditParamsApi params) {
		SupplierSourceUnAuditResultApi result = new SupplierSourceUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierSourceUnAuditParams supplierSourceUnAuditParams = params.getParams();
	        if(supplierSourceUnAuditParams == null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurSupplierSource2 scmPurSupplierSource = new ScmPurSupplierSource2();
	        scmPurSupplierSource.setBillNo(supplierSourceUnAuditParams.getBillNo());
	        scmPurSupplierSourceBiz.doUnAudit(scmPurSupplierSource, ParamHelper.createParam(integratedRequest,"doUnAuditSupplierSource"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("应商寻源单申请单取消审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
