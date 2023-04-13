package com.armitage.server.api.business.datasync.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.armitage.server.api.business.audit.result.AuditStatusResult;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.datasync.params.ScmInvPurInWarehsBillQueryParams;
import com.armitage.server.api.business.datasync.params.ScmInvPurInWarehsBillQueryParamsApi;
import com.armitage.server.api.business.datasync.params.ScmMaterialQueryParam;
import com.armitage.server.api.business.datasync.params.ScmMaterialQueryParamApi;
import com.armitage.server.api.business.datasync.params.ScmPurRequireBillQueryParams;
import com.armitage.server.api.business.datasync.params.ScmPurRequireBillQueryParamsApi;
import com.armitage.server.api.business.datasync.params.ScmSupplierQueryParams;
import com.armitage.server.api.business.datasync.params.ScmSupplierQueryParamsApi;
import com.armitage.server.api.business.datasync.result.ScmInvPurInWarehsBillQueryResult;
import com.armitage.server.api.business.datasync.result.ScmInvPurInWarehsBillQueryResultApi;
import com.armitage.server.api.business.datasync.result.ScmInvPurInWarehsItemsQueryResult;
import com.armitage.server.api.business.datasync.result.ScmMaterialQueryResult;
import com.armitage.server.api.business.datasync.result.ScmMaterialQueryResultApi;
import com.armitage.server.api.business.datasync.result.ScmPurRequireBillQueryResult;
import com.armitage.server.api.business.datasync.result.ScmPurRequireBillQueryResultApi;
import com.armitage.server.api.business.datasync.result.ScmPurRequireItemsQueryResult;
import com.armitage.server.api.business.datasync.result.ScmSupplierQueryResult;
import com.armitage.server.api.business.datasync.result.ScmSupplierQueryResultApi;
import com.armitage.server.api.business.invcounttask.controller.InvCountTaskController;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;
import com.armitage.server.iscm.basedata.model.Scmsupplier;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialInventoryBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.common.model.CommonEventHistory2;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/dateQuery")
@Api(tags="数据查询接口")
public class DateQueryController {
	private static Log log = LogFactory.getLog(InvCountTaskController.class);
	private ScmsupplierBiz scmSupplierbiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
	private ScmsuppliergroupBiz scmsuppliergroupBiz = (ScmsuppliergroupBiz) AppContextUtil.getBean("scmsuppliergroupBiz");
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");
	private ScmMaterialGroupBiz scmMaterialGroupBiz = (ScmMaterialGroupBiz) AppContextUtil.getBean("scmMaterialGroupBiz");
	private ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
	private ScmMaterialInventoryBiz scmMaterialInventoryBiz = (ScmMaterialInventoryBiz) AppContextUtil.getBean("scmMaterialInventoryBiz");
	private ScmMaterialPurchaseBiz scmMaterialPurchaseBiz = (ScmMaterialPurchaseBiz) AppContextUtil.getBean("scmMaterialPurchaseBiz");
	private ScmMeasureUnitBiz scmMeasureUnitBiz = (ScmMeasureUnitBiz) AppContextUtil.getBean("scmMeasureUnitBiz");
    private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz = (ScmInvPurInWarehsBillBiz) AppContextUtil.getBean("scmInvPurInWarehsBillBiz");
    private ScmInvPurInWarehsBillEntryBiz scmInvPurInWarehsBillEntryBiz = (ScmInvPurInWarehsBillEntryBiz) AppContextUtil.getBean("scmInvPurInWarehsBillEntryBiz");
    private ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
    private ScmPurRequireEntryBiz scmPurRequireEntryBiz = (ScmPurRequireEntryBiz) AppContextUtil.getBean("scmPurRequireEntryBiz");
	private CommonEventHistoryBiz commonEventHistoryBiz = (CommonEventHistoryBiz) AppContextUtil.getBean("commonEventHistoryBiz");
    @ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/queryScmSupplierList", method=RequestMethod.POST)
    @ApiOperation(value="查询供应商", consumes="application/json")
    public ScmSupplierQueryResultApi queryScmSupplierList(@RequestBody ScmSupplierQueryParamsApi params) {
		
		ScmSupplierQueryResultApi ResultApi = new ScmSupplierQueryResultApi();
		ScmSupplierQueryParams scmScmSupplierQueryParams = params.getParams();
	    IntegratedRequest integratedRequest = new IntegratedRequest();
	    BeanUtils.copyProperties(params.getIntegratedRequest(), integratedRequest);
	    Param param = ParamHelper.createParam(integratedRequest,"queryScmSupplierList");
	    try {
	    ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        SecurityUtils.verify(integratedRequest);
		} catch (Exception e) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("查询供应商报错:"+e.getMessage());
			return ResultApi;
		}
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(integratedRequest.getOrgUnitNo(), param);
		if(orgBaseUnit==null) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("操作组织不存在");
			return ResultApi;
		}
		List<Scmsuppliergroup> groupList = null;
	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getVendorClassNo())) {
	    	Page page = new Page();
	    	page.setModelClass(Scmsuppliergroup.class);
	    	page.setShowCount(Integer.MAX_VALUE);
	    	String Scmsuppliergroup_controlUnitNo = "a."+ Scmsuppliergroup.FN_CONTROLUNITNO;
	    	String Scmsuppliergroup_classCode = "a."+ Scmsuppliergroup.FN_CLASSCODE;
	    	page.getParam().put(Scmsuppliergroup_controlUnitNo,new QueryParam(Scmsuppliergroup_controlUnitNo, QueryParam.QUERY_EQ, param.getControlUnitNo()));
	    	page.getParam().put(Scmsuppliergroup_classCode, new QueryParam(Scmsuppliergroup_classCode,QueryParam.QUERY_EQ,scmScmSupplierQueryParams.getVendorClassNo()));
	    	List<String> arglist = new ArrayList<>();
	    	groupList = scmsuppliergroupBiz.queryPage(page, arglist,"findAllPage", param);
	        if(groupList==null||groupList.isEmpty()) {
	        	ResultApi.setErrCode("0");
				return ResultApi;
	        }
	    }
	    Page page = new Page();
    	page.setModelClass(Scmsupplier.class);
    	page.setShowCount(Integer.MAX_VALUE);
	    String Scmsupplier_controlUnitNo = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_CONTROLUNITNO;
	    String Scmsupplier_createDate = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_CREATEDATE;
	    String Scmsupplier_vendorNo = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_VENDORNO;
	    String Scmsupplier_status = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_STATUS;
	    String Scmsupplier_classId = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_CLASSID;
	    String Scmsupplier_editDate = ClassUtils.getFinalModelSimpleName(Scmsupplier.class) + "." + Scmsupplier.FN_EDITDATE;
	    page.getParam().put(Scmsupplier_controlUnitNo, new QueryParam(Scmsupplier_controlUnitNo, QueryParam.QUERY_EQ,orgBaseUnit.getControlUnitNo()));
	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getCreateStartDate())) {
	    	page.getParam().put(Scmsupplier_createDate+"_Start", new QueryParam(Scmsupplier_createDate, QueryParam.QUERY_GT, scmScmSupplierQueryParams.getCreateStartDate()));
	    }
	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getCreateEndDate())) {
	    	page.getParam().put(Scmsupplier_createDate+"_End", new QueryParam(Scmsupplier_createDate, QueryParam.QUERY_LE, scmScmSupplierQueryParams.getCreateEndDate()));
	    }
	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getVendorNo())) {
	    	page.getParam().put(Scmsupplier_vendorNo, new QueryParam(Scmsupplier_vendorNo, QueryParam.QUERY_EQ, scmScmSupplierQueryParams.getVendorNo()));
	    }
	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getStatus())) {
	    	page.getParam().put(Scmsupplier_status, new QueryParam(Scmsupplier_status, QueryParam.QUERY_EQ, scmScmSupplierQueryParams.getStatus()));
	    }
	    if(groupList!=null&&groupList.size()>0) {
	    	page.getParam().put(Scmsupplier_classId, new QueryParam(Scmsupplier_classId, QueryParam.QUERY_EQ, String.valueOf(groupList.get(0).getId())));
	    }
   	    if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getEditStartDate())){
   	    	page.getParam().put("editStartDate", new QueryParam(Scmsupplier_editDate, QueryParam.QUERY_GT, scmScmSupplierQueryParams.getEditStartDate()));
   	    }
   	   if(StringUtils.isNotBlank(scmScmSupplierQueryParams.getEditEndDate())){
   		    page.getParam().put("editEndDate", new QueryParam(Scmsupplier_editDate, QueryParam.QUERY_LE, scmScmSupplierQueryParams.getEditEndDate()));
  	    }
		
	    List<String> arglist = new ArrayList<>();
	    List<Scmsupplier2> ScmsupplierList =  scmSupplierbiz.queryPage(page, arglist, "findAllPage", param);
	    if(ScmsupplierList==null||ScmsupplierList.size()==0) {
	    	ResultApi.setErrCode("0");
			return ResultApi;
	    }
	    StringBuffer supplierGroup = new StringBuffer();
	    
	    for(Scmsupplier2 Scmsupplier2:ScmsupplierList) {
               if(StringUtils.isNotBlank(supplierGroup.toString()))   	    	
            	   supplierGroup.append(",");
             
               supplierGroup.append(String.valueOf(Scmsupplier2.getClassId()));
	    }
	    List<String> ids = new ArrayList<>();
	    ids.add("item="+supplierGroup.toString());
	    Page grpupPage = new Page();
	    grpupPage.setModelClass(Scmsuppliergroup.class);
	    grpupPage.setShowCount(Integer.MAX_VALUE);
	    List<Scmsuppliergroup2> scmsuppliergroupList = scmsuppliergroupBiz.queryPage(grpupPage, ids,"selectByIds", param);
	    HashMap<String,Scmsuppliergroup2>scmsuppliergroup =  new HashMap<String, Scmsuppliergroup2>();
	    for(Scmsuppliergroup2 scmsuppliergroup2 :scmsuppliergroupList) {
	    	scmsuppliergroup.put(String.valueOf(scmsuppliergroup2.getId()), scmsuppliergroup2);
	    }
	    List< ScmSupplierQueryResult > resuleList = new ArrayList<ScmSupplierQueryResult>();
	    for(Scmsupplier2 scmsupplier:ScmsupplierList) {
	    	ScmSupplierQueryResult resul = new ScmSupplierQueryResult();
	    	BeanUtils.copyProperties(scmsupplier, resul);
	    	resul.setVendorClassNo(scmsuppliergroup.get(String.valueOf(scmsupplier.getClassId())).getClassCode());
	    	resul.setVendorClassName(scmsuppliergroup.get(String.valueOf(scmsupplier.getClassId())).getClassName());
	    	resuleList.add(resul);
	    }
	    ResultApi.setResultList(resuleList);
		return ResultApi;
	}
	
	
	
	
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/queryScmMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="查询物资编码", consumes="application/json")
    public ScmMaterialQueryResultApi queryScmMaterialList(@RequestBody ScmMaterialQueryParamApi params) {
		
		ScmMaterialQueryResultApi ResultApi = new ScmMaterialQueryResultApi();
		ScmMaterialQueryParam scmScmMaterialQueryParams = params.getParams();
	    IntegratedRequest integratedRequest = new IntegratedRequest();
	    Param param = null;
	    try {
	    BeanUtils.copyProperties(params.getIntegratedRequest(), integratedRequest);
	    param = ParamHelper.createParam(integratedRequest,"queryScmMaterialList");
	    ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        SecurityUtils.verify(integratedRequest);
		} catch (Exception e) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("查询物资编码:"+e.getMessage());
			return ResultApi;
		}
		if(StringUtils.isBlank(param.getOrgUnitNo())) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("操作组织不能为空");
			return ResultApi;
		}
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(integratedRequest.getOrgUnitNo(), param);
		if(orgBaseUnit==null) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("操作组织不存在");
			return ResultApi;
		}
		List<ScmMaterialGroup> groupList = null;
	    if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getItemClassNo())) {
	    	Page page = new Page();
	    	page.setModelClass(ScmMaterialGroup2.class);
	    	page.setShowCount(Integer.MAX_VALUE);
	    	String scmMaterialGroup_controlUnitNo = ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) +"."+ ScmMaterialGroup2.FN_CONTROLUNITNO;
	    	String scmMaterialGroup_classCode = ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class)+"."+ ScmMaterialGroup2.FN_CLASSCODE;
	    	page.getParam().put(scmMaterialGroup_controlUnitNo,new QueryParam(scmMaterialGroup_controlUnitNo, QueryParam.QUERY_EQ, param.getControlUnitNo()));
	    	page.getParam().put(scmMaterialGroup_classCode, new QueryParam(scmMaterialGroup_classCode,QueryParam.QUERY_EQ,scmScmMaterialQueryParams.getItemClassNo()));
	    	List<String> arglist = new ArrayList<>();
	    	groupList = scmMaterialGroupBiz.queryPage(page, arglist,"findAllPage", param);
	        if(groupList==null||groupList.isEmpty()) {
	        	ResultApi.setErrCode("0");
				return ResultApi;
	        }
	    }
	    Page page = new Page();
    	page.setModelClass(Scmsupplier.class);
    	page.setShowCount(Integer.MAX_VALUE);
    	
    	String scmMaterial_itemNo = ClassUtils.getFinalModelSimpleName(ScmMaterial.class) + "." + ScmMaterial.FN_ITEMNO;
    	String scmMaterialGroup_itemClassNo = ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." + ScmMaterialGroup.FN_ID;
    	String scmMaterial_createDate = ClassUtils.getFinalModelSimpleName(ScmMaterial.class) + "." + ScmMaterial.FN_CREATEDATE;
    	String scmMaterial_editDate = ClassUtils.getFinalModelSimpleName(ScmMaterial.class) + "." + ScmMaterial.FN_EDITDATE;
    	String scmMaterial_controlUnitNo = ClassUtils.getFinalModelSimpleName(ScmMaterial.class) + "." + ScmMaterial.FN_CONTROLUNITNO;
    	String scmMaterial_status = ClassUtils.getFinalModelSimpleName(ScmMaterial.class) + "." + ScmMaterial.FN_STATUS;
    	page.getParam().put(scmMaterial_controlUnitNo, new QueryParam(scmMaterial_controlUnitNo,QueryParam.QUERY_EQ,param.getControlUnitNo()));
    	if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getItemNo())) {
			page.getParam().put(scmMaterial_itemNo, new QueryParam(scmMaterial_itemNo,QueryParam.QUERY_EQ,scmScmMaterialQueryParams.getItemNo()));
		}
        if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getStatus())) {
        	page.getParam().put(scmMaterial_status, new QueryParam(scmMaterial_status,QueryParam.QUERY_EQ,scmScmMaterialQueryParams.getStatus()));
		}
    	if(groupList!=null && !groupList.isEmpty()){
    		page.getParam().put(scmMaterialGroup_itemClassNo,new QueryParam(scmMaterialGroup_itemClassNo, QueryParam.QUERY_EQ, String.valueOf(groupList.get(0).getId())));
    	}
    	if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getCreateStartDate())) {
    		page.getParam().put("createStartDate", new QueryParam(scmMaterial_createDate, QueryParam.QUERY_GT, scmScmMaterialQueryParams.getCreateStartDate()));
    	}
   	    if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getCreateEndDate())) {
   	    	page.getParam().put("createEndDate", new QueryParam(scmMaterial_createDate, QueryParam.QUERY_LE, scmScmMaterialQueryParams.getCreateEndDate()));
    	}
   	    if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getEditStartDate())){
   	    	page.getParam().put("editStartDate", new QueryParam(scmMaterial_editDate, QueryParam.QUERY_GT, scmScmMaterialQueryParams.getEditStartDate()));
   	    }
   	   if(StringUtils.isNotBlank(scmScmMaterialQueryParams.getEditEndDate())){
   		    page.getParam().put("editEndDate", new QueryParam(scmMaterial_editDate, QueryParam.QUERY_LE, scmScmMaterialQueryParams.getEditEndDate()));
  	    }
    	
	    List<String> arglist = new ArrayList<>();
	    arglist.add("orgUnitNo="+param.getControlUnitNo());
	    List<ScmMaterial2> ScmMaterialList =  scmMaterialBiz.queryPage(page, arglist, "findUnitPage", param);
	    if(ScmMaterialList==null||ScmMaterialList.size()==0) {
	    	ResultApi.setErrCode("0");
			return ResultApi;
	    }
	    List< ScmMaterialQueryResult > resuleList = new ArrayList<ScmMaterialQueryResult>();
	    for(ScmMaterial2 scmMaterial:ScmMaterialList) {
	    	ScmMaterialQueryResult resul = new ScmMaterialQueryResult();
	    	BeanUtils.copyProperties(scmMaterial, resul);
	    	resuleList.add(resul);
	    }
	    ResultApi.setResultList(resuleList);
	    
		return ResultApi;
	}
	
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/queryScmInvPurInWarehsBillList", method=RequestMethod.POST)
    @ApiOperation(value="查询物采购入库单", consumes="application/json")
    public ScmInvPurInWarehsBillQueryResultApi queryScmInvPurInWarehsBillList(@RequestBody ScmInvPurInWarehsBillQueryParamsApi params) {
		
		ScmInvPurInWarehsBillQueryResultApi ResultApi = new ScmInvPurInWarehsBillQueryResultApi();
		ScmInvPurInWarehsBillQueryParams scmInvPurInWarehsBillQueryParams = params.getParams();
	    IntegratedRequest integratedRequest = new IntegratedRequest();
	    BeanUtils.copyProperties(params.getIntegratedRequest(), integratedRequest);
	    Param param = ParamHelper.createParam(integratedRequest,"queryScmInvPurInWarehsBillList");
	    try {
		    ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
	        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
	        SecurityUtils.verify(integratedRequest);
			} catch (Exception e) {
				ResultApi.setErrCode("-1");
				ResultApi.setErrText("查询物采购入库单:"+e.getMessage());
				return ResultApi;
			}
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(integratedRequest.getOrgUnitNo(), param);
		if(orgBaseUnit==null) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("操作组织不存在");
			return ResultApi;
		}
		
	    Page page = new Page();
    	page.setModelClass(ScmInvPurInWarehsBill.class);
    	page.setShowCount(Integer.MAX_VALUE);
    	String scmInvPurInWarehsBill_wrNo = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_WRNO;
    	String scmInvPurInWarehsBill_bizDate = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_BIZDATE;
    	String scmInvPurInWarehsBill_controlUnitNo = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_CONTROLUNITNO;
    	String scmInvPurInWarehsBill_status = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_STATUS;
    	String scmInvPurInWarehsBill_orgUnitNo = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_ORGUNITNO;
    	String scmInvPurInWarehsBill_vendorId = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill.class) + "." + ScmInvPurInWarehsBill.FN_VENDORID;
    	String scmInvPurInWarehsBillEntry_wrid = ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry.class) + "." + ScmInvPurInWarehsBillEntry.FN_WRID;

    	page.getParam().put(scmInvPurInWarehsBill_controlUnitNo, new QueryParam(scmInvPurInWarehsBill_controlUnitNo,QueryParam.QUERY_EQ,param.getControlUnitNo()));
    	if(StringUtils.isNotBlank(scmInvPurInWarehsBillQueryParams.getWrNo())) {
    		page.getParam().put(scmInvPurInWarehsBill_wrNo,new QueryParam(scmInvPurInWarehsBill_wrNo,QueryParam.QUERY_EQ,scmInvPurInWarehsBillQueryParams.getWrNo()));
    	}
    	if(StringUtils.isNotBlank(param.getOrgUnitNo())) {
    		page.getParam().put(scmInvPurInWarehsBill_orgUnitNo,new QueryParam(scmInvPurInWarehsBill_orgUnitNo,QueryParam.QUERY_EQ,param.getOrgUnitNo()));
    	}
     	if(StringUtils.isNotBlank(scmInvPurInWarehsBillQueryParams.getStatus())) {
     		page.getParam().put(scmInvPurInWarehsBill_status,new QueryParam(scmInvPurInWarehsBill_status,QueryParam.QUERY_EQ,scmInvPurInWarehsBillQueryParams.getStatus()));
     	}
     	if(StringUtils.isNotBlank(scmInvPurInWarehsBillQueryParams.getBegDate())) {
     		page.getParam().put("begDate",new QueryParam(scmInvPurInWarehsBill_bizDate,QueryParam.QUERY_GT,scmInvPurInWarehsBillQueryParams.getBegDate()));
     	}
     	if(StringUtils.isNotBlank(scmInvPurInWarehsBillQueryParams.getBegDate())) {
     		page.getParam().put("endDate",new QueryParam(scmInvPurInWarehsBill_bizDate,QueryParam.QUERY_LE,scmInvPurInWarehsBillQueryParams.getEndDate()));
     	}
     	
     	if(StringUtils.isNotBlank(scmInvPurInWarehsBillQueryParams.getVendorNo())) {
     		Scmsupplier2 scmSupplier2 = scmSupplierbiz.selectByCode(scmInvPurInWarehsBillQueryParams.getVendorNo(), param);
     		if(scmSupplier2.getId()>0) {
     			page.getParam().put(scmInvPurInWarehsBill_vendorId,new QueryParam(scmInvPurInWarehsBill_vendorId,QueryParam.QUERY_EQ,String.valueOf(scmSupplier2.getId())));
     		}
     		
     	}
    	List<String> arglist = new ArrayList<>();
     	List<ScmInvPurInWarehsBill2> billList = null;
     	StringBuffer supplerId = new StringBuffer();
     	StringBuffer wrId = new StringBuffer();
    	billList = scmInvPurInWarehsBillBiz.queryPage(page, arglist, "findAllPage", param);
    	if(billList==null||billList.isEmpty()) {
    		return ResultApi;
    	}
    	List<ScmInvPurInWarehsBillQueryResult>resultList = new ArrayList<ScmInvPurInWarehsBillQueryResult>();
    	for(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 :billList) {
    		ScmInvPurInWarehsBillQueryResult result = new ScmInvPurInWarehsBillQueryResult();
    		BeanUtils.copyProperties(scmInvPurInWarehsBill2, result);
    		resultList.add(result);
    		if(scmInvPurInWarehsBill2.getVendorId()!=0) {
	    		if(StringUtils.isNotBlank(supplerId.toString()))
	    		    supplerId.append(",");
	    		 supplerId.append(scmInvPurInWarehsBill2.getVendorId());
    		}
    		 if(StringUtils.isNotBlank(wrId.toString()))
    			 wrId.append(",");
    		 wrId.append(String.valueOf(scmInvPurInWarehsBill2.getWrId()));
    	}
        Page supplerPage = new Page();
        supplerPage.setModelClass(Scmsupplier2.class);
        supplerPage.setShowCount(Integer.MAX_VALUE);
    	List<String> supplerParamList = new ArrayList<>();
    	supplerParamList.add("item="+supplerId.toString());
       	List<Scmsupplier2> ScmsupplierList = null;
       	if(StringUtils.isNotBlank(supplerId.toString())) {
       	ScmsupplierList = scmSupplierbiz.queryPage(supplerPage, supplerParamList, "selectByIds", param);
       	}
       	HashMap<Long,Scmsupplier2> supplerMap=new HashMap<Long, Scmsupplier2>(); 
	    for(Scmsupplier2 supple:ScmsupplierList) {
	       		supplerMap.put(supple.getId(), supple);
	    }
        Page entryPage = new Page();
        entryPage.setModelClass(ScmInvPurInWarehsBillEntry.class);
        entryPage.setShowCount(Integer.MAX_VALUE);
    	List<ScmInvPurInWarehsBillEntry2> entryList = null;
    	entryPage.getParam().put(scmInvPurInWarehsBillEntry_wrid,new QueryParam(scmInvPurInWarehsBillEntry_wrid, QueryParam.QUERY_IN, wrId.toString()));
    	entryList = scmInvPurInWarehsBillEntryBiz.queryPage(entryPage, arglist, "findAllPage", param);
    	
    	//Map<String,List<ScmInvPurInWarehsBillEntry2>> mapList = entryList.stream().collect(Collectors.groupingBy(ScmInvPurInWarehsBillEntry2::getWrNo));
    	List<ScmInvPurInWarehsItemsQueryResult> resultItemList = new ArrayList<ScmInvPurInWarehsItemsQueryResult>();
    	for(ScmInvPurInWarehsBillEntry2 entry :entryList) {
    		ScmInvPurInWarehsItemsQueryResult items = new ScmInvPurInWarehsItemsQueryResult();
    		BeanUtils.copyProperties(entry, items);
    		resultItemList.add(items);
    	}
    	Map<String,List<ScmInvPurInWarehsItemsQueryResult>> mapList = resultItemList.stream().collect(Collectors.groupingBy(ScmInvPurInWarehsItemsQueryResult::getWrNo));
       	for(ScmInvPurInWarehsBillQueryResult result:resultList) {
       		result.setVendorName(supplerMap.get(result.getVendorId()).getVendorName());
       		if(ScmsupplierList!=null&&!ScmsupplierList.isEmpty()) {
       		result.setVendorNo(supplerMap.get(result.getVendorId()).getVendorNo());
       		result.setItems(mapList.get(result.getWrNo()));
       		}
       	}
       	ResultApi.setResultList(resultList);
		return ResultApi;
	}
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/queryScmPurRequireBillList", method=RequestMethod.POST)
    @ApiOperation(value="查询物请购单", consumes="application/json")
    public ScmPurRequireBillQueryResultApi queryScmPurRequireBillList(@RequestBody ScmPurRequireBillQueryParamsApi params) {
		
		ScmPurRequireBillQueryResultApi ResultApi = new ScmPurRequireBillQueryResultApi();
		ScmPurRequireBillQueryParams scmPurRequireBillParam = params.getParams();
	    IntegratedRequest integratedRequest = new IntegratedRequest();
	    BeanUtils.copyProperties(params.getIntegratedRequest(), integratedRequest);
	    Param param = ParamHelper.createParam(integratedRequest,"queryScmPurRequireBillList");
	    try {
	    ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
       // SecurityUtils.verify(integratedRequest);
		} catch (Exception e) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("查询物资编码:"+e.getMessage());
			return ResultApi;
		}
		OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(integratedRequest.getOrgUnitNo(), param);
		if(orgBaseUnit==null) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("操作组织不存在");
			return ResultApi;
		}
		
		String scmPurRequire_prNo = ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_PRNO;
		String scmPurRequire_controlUnitNo = ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_CONTROLUNITNO;
		String scmPurRequire_orgUnitNo = ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_ORGUNITNO;
		String scmPurRequire_status = ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_STATUS;
		String scmPurRequire_applyDate = ClassUtils.getFinalModelSimpleName(ScmPurRequire.class) + "." + ScmPurRequire.FN_APPLYDATE;
		String scmPurRequireEntry_prId = ClassUtils.getFinalModelSimpleName(ScmPurRequireEntry.class)+"."+ScmPurRequireEntry.FN_PRID;
	    Page page = new Page();
    	page.setModelClass(ScmPurRequire.class);
    	page.setShowCount(Integer.MAX_VALUE);
    	page.getParam().put(scmPurRequire_controlUnitNo, new QueryParam(scmPurRequire_controlUnitNo, QueryParam.QUERY_EQ, param.getControlUnitNo()));
    	if(StringUtils.isNotBlank(param.getOrgUnitNo())) {
    		page.getParam().put(scmPurRequire_orgUnitNo, new QueryParam(scmPurRequire_orgUnitNo,QueryParam.QUERY_EQ,param.getOrgUnitNo()));
    	}
    	if(StringUtils.isNotBlank(scmPurRequireBillParam.getStatus())) {
    		page.getParam().put(scmPurRequire_status, new QueryParam(scmPurRequire_status,QueryParam.QUERY_EQ,scmPurRequireBillParam.getStatus()));
    	}
    	if(StringUtils.isNotBlank(scmPurRequireBillParam.getPrNo())) {
    		page.getParam().put(scmPurRequire_prNo, new QueryParam(scmPurRequire_prNo,QueryParam.QUERY_EQ,scmPurRequireBillParam.getPrNo()));
    	}
    	if(StringUtils.isNotBlank(scmPurRequireBillParam.getBegDate())) {
    		page.getParam().put("BegDate", new QueryParam(scmPurRequire_applyDate,QueryParam.QUERY_GT,scmPurRequireBillParam.getBegDate()));
    	}
    	if(StringUtils.isNotBlank(scmPurRequireBillParam.getEndDate())) {
    		page.getParam().put("EndDate", new QueryParam(scmPurRequire_applyDate,QueryParam.QUERY_LE,scmPurRequireBillParam.getEndDate()));
    	}
    	List<String> arglist = new ArrayList<>();
    	List<ScmPurRequire2> billList = scmPurRequireBiz.queryPage(page, arglist, "findAllPage", param);
		if(billList==null||billList.size()==0) {
			return ResultApi;
		}
    	
    	List<ScmPurRequireBillQueryResult> resultList= new ArrayList<ScmPurRequireBillQueryResult>();
    	StringBuffer idString = new StringBuffer();
    	for(ScmPurRequire2 bill:billList) {
    		ScmPurRequireBillQueryResult result = new ScmPurRequireBillQueryResult();
    		BeanUtils.copyProperties(bill, result);
    		resultList.add(result);
    		if(StringUtils.isNotBlank(idString.toString())) 
    			idString.append(",");
    		idString.append(String.valueOf(bill.getId()));
    		
    	}
	    Page enrtyPage = new Page();
	    enrtyPage.setModelClass(ScmPurRequireEntry.class);
	    enrtyPage.setShowCount(Integer.MAX_VALUE);
	    enrtyPage.getParam().put(scmPurRequireEntry_prId, new QueryParam(scmPurRequireEntry_prId,QueryParam.QUERY_IN,idString.toString()));
	    List<ScmPurRequireEntry2> entryList = scmPurRequireEntryBiz.queryPage(enrtyPage, arglist, "findAllPage", param);
	    
	    List<ScmPurRequireItemsQueryResult> resultItemList = new ArrayList<ScmPurRequireItemsQueryResult>();
	    StringBuffer supplerId = new StringBuffer();
    	for(ScmPurRequireEntry2 entry :entryList) {
    		if(StringUtils.isNotBlank(supplerId.toString()))
    		    supplerId.append(",");
    		 supplerId.append(entry.getVendorId());
    		ScmPurRequireItemsQueryResult items = new ScmPurRequireItemsQueryResult();
    		BeanUtils.copyProperties(entry, items);
    		resultItemList.add(items);
    	}
    	Page supplerPage = new Page();
        supplerPage.setModelClass(Scmsupplier2.class);
        supplerPage.setShowCount(Integer.MAX_VALUE);
    	List<String> supplerParamList = new ArrayList<>();
    	supplerParamList.add("item="+supplerId.toString());
       	List<Scmsupplier2> ScmsupplierList = null;
       	ScmsupplierList = scmSupplierbiz.queryPage(supplerPage, supplerParamList, "selectByIds", param);
       	HashMap<Long,Scmsupplier2> supplerMap=new HashMap<Long, Scmsupplier2>(); 
       	for(Scmsupplier2 supple:ScmsupplierList) {
       		supplerMap.put(supple.getId(), supple);
       	}
       	if(ScmsupplierList!=null && !ScmsupplierList.isEmpty()) {
	       	for(ScmPurRequireItemsQueryResult itesm:resultItemList) {
	       		itesm.setVendorNo(supplerMap.get(itesm.getVendorId()).getVendorNo());
	       		itesm.setVendorName(supplerMap.get(itesm.getVendorId()).getVendorName());
	       	}
       	}
    	Map<String,List<ScmPurRequireItemsQueryResult>> mapList = resultItemList.stream().collect(Collectors.groupingBy(ScmPurRequireItemsQueryResult::getPrNo));
       	for(ScmPurRequireBillQueryResult result:resultList) {
       		result.setItems(mapList.get(result.getPrNo()));
       		
	    	List<CommonEventHistory2> commonEventHistoryList = commonEventHistoryBiz.loadAuditStatus("purrequire", result.getPrNo(), param);
	    	if(commonEventHistoryList!=null && !commonEventHistoryList.isEmpty()) {
	    		List< AuditStatusResult > AuditList = new ArrayList();
	    		for(CommonEventHistory2 commonEventHistory:commonEventHistoryList) {
	    			AuditStatusResult auditStatusResult = new AuditStatusResult();
	    			BeanUtils.copyProperties(commonEventHistory,auditStatusResult);
	    			AuditList.add(auditStatusResult);
	    		}
	    		result.setAuditStatusResults(AuditList);
	    	}
       	}
       	ResultApi.setResultList(resultList);
    	
       	//List<CommonEventHistory2> commonEventHistoryList = commonEventHistoryBiz.loadAuditStatus(auditStatusParams.getBillTypeCode(), auditStatusParams.getBillNo(), ParamHelper.createParam(integratedRequest,"loadAuditStatus"));
       	
       
    	
		return ResultApi;
	}
	
	
	
	
	
	
	
	
	
	
	
}
