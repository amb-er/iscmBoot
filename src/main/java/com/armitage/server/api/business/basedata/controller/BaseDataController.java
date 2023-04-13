package com.armitage.server.api.business.basedata.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.armitage.server.api.business.basedata.params.InvMaterialReqOrgAdminListParams;
import com.armitage.server.api.business.basedata.params.InvMaterialReqOrgAdminListParamsApi;
import com.armitage.server.api.business.basedata.params.InvMaterialReqOrgStorageListParams;
import com.armitage.server.api.business.basedata.params.InvMaterialReqOrgStorageListParamsApi;
import com.armitage.server.api.business.basedata.params.InvMaterialReqWareHouseParams;
import com.armitage.server.api.business.basedata.params.InvMaterialReqWareHouseParamsApi;
import com.armitage.server.api.business.basedata.params.InvOrgMaterialListParams;
import com.armitage.server.api.business.basedata.params.InvOrgMaterialListParamsApi;
import com.armitage.server.api.business.basedata.params.MaterialClassListParams;
import com.armitage.server.api.business.basedata.params.MaterialClassListParamsApi;
import com.armitage.server.api.business.basedata.params.MaterialDetailedParams;
import com.armitage.server.api.business.basedata.params.MaterialDetailedParamsApi;
import com.armitage.server.api.business.basedata.params.MaterialListParams;
import com.armitage.server.api.business.basedata.params.MaterialListParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierAddParams;
import com.armitage.server.api.business.basedata.params.SupplierAddParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierBindDemanderParams;
import com.armitage.server.api.business.basedata.params.SupplierBindDemanderParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierToFinListParams;
import com.armitage.server.api.business.basedata.params.SupplierToFinListParamsApi;
import com.armitage.server.api.business.basedata.params.SupplierUpdateParams;
import com.armitage.server.api.business.basedata.params.SupplierUpdateParamsApi;
import com.armitage.server.api.business.basedata.params.TaxRateListParamsApi;
import com.armitage.server.api.business.basedata.params.WareHouseParams;
import com.armitage.server.api.business.basedata.params.WareHouseParamsApi;
import com.armitage.server.api.business.basedata.result.InvMaterialReqOrgAdminResult;
import com.armitage.server.api.business.basedata.result.InvMaterialReqOrgAdminResultApi;
import com.armitage.server.api.business.basedata.result.InvMaterialReqOrgStorageResult;
import com.armitage.server.api.business.basedata.result.InvMaterialReqOrgStorageResultApi;
import com.armitage.server.api.business.basedata.result.InvMaterialReqWareHouseResult;
import com.armitage.server.api.business.basedata.result.InvMaterialReqWareHouseResultApi;
import com.armitage.server.api.business.basedata.result.InvOrgMaterialListResult;
import com.armitage.server.api.business.basedata.result.InvOrgMaterialListResultApi;
import com.armitage.server.api.business.basedata.result.MaterialClassListResult;
import com.armitage.server.api.business.basedata.result.MaterialClassListResultApi;
import com.armitage.server.api.business.basedata.result.MaterialDetailedList;
import com.armitage.server.api.business.basedata.result.MaterialDetailedResult;
import com.armitage.server.api.business.basedata.result.MaterialDetailedResultApi;
import com.armitage.server.api.business.basedata.result.MaterialListResult;
import com.armitage.server.api.business.basedata.result.MaterialListResultApi;
import com.armitage.server.api.business.basedata.result.SupplierAddResultApi;
import com.armitage.server.api.business.basedata.result.SupplierBindDemanderResultApi;
import com.armitage.server.api.business.basedata.result.SupplierToFinListDetailResult;
import com.armitage.server.api.business.basedata.result.SupplierToFinListResult;
import com.armitage.server.api.business.basedata.result.SupplierToFinListResultApi;
import com.armitage.server.api.business.basedata.result.SupplierUpdateResultApi;
import com.armitage.server.api.business.basedata.result.TaxRateListResult;
import com.armitage.server.api.business.basedata.result.TaxRateListResultApi;
import com.armitage.server.api.business.basedata.result.WareHouseResult;
import com.armitage.server.api.business.basedata.result.WareHouseResultApi;
import com.armitage.server.api.business.security.params.UsrPendingBillTypeQueryParamsApi;
import com.armitage.server.api.business.security.result.UsrPendingBillTypeQueryResult;
import com.armitage.server.api.business.security.result.UsrPendingBillTypeQueryResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierDemanderBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.util.OrgUnitRelationType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/baseData")
@Api(tags="基础数据接口")
public class BaseDataController {
	private static Log log = LogFactory.getLog(BaseDataController.class);
	private ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
	private ScmInvWareHouseBiz scmInvWareHouseBiz = (ScmInvWareHouseBiz) AppContextUtil.getBean("scmInvWareHouseBiz");
	private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
	private ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil.getBean("scmMaterialGroupBiz");
	private PubSysBasicInfoBiz pubSysBasicInfoBiz = (PubSysBasicInfoBiz) AppContextUtil.getBean("pubSysBasicInfoBiz");
	private ScmBillPendingBiz scmBillPendingBiz = (ScmBillPendingBiz) AppContextUtil.getBean("scmBillPendingBiz");
	private OrgAdminBiz orgAdminBiz = (OrgAdminBiz) AppContextUtil.getBean("orgAdminBiz");
	private OrgCompanyBiz orgCompanyBiz = (OrgCompanyBiz) AppContextUtil.getBean("orgCompanyBiz");
	private OrgStorageBiz orgStorageBiz = (OrgStorageBiz) AppContextUtil.getBean("orgStorageBiz");
	private ScmSupplierDemanderBiz scmSupplierDemanderBiz = (ScmSupplierDemanderBiz) AppContextUtil.getBean("scmSupplierDemanderBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryWareHouseList", method=RequestMethod.POST)
    @ApiOperation(value="查询库存组织仓库", consumes="application/json")
     public WareHouseResultApi queryWareHouseList(@RequestBody WareHouseParamsApi params) {
		WareHouseResultApi result = new WareHouseResultApi();//显示的结果集
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
        	WareHouseParams wareHouseParams = params.getParams();
	        if(wareHouseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvWareHouse wareHouse= new ScmInvWareHouse();
	        wareHouse.setWhName(wareHouseParams.getWareHouseName());
	        List<ScmInvWareHouse> scmInvWareHouseList = scmInvWareHouseBiz.queryWareHouseList(wareHouse,pageIndex, ParamHelper.createParam(integratedRequest,"queryWareHouseList"));
	        if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
	        	List< WareHouseResult > resultList = new ArrayList<>();
	        	for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
	        		WareHouseResult wareHouseResult = new WareHouseResult();
	        		wareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
	        		wareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
	        		resultList.add(wareHouseResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询仓库失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialClassList", method=RequestMethod.POST)
    @ApiOperation(value="查询物资类别列表", consumes="application/json",position=4)
    public MaterialClassListResultApi queryMaterialClassList(@RequestBody MaterialClassListParamsApi params) {
		MaterialClassListResultApi result = new MaterialClassListResultApi();//显示的结果集
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
        	MaterialClassListParams materialClassListParams = params.getParams();
        	if(materialClassListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmMaterialGroupAdvQuery scmMaterialGroupAdvQuery = new ScmMaterialGroupAdvQuery();
    	    	BeanUtils.copyProperties(materialClassListParams, scmMaterialGroupAdvQuery);
    	    	List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.queryMaterialClassList(scmMaterialGroupAdvQuery, pageIndex,ParamHelper.createParam(integratedRequest,"queryMaterialClassList"));
    	    	if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()){
    	    		List< MaterialClassListResult > resultList = new ArrayList<>();
    	    		for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList){
    	    			MaterialClassListResult materialClassListResult = new MaterialClassListResult();
    	    			BeanUtils.copyProperties(scmMaterialGroup, materialClassListResult);
    	    			resultList.add(materialClassListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("查询物资类别列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="查询物资编码列表", consumes="application/json",position=4)
    public MaterialListResultApi queryMaterialList(@RequestBody MaterialListParamsApi params) {
		MaterialListResultApi result = new MaterialListResultApi();//显示的结果集
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
        	MaterialListParams materialListParams = params.getParams();
        	if(materialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmMaterialAdvQuery scmMaterialAdvQuery = new ScmMaterialAdvQuery();
    	    	BeanUtils.copyProperties(materialListParams, scmMaterialAdvQuery);
    	    	List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryMaterialList(scmMaterialAdvQuery, pageIndex,ParamHelper.createParam(integratedRequest,"queryMaterialList"));
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<MaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			MaterialListResult materialListResult = new MaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, materialListResult);
    	    			resultList.add(materialListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("查询物资编码列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialListByControlUnitNo", method=RequestMethod.POST)
    @ApiOperation(value="查询物资编码列表", consumes="application/json",position=4)
    public MaterialListResultApi queryMaterialListByControlUnitNo(@RequestBody MaterialListParamsApi params) {
		MaterialListResultApi result = new MaterialListResultApi();//显示的结果集
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
        	MaterialListParams materialListParams = params.getParams();
        	if(materialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmMaterialAdvQuery scmMaterialAdvQuery = new ScmMaterialAdvQuery();
    	    	BeanUtils.copyProperties(materialListParams, scmMaterialAdvQuery);
				Param param = ParamHelper.createParam(integratedRequest,"queryMaterialList");
				param.setControlUnitNo(integratedRequest.getOrgUnitNo());
    	    	List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryMaterialList(scmMaterialAdvQuery, pageIndex,param);
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<MaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			MaterialListResult materialListResult = new MaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, materialListResult);
    	    			resultList.add(materialListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("查询物资编码列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	

	@ApiVersion(group="supplierPlatApi")
    @ResponseBody
    @RequestMapping(value="/bindSupplierDemander", method=RequestMethod.POST)
    @ApiOperation(value="绑定供应商平台需求方", consumes="application/json",position=4)
    public SupplierBindDemanderResultApi bindSupplierDemander(@RequestBody SupplierBindDemanderParamsApi params) {
		SupplierBindDemanderResultApi result = new SupplierBindDemanderResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierBindDemanderParams supplierBindDemanderParams = params.getParams();
        	if(supplierBindDemanderParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(supplierBindDemanderParams.getOrgUnitNo()) || supplierBindDemanderParams.getDemanderId() <= 0){
    	    		result.setErrCode("-1");
    	    		result.setErrText("绑定供应商平台需求方失败: 需求方组织为空或需求方ID不大于0！");
    	    	}else{
    	    		integratedRequest.setOrgUnitNo(supplierBindDemanderParams.getOrgUnitNo());
    				Param param = ParamHelper.createParam(integratedRequest,"bindSupplierDemander");
    	    		ScmSupplierDemander scmSupplierDemander = scmSupplierDemanderBiz.addByIdAndCtrl(supplierBindDemanderParams.getDemanderId(), param.getControlUnitNo(), param);
        	    	if(scmSupplierDemander!=null){
        	    		return result;
        	    	}else{
        	    		result.setErrCode("-1");
        	    		result.setErrText("绑定供应商平台需求方失败: 已存在绑定关系！");
        	    	}
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("绑定供应商平台需求方失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryTaxRateList", method=RequestMethod.POST)
    @ApiOperation(value="查询税率列表", consumes="application/json",position=4)
    public TaxRateListResultApi queryTaxRateList(@RequestBody TaxRateListParamsApi params) {
		TaxRateListResultApi result = new TaxRateListResultApi();//显示的结果集
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
	    	List<PubSysBasicInfo> pubSysBasicInfoList = pubSysBasicInfoBiz.queryTaxRateList(pageIndex, ParamHelper.createParam(integratedRequest,"queryTaxRateList"));
	    	if(pubSysBasicInfoList!=null && !pubSysBasicInfoList.isEmpty()){
	    		List<TaxRateListResult> resultList = new ArrayList<>();
	    		for(PubSysBasicInfo pubSysBasicInfo:pubSysBasicInfoList){
	    			TaxRateListResult taxRateListResult = new TaxRateListResult();
	    			taxRateListResult.setCode(pubSysBasicInfo.getFInfoNo());
	    			taxRateListResult.setName(pubSysBasicInfo.getFInfoName());
	    			taxRateListResult.setValues(new BigDecimal(pubSysBasicInfo.getFValue()));
	    			resultList.add(taxRateListResult);
	    		}
	    		result.setResultList(resultList);
	    	}
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取税率列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="oaApi")
    @ResponseBody
    @RequestMapping(value="/doAddSupplier", method=RequestMethod.POST)
    @ApiOperation(value="新增供应商", consumes="application/json",position=3)
    public SupplierAddResultApi doAddSupplier(@RequestBody SupplierAddParamsApi params) {
		SupplierAddResultApi result = new SupplierAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierAddParams supplierAddParams = params.getParams();
        	if (supplierAddParams.isExternal()) {
				if (StringUtils.isBlank(supplierAddParams.getExternalCode())) {
					throw new AppException("iscm.server.BaseDataController.doAddSupplier.externalCode");
				}
			}
        	if(supplierAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	scmsupplierBiz.doAddSupplier(supplierAddParams, ParamHelper.createParam(integratedRequest,"doAddSupplier"));    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("新增供应商失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/doUpdateSupplier", method=RequestMethod.POST)
    @ApiOperation(value="修改供应商", consumes="application/json",position=3)
    public SupplierUpdateResultApi doUpdateSupplier(@RequestBody SupplierUpdateParamsApi params) {
		SupplierUpdateResultApi result = new SupplierUpdateResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierUpdateParams supplierUpdateParams = params.getParams();
        	if(supplierUpdateParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if (StringUtils.isEmpty(supplierUpdateParams.getExternalCode()) && StringUtils.isEmpty(supplierUpdateParams.getVendorNo())) {
    	    		throw new AppException("iscm.Scmsupplier.doUpdateSupplier.error.paramesEmptyCode");
				}
    	    	scmsupplierBiz.doUpdateSupplier(supplierUpdateParams, ParamHelper.createParam(integratedRequest,"doUpdateSupplier"));    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("修改供应商失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/querySupplierToFinList", method=RequestMethod.POST)
    @ApiOperation(value="查询供应商列表（财务接口）", consumes="application/json")
    public SupplierToFinListResultApi querySupplierToFinList(@RequestBody SupplierToFinListParamsApi params) {
		SupplierToFinListResultApi result = new SupplierToFinListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupplierToFinListParams supplierToFinListParams = params.getParams();
	        if(supplierToFinListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Scmsupplier2 scmsupplier = new Scmsupplier2(true);
	        BeanUtils.copyProperties(supplierToFinListParams, scmsupplier);
	        HashMap<String, List<Scmsupplier2>> supplierToFinMap = scmsupplierBiz.selectSupplierToFinList(scmsupplier,  ParamHelper.createParam(integratedRequest,"querySupplierToFinList"));
	        if(supplierToFinMap!=null && !supplierToFinMap.isEmpty()){
	        	List<SupplierToFinListResult> resultList = new ArrayList();
	        	for(String key : supplierToFinMap.keySet()){
	        		List<Scmsupplier2> scmsupplierToFinList = (List<Scmsupplier2>)supplierToFinMap.get(key);
	        		SupplierToFinListResult supplierToFinListResult = new SupplierToFinListResult();
	        		supplierToFinListResult.setClassCode(key.split("_")[0]);
	        		supplierToFinListResult.setClassName(key.split("_")[1]);
	        		supplierToFinListResult.setControlUnitNo(supplierToFinListParams.getControlUnitNo());
	        		if(scmsupplierToFinList != null && !scmsupplierToFinList.isEmpty()){
	        			List<SupplierToFinListDetailResult> detailResultList = new ArrayList();
	        			for(Scmsupplier2 scmsupplier2 : scmsupplierToFinList){
	        				SupplierToFinListDetailResult supplierToFinListDetailResult = new SupplierToFinListDetailResult();
	        				BeanUtils.copyProperties(scmsupplier2, supplierToFinListDetailResult);
	        				detailResultList.add(supplierToFinListDetailResult);
	        			}
	        			supplierToFinListResult.setResultList(detailResultList);
	        		}
	        		resultList.add(supplierToFinListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询供应商列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialDetailedList", method=RequestMethod.POST)
    @ApiOperation(value="查询物资明细", consumes="application/json")
	 public MaterialDetailedResultApi queryMaterialDetailedList(@RequestBody MaterialDetailedParamsApi params) {
		MaterialDetailedResultApi result = new MaterialDetailedResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	MaterialDetailedParams materialDetailedParams = params.getParams();
        	if(materialDetailedParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
			    Param param=ParamHelper.createParam(integratedRequest,"findByFinAllList");
			    String controlUnitNo=materialDetailedParams.getControlUnitNo();
			    if(StringUtils.isBlank(controlUnitNo)){
			    	controlUnitNo = param.getControlUnitNo(); 
			    }
    	    	List<ScmMaterial2> scmMaterialList = scmMaterialBiz.findByFinAllList(controlUnitNo,materialDetailedParams.getGroupLevel(), param);
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<MaterialDetailedResult> resultList = new ArrayList<>();
    	    		HashMap<String,List<ScmMaterial2>> materialMap=new HashMap<>();
    		        for (ScmMaterial2 scmMaterial2 : scmMaterialList) {
    		        	String key = scmMaterial2.getClassCode()+"_"+scmMaterial2.getClassName();
    		        	if(materialMap.containsKey(key)){
    		        		List<ScmMaterial2> scmMaterialToFinList = (List<ScmMaterial2>)materialMap.get(key);
    		        		scmMaterialToFinList.add(scmMaterial2);
    		        	}else{
    		        		List<ScmMaterial2> scmMaterialToFinList = new ArrayList<>();
    		        		scmMaterialToFinList.add(scmMaterial2);
    		        		materialMap.put(key, scmMaterialToFinList);
    		        	}
    		        }
    		        if(materialMap != null && !materialMap.isEmpty()){
	        			for(String key : materialMap.keySet()){
	        				List<ScmMaterial2> scmMaterialToFinList = (List<ScmMaterial2>)materialMap.get(key);
        					MaterialDetailedResult materialDetailedResult = new MaterialDetailedResult();
        		        	materialDetailedResult.setControlUnitNo(param.getControlUnitNo());
        		        	materialDetailedResult.setClassCode(key.split("_")[0]);
        		        	materialDetailedResult.setClassName(key.split("_")[1]);
        		        	List<MaterialDetailedList> list = new ArrayList<>();
        		        	for (ScmMaterial2 scmMaterialEntity2 : scmMaterialToFinList) {
        		        		MaterialDetailedList materialDetailedList = new MaterialDetailedList();
        		        		BeanUtils.copyProperties(scmMaterialEntity2, materialDetailedList);
        		        		int groupLevel = ((scmMaterialEntity2.getLongNo()).split(",")).length;
        		        		materialDetailedList.setGroupLevel(groupLevel);
        		        		list.add(materialDetailedList);
        		        	}
        		        	materialDetailedResult.setDetailedList(list);
        		        	resultList.add(materialDetailedResult);
	        			}
	        		}
    		        result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("查询物资明细失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPendingBillType", method=RequestMethod.POST)
    @ApiOperation(value="获取单据类型", consumes="application/json")
    public UsrPendingBillTypeQueryResultApi queryPendingBillType(@RequestBody UsrPendingBillTypeQueryParamsApi params) {
		UsrPendingBillTypeQueryResultApi result = new UsrPendingBillTypeQueryResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
	    	List<ScmBillPending2> scmBillPendingList = scmBillPendingBiz.queryPendingBillType(ParamHelper.createParam(integratedRequest,"queryPendingBillType"));
	    	if(scmBillPendingList!=null && !scmBillPendingList.isEmpty()){
	    		List<UsrPendingBillTypeQueryResult> resultList = new ArrayList<>();
	    		for(ScmBillPending2 scmBillPending:scmBillPendingList){
	    			UsrPendingBillTypeQueryResult usrPendingBillTypeResult = new UsrPendingBillTypeQueryResult();
	    			BeanUtils.copyProperties(scmBillPending,usrPendingBillTypeResult);
	    			resultList.add(usrPendingBillTypeResult);
	    		}
	    		result.setResultList(resultList);
	    	}
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("获取单据类型失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result;  
	}
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMaterialReqWareHouseList", method=RequestMethod.POST)
    @ApiOperation(value="查询领用仓库", consumes="application/json")
     public InvMaterialReqWareHouseResultApi queryWareHouseList(@RequestBody InvMaterialReqWareHouseParamsApi params) {
		InvMaterialReqWareHouseResultApi result = new InvMaterialReqWareHouseResultApi();//显示的结果集
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
        	InvMaterialReqWareHouseParams invMaterialReqInvMaterialReqWareHouseParams = params.getParams();
	        if(invMaterialReqInvMaterialReqWareHouseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Page page = new Page();
	        page.setModelClass(ScmInvWareHouse.class);
	        if (pageIndex == -1) {
				page.setPagePos(1);
				page.setShowCount(Integer.MAX_VALUE);
			} else {
				page.setPagePos(pageIndex);
				page.setShowCount(20);
			}
	        List<ScmInvWareHouse> scmInvWareHouseList = new ArrayList<>();
	        if(StringUtils.isNotBlank(invMaterialReqInvMaterialReqWareHouseParams.getInvOrgUnitNo())){
	        	Param param = ParamHelper.createParam(integratedRequest,"queryInvMaterialReqWareHouseList");
				List<String> arglist = new ArrayList<>();
				arglist.add("orgUnitNo="+invMaterialReqInvMaterialReqWareHouseParams.getInvOrgUnitNo());
				if(StringUtils.isNotBlank(invMaterialReqInvMaterialReqWareHouseParams.getMixParam())){
    				page.setSqlCondition("(scminvwarehouse.whNo like '%"+invMaterialReqInvMaterialReqWareHouseParams.getMixParam()+"%' or scminvwarehouse.whName like '%"+invMaterialReqInvMaterialReqWareHouseParams.getMixParam()+"%')");
    			}
				scmInvWareHouseList = scmInvWareHouseBiz.queryPage(page, arglist, "selectByOrgUnitNoPage", param);
				if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
		        	List<InvMaterialReqWareHouseResult> resultList = new ArrayList<>();
		        	for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
		        		InvMaterialReqWareHouseResult invMaterialReqWareHouseResult = new InvMaterialReqWareHouseResult();
		        		invMaterialReqWareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
		        		invMaterialReqWareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
		        		resultList.add(invMaterialReqWareHouseResult);
		        	}
		        	result.setResultList(resultList);
		        }
	        }
        } catch (Exception e) {
    		result.setErrCode("-1");
    		result.setErrText("查询领用仓库失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
    		e.printStackTrace();
        }
        return result; 
    	
    }
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialReqOrgStorageList", method=RequestMethod.POST)
    @ApiOperation(value="查询领用库存组织列表", consumes="application/json")
    public InvMaterialReqOrgStorageResultApi queryOrgStorageList(@RequestBody InvMaterialReqOrgStorageListParamsApi params) {
		InvMaterialReqOrgStorageResultApi result = new InvMaterialReqOrgStorageResultApi();//显示的结果集
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
        	InvMaterialReqOrgStorageListParams invMaterialReqOrgStorageListParams = params.getParams();
        	if(invMaterialReqOrgStorageListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Page page = new Page();
    	        page.setModelClass(OrgStorage2.class);
    	        if (pageIndex == -1) {
    				page.setPagePos(1);
    				page.setShowCount(Integer.MAX_VALUE);
    			} else {
    				page.setPagePos(pageIndex);
    				page.setShowCount(20);
    			}
    	        List<OrgStorage2> orgStorageList = new ArrayList<>();
    	        if(StringUtils.isNotBlank(invMaterialReqOrgStorageListParams.getUseOrgUnitNo())){
    	        	Param param = ParamHelper.createParam(integratedRequest,"queryMaterialReqOrgStorageList");
    				List<String> arglist = new ArrayList<>();
    				arglist.add("type=to");
    				arglist.add("relationType="+OrgUnitRelationType.ADMINTOINV);
    				arglist.add("fromOrgUnitNo="+invMaterialReqOrgStorageListParams.getUseOrgUnitNo());
    				if(StringUtils.isNotBlank(invMaterialReqOrgStorageListParams.getMixParam())){
	    				page.setSqlCondition("(OrgBaseUnit.orgUnitNo like '%"+invMaterialReqOrgStorageListParams.getMixParam()+"%' or OrgBaseUnit.orgUnitName like '%"+invMaterialReqOrgStorageListParams.getMixParam()+"%')");
	    			}
    				orgStorageList = orgStorageBiz.queryPage(page, arglist, "queryPageEx", param);
        	    	if(orgStorageList!=null && !orgStorageList.isEmpty()){
        	    		List<InvMaterialReqOrgStorageResult> resultList = new ArrayList<>();
        	    		for(OrgStorage2 orgStorage:orgStorageList){
        	    			InvMaterialReqOrgStorageResult invMaterialReqOrgStorageResult = new InvMaterialReqOrgStorageResult();
        	    			BeanUtils.copyProperties(orgStorage, invMaterialReqOrgStorageResult);
        	    			resultList.add(invMaterialReqOrgStorageResult);
        	    		}
        	    		result.setResultList(resultList);
        	    	}
    	        }
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询领用库存组织列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/queryMaterialReqOrgAdminList", method=RequestMethod.POST)
    @ApiOperation(value="查询领料部门列表", consumes="application/json")
	public InvMaterialReqOrgAdminResultApi queryOrgAdminList(@RequestBody InvMaterialReqOrgAdminListParamsApi params) {
		InvMaterialReqOrgAdminResultApi result = new InvMaterialReqOrgAdminResultApi();//显示的结果集
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
        	InvMaterialReqOrgAdminListParams invMaterialReqOrgAdminListParams = params.getParams();
        	if(invMaterialReqOrgAdminListParams==null){
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
    	    	Param param = ParamHelper.createParam(integratedRequest,"queryMaterialReqOrgAdminList");
    	    	//根据资源组织获取财务组织，再通过财务组织获取其下的所有行政组织
    	    	Page finPage = new Page();
    	    	finPage.setModelClass(OrgCompany2.class);
    	    	finPage.setShowCount(Integer.MAX_VALUE);
				List<String> finArglist = new ArrayList<>();
				finArglist.add("type=to");
				finArglist.add("relationType="+OrgUnitRelationType.RESTOFIN);
				finArglist.add("fromOrgUnitNo="+param.getOrgUnitNo());
				List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(finPage, finArglist, "queryPageEx", param);
				List<OrgAdmin2> orgAdminList = new ArrayList<>();
				if(orgCompanyList != null && !orgCompanyList.isEmpty()){
					List<String> argList = new ArrayList<>();
					argList.add("type=from");
					argList.add("relationType="+OrgUnitRelationType.ADMINTOFIN);
					argList.add("toOrgUnitNo="+orgCompanyList.get(0).getOrgUnitNo());
					if(StringUtils.isNotBlank(invMaterialReqOrgAdminListParams.getMixParam())){
	    				page.setSqlCondition("(OrgBaseUnit.orgUnitNo like '%"+invMaterialReqOrgAdminListParams.getMixParam()+"%' or OrgBaseUnit.orgUnitName like '%"+invMaterialReqOrgAdminListParams.getMixParam()+"%')");
	    			}
					orgAdminList = orgAdminBiz.queryPage(page, argList, "queryPageEx", param);
				}
    	    	if(orgAdminList!=null && !orgAdminList.isEmpty()){
    	    		List<InvMaterialReqOrgAdminResult> resultList = new ArrayList<>();
    	    		for(OrgAdmin2 orgAdmin:orgAdminList){
    	    			InvMaterialReqOrgAdminResult invMaterialReqOrgAdminResult = new InvMaterialReqOrgAdminResult();
    	    			BeanUtils.copyProperties(orgAdmin, invMaterialReqOrgAdminResult);
    	    			resultList.add(invMaterialReqOrgAdminResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("查询领料部门列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="iespApi")
    @ResponseBody
    @RequestMapping(value="/queryInvOrgMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="根据库存组织查询物资", consumes="application/json")
	public InvOrgMaterialListResultApi queryInvOrgMaterialList(@RequestBody InvOrgMaterialListParamsApi params) {
		InvOrgMaterialListResultApi result = new InvOrgMaterialListResultApi();//显示的结果集
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
        	InvOrgMaterialListParams invOrgMaterialListParams = params.getParams();
        	if(invOrgMaterialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	        if (StringUtils.isNotBlank(invOrgMaterialListParams.getInvOrgUnitNo())) {
    	        	List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryInvOrgMaterialList(invOrgMaterialListParams,pageIndex,ParamHelper.createParam(integratedRequest,"queryInvOrgMaterialList"));
    	        	if (scmMaterialList != null && !scmMaterialList.isEmpty()) {
    	        		List<InvOrgMaterialListResult> resultList = new ArrayList<>();
    	        		for (ScmMaterial2 scmMaterial : scmMaterialList) {
    	        			InvOrgMaterialListResult invOrgMaterialListResult = new InvOrgMaterialListResult();
    	        			BeanUtils.copyProperties(scmMaterial, invOrgMaterialListResult);
    	        			invOrgMaterialListResult.setInvUnitName(scmMaterial.getUnitName());
        	    			resultList.add(invOrgMaterialListResult);
						}
    	        		result.setResultList(resultList);
					}
    	        }else {
					throw new AppException("iscm.BaseDataController.queryInvOrgMaterialList.invOrgUnitNo.isnull");
				}
    	    }
        } catch (Exception e) {
	    	result.setErrCode("-1");
	    	result.setErrText("根据库存组织查询物资列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
		return result;
	}
	
}
