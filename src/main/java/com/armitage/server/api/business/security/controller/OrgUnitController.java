package com.armitage.server.api.business.security.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.armitage.server.api.business.security.params.CommonAppServiceInfoParams;
import com.armitage.server.api.business.security.params.CommonAppServiceInfoParamsApi;
import com.armitage.server.api.business.security.params.CommonOutletTypeParams;
import com.armitage.server.api.business.security.params.CommonOutletTypeParamsApi;
import com.armitage.server.api.business.security.params.OrgAdminListParams;
import com.armitage.server.api.business.security.params.OrgAdminListParamsApi;
import com.armitage.server.api.business.security.params.OrgAdminToFinListParams;
import com.armitage.server.api.business.security.params.OrgAdminToFinListParamsApi;
import com.armitage.server.api.business.security.params.OrgCompanyParams;
import com.armitage.server.api.business.security.params.OrgCompanyParamsApi;
import com.armitage.server.api.business.security.params.OrgCostCenterListParams;
import com.armitage.server.api.business.security.params.OrgCostCenterListParamsApi;
import com.armitage.server.api.business.security.params.OrgStorageListParams;
import com.armitage.server.api.business.security.params.OrgStorageListParamsApi;
import com.armitage.server.api.business.security.params.PlatformInfoParams;
import com.armitage.server.api.business.security.params.PlatformInfoParamsApi;
import com.armitage.server.api.business.security.params.SubscriptionParams;
import com.armitage.server.api.business.security.params.SubscriptionParamsApi;
import com.armitage.server.api.business.security.params.SysParamParams;
import com.armitage.server.api.business.security.params.SysParamParamsApi;
import com.armitage.server.api.business.security.result.CommonAppServiceInfoDetailResult;
import com.armitage.server.api.business.security.result.CommonAppServiceInfoResult;
import com.armitage.server.api.business.security.result.CommonAppServiceInfoResultApi;
import com.armitage.server.api.business.security.result.CommonOutletTypeResult;
import com.armitage.server.api.business.security.result.CommonOutletTypeResultApi;
import com.armitage.server.api.business.security.result.OrgAdminResult;
import com.armitage.server.api.business.security.result.OrgAdminResultApi;
import com.armitage.server.api.business.security.result.OrgAdminToFinResult;
import com.armitage.server.api.business.security.result.OrgAdminToFinResultApi;
import com.armitage.server.api.business.security.result.OrgCompanyResult;
import com.armitage.server.api.business.security.result.OrgCompanyResultApi;
import com.armitage.server.api.business.security.result.OrgCostCenterResult;
import com.armitage.server.api.business.security.result.OrgCostCenterResultApi;
import com.armitage.server.api.business.security.result.OrgStorageResult;
import com.armitage.server.api.business.security.result.OrgStorageResultApi;
import com.armitage.server.api.business.security.result.PlatformInfoResult;
import com.armitage.server.api.business.security.result.PlatformInfoResultApi;
import com.armitage.server.api.business.security.result.SubscriptionResult;
import com.armitage.server.api.business.security.result.SubscriptionResultApi;
import com.armitage.server.api.business.security.result.SysParamResult;
import com.armitage.server.api.business.security.result.SysParamResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.common.util.VarConstant;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.system.model.AppServiceParam2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgCostCenterAdvQuery;
import com.armitage.server.system.model.OrgPlatForm2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.OrgStorageAdvQuery;
import com.armitage.server.system.service.AppServiceBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgPlatFormBiz;
import com.armitage.server.system.service.OrgResourceOutletTypeBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.SubscriptionSettingBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.webservice.model.CommonAppServiceInfoQueryParams;
import com.armitage.server.webservice.model.CommonAppServiceInfoQueryResult;
import com.armitage.server.webservice.model.CommonOutletTypeQuery;
import com.armitage.server.webservice.model.CommonOutletTypeQueryParams;
import com.armitage.server.webservice.model.CommonOutletTypeQueryResult;
import com.armitage.server.webservice.model.SubscriptionFileParams;
import com.armitage.server.webservice.model.SubscriptionListResult;
import com.armitage.server.webservice.model.SubscriptionQueryResult;
import com.armitage.server.webservice.model.SysParamGetParams;
import com.armitage.server.webservice.model.SysParamGetResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/orgUnit")
@Api(tags="组织相关接口")
public class OrgUnitController {
	private OrgResourceOutletTypeBiz orgResourceOutletTypeBiz = (OrgResourceOutletTypeBiz) AppContextUtil.getBean("orgResourceOutletTypeBiz");
	private AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
	private OrgPlatFormBiz orgPlatFormBiz = (OrgPlatFormBiz) AppContextUtil.getBean("orgPlatFormBiz");
	private SubscriptionSettingBiz subscriptionSettingBiz = (SubscriptionSettingBiz) AppContextUtil.getBean("subscriptionSettingBiz");
	private SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
	private OrgStorageBiz orgStorageBiz = (OrgStorageBiz) AppContextUtil.getBean("orgStorageBiz");
	private OrgCostCenterBiz orgCostCenterBiz = (OrgCostCenterBiz) AppContextUtil.getBean("orgCostCenterBiz");
	private OrgAdminBiz orgAdminBiz = (OrgAdminBiz) AppContextUtil.getBean("orgAdminBiz");
	private OrgCompanyBiz orgCompanyBiz = (OrgCompanyBiz) AppContextUtil.getBean("orgCompanyBiz");
	private OrgUnitBiz orgUnitBiz = (OrgUnitBiz) AppContextUtil.getBean("orgUnitBiz");
	private ScmSupplierDemanderBiz scmSupplierDemanderBiz = (ScmSupplierDemanderBiz) AppContextUtil.getBean("scmSupplierDemanderBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCommonOutletType", method=RequestMethod.POST)
    @ApiOperation(value="查询组织可用产品", consumes="application/json")
    public CommonOutletTypeResultApi queryCommonOutletType(@RequestBody CommonOutletTypeParamsApi params) {
		CommonOutletTypeResultApi result = new CommonOutletTypeResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		CommonOutletTypeParams commonOutletTypeParams = params.getParams();
	        if(commonOutletTypeParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        CommonOutletTypeQueryParams commonOutletTypeQueryParams = new CommonOutletTypeQueryParams();
	        BeanUtils.copyProperties(commonOutletTypeParams, commonOutletTypeQueryParams);
	        CommonOutletTypeQueryResult commonOutletTypeQueryResult = orgResourceOutletTypeBiz.queryCommonOutletType(commonOutletTypeQueryParams, ParamHelper.createParam(integratedRequest, "queryCommonOutletType"));
	        if(commonOutletTypeQueryResult!=null && commonOutletTypeQueryResult.getCommonOutletTypeList()!=null && !commonOutletTypeQueryResult.getCommonOutletTypeList().isEmpty()){
	        	List< CommonOutletTypeResult > resultList = new ArrayList<>();
	        	for(CommonOutletTypeQuery commonOutletTypeQuery:commonOutletTypeQueryResult.getCommonOutletTypeList()){
	        		CommonOutletTypeResult commonOutletTypeResult = new CommonOutletTypeResult();
	        		BeanUtils.copyProperties(commonOutletTypeQuery, commonOutletTypeResult);
	        		resultList.add(commonOutletTypeResult);
	        	}
	        	result.setResultList(resultList);
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCommonAppServiceInfo", method=RequestMethod.POST)
    @ApiOperation(value="获取应用服务配置信息", consumes="application/json")
    public CommonAppServiceInfoResultApi queryCommonAppServiceInfo(@RequestBody CommonAppServiceInfoParamsApi params) {
		CommonAppServiceInfoResultApi result = new CommonAppServiceInfoResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		CommonAppServiceInfoParams commonAppServiceInfoParams = params.getParams();
	        if(commonAppServiceInfoParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryCommonAppServiceInfo");
	        CommonAppServiceInfoQueryParams commonAppServiceInfoQueryParams = new CommonAppServiceInfoQueryParams();
	        BeanUtils.copyProperties(commonAppServiceInfoParams, commonAppServiceInfoQueryParams);
	        commonAppServiceInfoQueryParams.setOrgUnitNo(param.getOrgUnitNo());
	        CommonAppServiceInfoQueryResult commonAppServiceInfoQueryResult = appServiceBiz.queryCommonAppServiceInfo(commonAppServiceInfoQueryParams,param);
	        if(commonAppServiceInfoQueryResult!=null && commonAppServiceInfoQueryResult.getAppService()!=null){
	        	CommonAppServiceInfoResult commonAppServiceInfoResult = new CommonAppServiceInfoResult();
	        	BeanUtils.copyProperties(commonAppServiceInfoQueryResult.getAppService(), commonAppServiceInfoResult);
	        	List<AppServiceParam2> appServiceParamList = commonAppServiceInfoQueryResult.getAppService().getAppServiceParamList();
	        	if(appServiceParamList!=null && !appServiceParamList.isEmpty()){
		        	List< CommonAppServiceInfoDetailResult > detailList = new ArrayList<>();
		        	for(AppServiceParam2 appServiceParam:appServiceParamList){
		        		CommonAppServiceInfoDetailResult commonAppServiceInfoDetailResult = new CommonAppServiceInfoDetailResult();
		        		BeanUtils.copyProperties(appServiceParam, commonAppServiceInfoDetailResult);
		        		detailList.add(commonAppServiceInfoDetailResult);
		        	}
		        	commonAppServiceInfoResult.setDetailList(detailList);
	        	}
	        	result.setResult(commonAppServiceInfoResult);
	        }else{
	    		result.setErrCode("-1");
	    		result.setErrText("无法获取应用服务配置信息");
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPlatformInfo", method=RequestMethod.POST)
    @ApiOperation(value="获取平台组织信息", consumes="application/json")
    public PlatformInfoResultApi queryPlatformInfo(@RequestBody PlatformInfoParamsApi params) {
		PlatformInfoResultApi result = new PlatformInfoResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
    		SecurityUtils.verify(integratedRequest);
    		PlatformInfoParams platformInfoParams = params.getParams();
	        if(platformInfoParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        OrgPlatForm2 orgPlatForm = orgPlatFormBiz.selectByOrgUnitNo(platformInfoParams.getOrgUnitNo(), ParamHelper.createParam(integratedRequest,"queryPlatformInfo"));
	        if(orgPlatForm!=null){
	        	PlatformInfoResult platformInfoResult = new PlatformInfoResult();
	        	BeanUtils.copyProperties(orgPlatForm, platformInfoResult);
	        	result.setResult(platformInfoResult);
	        }else{
	    		result.setErrCode("-1");
	    		result.setErrText("无法获取平台组织信息");
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/querySubscription", method=RequestMethod.POST)
    @ApiOperation(value="查询公众号设置信息", consumes="application/json")
    public SubscriptionResultApi querySubscription(@RequestBody SubscriptionParamsApi params) {
		SubscriptionResultApi result = new SubscriptionResultApi();//返回结果
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
    		SubscriptionParams subscriptionParams = params.getParams();
	        if(subscriptionParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        SubscriptionFileParams subscriptionFileParams = new SubscriptionFileParams();
	        BeanUtils.copyProperties(subscriptionParams, subscriptionFileParams);
	        SubscriptionQueryResult subscriptionQueryResult = subscriptionSettingBiz.querySubscription(subscriptionFileParams, null, pageIndex, ParamHelper.createParam(integratedRequest,
					"querySubscription"));
	        if(subscriptionQueryResult!=null && subscriptionQueryResult.getResultList()!=null && !subscriptionQueryResult.getResultList().isEmpty()){
	        	List<SubscriptionResult> resultList = new ArrayList();
	        	for(SubscriptionListResult subscriptionListResult:subscriptionQueryResult.getResultList()) {
		        	SubscriptionResult subscriptionResult = new SubscriptionResult();
		        	BeanUtils.copyProperties(subscriptionListResult, subscriptionResult);
		        	resultList.add(subscriptionResult);
	        	}
	        	result.setResultList(resultList);
	        }else{
	    		result.setErrCode("-1");
	    		result.setErrText("无法获取平台组织信息");
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doGetSysParam", method=RequestMethod.POST)
    @ApiOperation(value="查询系统参数", consumes="application/json")
    public SysParamResultApi doGetSysParam(@RequestBody SysParamParamsApi params) {
		SysParamResultApi result = new SysParamResultApi();//返回结果
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);

    	try {
        	SecurityUtils.verify(integratedRequest);
    		SysParamParams sysParamParams = params.getParams();
	        if(sysParamParams==null){
	            result.setErrCode("-1");
	            result.setErrText("params参数不能为空");
	            throw new AppException("webservice.params.null");
	        }
	        SysParamGetParams sysParamGetParams = new SysParamGetParams();
	        BeanUtils.copyProperties(sysParamParams, sysParamGetParams);
	        SysParamGetResult sysParamGetResult = sysParamBiz.doGetSysParam(sysParamGetParams, ParamHelper.createParam(integratedRequest,"doGetSysParam"));
	        if(sysParamGetResult!=null && sysParamGetResult.getSysGet()!=null){
	        	SysParamResult sysParamResult = new SysParamResult();
	        	BeanUtils.copyProperties(sysParamGetResult.getSysGet(), sysParamResult);
	        	result.setResult(sysParamResult);
	        }
    	} catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText(Message.getMessage(VarConstant.DEFAULT_LOCALE, e.getMessage(), null));
		}
        return result;
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryOrgStorageList", method=RequestMethod.POST)
    @ApiOperation(value="查询库存组织列表", consumes="application/json")
    public OrgStorageResultApi queryOrgStorageList(@RequestBody OrgStorageListParamsApi params) {
		OrgStorageResultApi result = new OrgStorageResultApi();//显示的结果集
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
        	OrgStorageListParams orgStorageListParams = params.getParams();
        	if(orgStorageListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	OrgStorageAdvQuery orgStorageAdvQuery = new OrgStorageAdvQuery();
    	    	BeanUtils.copyProperties(orgStorageListParams, orgStorageAdvQuery);
    	    	List<OrgStorage2> orgStorageList = orgStorageBiz.queryStorage(orgStorageAdvQuery,logicSymbol,pageIndex,ParamHelper.createParam(integratedRequest,"queryOrgStorageList"));
    	    	if(orgStorageList!=null && !orgStorageList.isEmpty()){
    	    		List<OrgStorageResult> resultList = new ArrayList<>();
    	    		for(OrgStorage2 orgStorage:orgStorageList){
    	    			OrgStorageResult orgStorageResult = new OrgStorageResult();
    	    			BeanUtils.copyProperties(orgStorage, orgStorageResult);
    	    			resultList.add(orgStorageResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询库存组织列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryOrgCostCenterList", method=RequestMethod.POST)
    @ApiOperation(value="查询成本中心列表", consumes="application/json")
    public OrgCostCenterResultApi queryOrgCostCenterList(@RequestBody OrgCostCenterListParamsApi params) {
		OrgCostCenterResultApi result = new OrgCostCenterResultApi();//显示的结果集
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
        	OrgCostCenterListParams orgCostCenterListParams = params.getParams();
        	if(orgCostCenterListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	OrgCostCenterAdvQuery orgCostCenterAdvQuery = new OrgCostCenterAdvQuery();
    	    	BeanUtils.copyProperties(orgCostCenterListParams, orgCostCenterAdvQuery);
    	    	List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.queryCostCenter(orgCostCenterAdvQuery,logicSymbol,pageIndex,ParamHelper.createParam(integratedRequest,"queryOrgCostCenterList"));
    	    	if(orgCostCenterList!=null && !orgCostCenterList.isEmpty()){
    	    		List<OrgCostCenterResult> resultList = new ArrayList<>();
    	    		for(OrgCostCenter2 orgCostCenter:orgCostCenterList){
    	    			OrgCostCenterResult orgCostCenterResult = new OrgCostCenterResult();
    	    			BeanUtils.copyProperties(orgCostCenter, orgCostCenterResult);
    	    			resultList.add(orgCostCenterResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询成本中心列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryOrgAdminList", method=RequestMethod.POST)
    @ApiOperation(value="查询部门列表", consumes="application/json")
	public OrgAdminResultApi queryOrgAdminList(@RequestBody OrgAdminListParamsApi params) {
		OrgAdminResultApi result = new OrgAdminResultApi();//显示的结果集
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
        	OrgAdminListParams orgAdminListParams = params.getParams();
        	if(orgAdminListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Page page = new Page();
    	        page.setModelClass(OrgAdmin2.class);
    	        if (pageIndex == -1) {
    				page.setPagePos(1);
    				page.setShowCount(Integer.MAX_VALUE);
    			} else {
    				page.setPagePos(pageIndex);
    				page.setShowCount(20);
    			}
    	    	Param param = ParamHelper.createParam(integratedRequest,"queryOrgAdminList");
    	    	ArrayList argList = new ArrayList();
    	        argList.add("orgUnitNo=" + param.getControlUnitNo());
    	        argList.add("controlUnitNo=" + param.getControlUnitNo());
    	        if(StringUtils.isNotBlank(orgAdminListParams.getMixParam())){
    				page.setSqlCondition("(OrgBaseUnit.orgUnitNo like '%"+orgAdminListParams.getMixParam()+"%' or OrgBaseUnit.orgUnitName like '%"+orgAdminListParams.getMixParam()+"%')");
    			}
    	        List<OrgAdmin2> orgAdminList = orgAdminBiz.queryPage(page, argList, "selectByOrgChildPage", param);
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<OrgAdminResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			OrgAdminResult orgAdminResult = new OrgAdminResult();
    	    			BeanUtils.copyProperties(orgAdmin, orgAdminResult);
    	    			resultList.add(orgAdminResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询部门列表:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="supplierPlatApi")
    @ResponseBody
    @RequestMapping(value="/queryOrgCompany", method=RequestMethod.POST)
    @ApiOperation(value="查询财务组织", consumes="application/json")
    public OrgCompanyResultApi queryOrgCompany(@RequestBody OrgCompanyParamsApi params) {
		OrgCompanyResultApi result = new OrgCompanyResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	OrgCompanyParams orgCompanyListParams = params.getParams();
        	if(orgCompanyListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    			if(StringUtils.isNotBlank(orgCompanyListParams.getOrgUnitNo())){
    				integratedRequest.setOrgUnitNo(orgCompanyListParams.getOrgUnitNo());
    				Param param = ParamHelper.createParam(integratedRequest,"queryOrgCompany");
    				OrgBaseUnit orgBaseUnit= orgUnitBiz.selectbyOrgNo(param.getControlUnitNo(), param);
    				if(orgBaseUnit != null){
    					OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
    					BeanUtils.copyProperties(orgBaseUnit, orgCompanyResult);
    					ScmSupplierDemander scmSupplierDemander =scmSupplierDemanderBiz.selectByCtrl(param.getControlUnitNo(), param);
    					String uniqueNo = Calendar.getInstance().getTimeInMillis() + StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    					if(scmSupplierDemander == null){
    						scmSupplierDemander = new ScmSupplierDemander(true);
    						scmSupplierDemander.setDemanderId(0);
    						scmSupplierDemander.setUniqueNo(uniqueNo);
    						scmSupplierDemander.setControlUnitNo(param.getControlUnitNo());
    						scmSupplierDemanderBiz.add(scmSupplierDemander, param);
    						orgCompanyResult.setUniqueNo(uniqueNo);
    					}else{
    						if(scmSupplierDemander != null && StringUtils.isBlank(scmSupplierDemander.getUniqueNo())){
    							scmSupplierDemander.setUniqueNo(uniqueNo);
    							scmSupplierDemanderBiz.update(scmSupplierDemander, param);
    						}
    						orgCompanyResult.setUniqueNo(scmSupplierDemander.getUniqueNo());
    					}
    					result.setResult(orgCompanyResult);
    				}
    				/*OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(orgCompanyListParams.getOrgUnitNo(), param);
    				if(orgCompany != null){
    					OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
    					BeanUtils.copyProperties(orgCompany, orgCompanyResult);
    					result.setResult(orgCompanyResult);
    				}else{
    					Page page = new Page();
    					page.setModelClass(OrgCompany2.class);
    					page.setPagePos(1);
    					page.setShowCount(Integer.MAX_VALUE);
    					List<String> arglist = new ArrayList<>();
    					//行政记账委托
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.ADMINTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//采购记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.PURTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//库存记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//成本记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.COSTTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//资源记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.RESTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//平台记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.PLATTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//服务记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.SRVTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//营销记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.SALETOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    					//积分记账委托
    					arglist.clear();
    					arglist.add("type=to");
    					arglist.add("relationType="+OrgUnitRelationType.POINTTOFIN);
    					arglist.add("fromOrgUnitNo="+orgCompanyListParams.getOrgUnitNo());
    					orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
    					if(orgCompanyList!=null && !orgCompanyList.isEmpty()){
    						OrgCompanyResult orgCompanyResult = new OrgCompanyResult();
        					BeanUtils.copyProperties(orgCompanyList.get(0), orgCompanyResult);
        					result.setResult(orgCompanyResult);
        					return result;
    					}
    				}*/
				}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询财务组织失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryOrgAdminToFinList", method=RequestMethod.POST)
    @ApiOperation(value="查询财务组织委托部门列表", consumes="application/json")
    public OrgAdminToFinResultApi queryOrgAdminToFinList(@RequestBody OrgAdminToFinListParamsApi params) {
		OrgAdminToFinResultApi result = new OrgAdminToFinResultApi();//显示的结果集
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
        	OrgAdminToFinListParams orgAdminToFinListParams = params.getParams();
        	if(orgAdminToFinListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Page page = new Page();
    			page.setModelClass(OrgAdmin2.class);
    			 if (pageIndex == -1) {
     				page.setPagePos(1);
     				page.setShowCount(Integer.MAX_VALUE);
     			} else {
     				page.setPagePos(pageIndex);
     				page.setShowCount(20);
     			}
    	        ArrayList argList = new ArrayList();
    	        argList.add("type=from");
    	        argList.add("relationType=" + OrgUnitRelationType.ADMINTOFIN);
    	        argList.add("toOrgUnitNo=" + orgAdminToFinListParams.getFinOrgUnitNo());
    	        if(StringUtils.isNotBlank(orgAdminToFinListParams.getMixParam())){
    				page.setSqlCondition("(OrgBaseUnit.orgUnitNo like '%"+orgAdminToFinListParams.getMixParam()+"%' or OrgBaseUnit.orgUnitName like '%"+orgAdminToFinListParams.getMixParam()+"%')");
    			}
    	        List<OrgAdmin2> orgAdminList = orgAdminBiz.queryPage(page, argList, "queryPageEx", ParamHelper.createParam(integratedRequest,"queryOrgAdminToFinList"));
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<OrgAdminToFinResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			OrgAdminToFinResult orgAdminToFinResult = new OrgAdminToFinResult();
    	    			BeanUtils.copyProperties(orgAdmin, orgAdminToFinResult);
    	    			resultList.add(orgAdminToFinResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询财务组织委托部门列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
}
