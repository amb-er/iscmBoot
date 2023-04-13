package com.armitage.server.api.business.basedata.controller;

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

import com.armitage.server.api.business.basedata.params.SupplierListParams;
import com.armitage.server.api.business.basedata.params.SupplierListParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierOAParams;
import com.armitage.server.api.business.basedata.params.SupplierOAParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierParams;
import com.armitage.server.api.business.basedata.params.SupplierParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierUpdateSendOAParams;
import com.armitage.server.api.business.basedata.params.SupplierUpdateSendOAParamsApi;
import com.armitage.server.api.business.basedata.result.SupplierInvitationResult;
import com.armitage.server.api.business.basedata.result.SupplierInvitationResultApi;
import com.armitage.server.api.business.basedata.result.SupplierListResult;
import com.armitage.server.api.business.basedata.result.SupplierListResultApi;
import com.armitage.server.api.business.basedata.result.SupplierOAResult;
import com.armitage.server.api.business.basedata.result.SupplierOAResultApi;
import com.armitage.server.api.business.basedata.result.SupplierResult;
import com.armitage.server.api.business.basedata.result.SupplierResultApi;
import com.armitage.server.api.business.basedata.result.SupplierUpdateSendOResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.ScmsupplierAdvQuery;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/supplier")
@Api(tags="供应商接口")
public class SupplierController {
	private static Log log = LogFactory.getLog(SupplierController.class);
	private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/querySupplierList", method=RequestMethod.POST)
    @ApiOperation(value="获取供应商列表", consumes="application/json",position=4)
    public SupplierListResultApi querySupplierList(@RequestBody SupplierListParamsApi params) {
		SupplierListResultApi result = new SupplierListResultApi();//显示的结果集
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
        	SupplierListParams supplierListParams = params.getParams();
        	if(supplierListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmsupplierAdvQuery scmsupplierAdvQuery = new ScmsupplierAdvQuery();
    	    	BeanUtils.copyProperties(supplierListParams, scmsupplierAdvQuery);
    	    	List<Scmsupplier2> scmsupplierList = scmsupplierBiz.querySupplierList(scmsupplierAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"querySupplierList"));
    	    	if(scmsupplierList!=null && !scmsupplierList.isEmpty()){
    	    		List< SupplierListResult > resultList = new ArrayList<>();
    	    		for(Scmsupplier2 scmsupplier:scmsupplierList){
    	    			SupplierListResult supplierListResult = new SupplierListResult();
    	    			BeanUtils.copyProperties(scmsupplier,supplierListResult);
    	    			supplierListResult.setVendorCode(scmsupplier.getVendorNo());
    	    			resultList.add(supplierListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取收货供应商列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/querySupplier", method=RequestMethod.POST)
    @ApiOperation(value="获取供应商详情", consumes="application/json",position=4)
    public SupplierResultApi querySupplier(@RequestBody SupplierParamsApi params) {
		SupplierResultApi result = new SupplierResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierParams supplierParams = params.getParams();
        	if(supplierParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmsupplierAdvQuery scmsupplierAdvQuery = new ScmsupplierAdvQuery();
    	    	BeanUtils.copyProperties(supplierParams, scmsupplierAdvQuery);
    	    	Scmsupplier2 scmsupplier = scmsupplierBiz.querySupplier(scmsupplierAdvQuery, ParamHelper.createParam(integratedRequest,"querySupplier"));
    	    	if(scmsupplier!=null){
    	    		SupplierResult supplierResult = new SupplierResult();
    	    		BeanUtils.copyProperties(scmsupplier,supplierResult);
    	    		supplierResult.setVendorCode(scmsupplier.getVendorNo());
    	    		supplierResult.setVendorId(scmsupplier.getId());
    	    		result.setResult(supplierResult);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取供应商详情失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/getInvitationCode", method=RequestMethod.POST)
    @ApiOperation(value="获取供应商邀请码", consumes="application/json",position=4)
    public SupplierInvitationResultApi getInvitationCode(@RequestBody SupplierParamsApi params) {
		SupplierInvitationResultApi result = new SupplierInvitationResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierParams supplierParams = params.getParams();
        	if(supplierParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmSupplierRegInvitation scmSupplierRegInvitation = scmsupplierBiz.getInvitationCode(supplierParams.getVendorNo(), ParamHelper.createParam(integratedRequest,"getInvitationCode"));
    	    	if(scmSupplierRegInvitation!=null){
    	    		SupplierInvitationResult supplierInvitationResult = new SupplierInvitationResult();
    	    		BeanUtils.copyProperties(scmSupplierRegInvitation,supplierInvitationResult);
    	    		result.setResult(supplierInvitationResult);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取供应商邀请码:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
	}
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/querySupplierOA", method=RequestMethod.POST)
    @ApiOperation(value="获取供应商", consumes="application/json",position=4)
    public SupplierOAResultApi querySupplierOA(@RequestBody SupplierOAParamsApi params) {
		SupplierOAResultApi result = new SupplierOAResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierOAParams supplierParams = params.getParams();
        	if(supplierParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Param createParam = ParamHelper.createParam(integratedRequest,"querySupplier");
    	    	List<Scmsupplier2> scmsupplierList = scmsupplierBiz.querySupplierOA(supplierParams,createParam);
    	    	if(scmsupplierList!=null && !scmsupplierList.isEmpty()){
    	    		List<SupplierOAResult> resultList = new ArrayList<>();
    	    		for (Scmsupplier2 scmsupplier : scmsupplierList) {
    	    			SupplierOAResult supplierOAResult = new SupplierOAResult(true);
    	    			supplierOAResult.setSupplierId(scmsupplier.getId());
        	    		supplierOAResult.setGhlb2(scmsupplier.getClassName());
        	    		supplierOAResult.setXzgysqc(scmsupplier.getVendorName());
        	    		supplierOAResult.setGyslxr(scmsupplier.getContactPerson());
        	    		supplierOAResult.setGysnsrsbh(scmsupplier.getTaxNo());
        	    		supplierOAResult.setGysskzh(scmsupplier.getBankAccNo());
        	    		supplierOAResult.setGyskhx(scmsupplier.getBankName());
        	    		supplierOAResult.setSl(scmsupplier.getVatRateString());
        	    		supplierOAResult.setGyszz(scmsupplier.getQualifyType());
        	    		supplierOAResult.setSqms(scmsupplier.getRemarks());
    	    			resultList.add(supplierOAResult);
					}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取供应商详情失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/updateSendOA", method=RequestMethod.POST)
    @ApiOperation(value="修改上传OA标志", consumes="application/json",position=4)
    public SupplierUpdateSendOResultApi updateSendOA(@RequestBody SupplierUpdateSendOAParamsApi params) {
		SupplierUpdateSendOResultApi result = new SupplierUpdateSendOResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierUpdateSendOAParams supplierUpdateSendOAParams = params.getParams();
        	if(supplierUpdateSendOAParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Param createParam = ParamHelper.createParam(integratedRequest,"updateSendOA");
    	    	scmsupplierBiz.updateSendOA(supplierUpdateSendOAParams.getId(),createParam);
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("修改上传OA标志失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
	}
}
