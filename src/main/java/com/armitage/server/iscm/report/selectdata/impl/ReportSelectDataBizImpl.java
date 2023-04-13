package com.armitage.server.iscm.report.selectdata.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.cache.ModelCacheMana;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupStandardBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.purchasemanage.reportParam.model.ReportSelectData;
import com.armitage.server.iscm.report.selectdata.ReportSelectDataBiz;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReportSelectDataBizImpl extends BaseBizImpl implements ReportSelectDataBiz {

	private OrgUnitBiz orgUnitBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private OrgStorageBiz orgStorageBiz;    
	private ScmInvWareHouseBiz scmInvWareHouseBiz;    
	private CodeBiz codeBiz;    
	private OrgUnitRelationBiz orgUnitRelationBiz;    
	private OrgCostCenterBiz orgCostCenterBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private UsrBiz usrBiz;
	private CustomerBiz customerBiz;
	private ScmMaterialGroupStandardBiz scmMaterialGroupStandardBiz;
	
	public OrgCompanyBiz getOrgCompanyBiz() {
		return orgCompanyBiz;
	}
	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}
	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}
	public void setScmsuppliergroupBiz(ScmsuppliergroupBiz scmsuppliergroupBiz) {
		this.scmsuppliergroupBiz = scmsuppliergroupBiz;
	}
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}
    public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
        this.orgUnitBiz = orgUnitBiz;
    }
    public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
        this.orgStorageBiz = orgStorageBiz;
    }
    public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
        this.scmInvWareHouseBiz = scmInvWareHouseBiz;
    }
    public void setCodeBiz(CodeBiz codeBiz) {
        this.codeBiz = codeBiz;
    }
    public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
        this.orgUnitRelationBiz = orgUnitRelationBiz;
    }
    public void setUsrBiz(UsrBiz usrBiz) {
        this.usrBiz = usrBiz;
    }
    public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}
	public void setCustomerBiz(CustomerBiz customerBiz) {
		this.customerBiz = customerBiz;
	}
	public void setScmMaterialGroupStandardBiz(ScmMaterialGroupStandardBiz scmMaterialGroupStandardBiz) {
		this.scmMaterialGroupStandardBiz = scmMaterialGroupStandardBiz;
	}
	
	@Override
	public String getReqOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgAdmin != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgAdmin.getOrgUnitName());
        		reportSelectData.setId(orgAdmin.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "reqOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(orgUnitNo,param);
        if(orgAdminList != null && !orgAdminList.isEmpty()){
        	for(OrgAdmin2 orgAdmin : orgAdminList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgAdmin.getOrgUnitName());
        		reportSelectData.setId(orgAdmin.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
    
    
	@Override
	public String getOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(defaultValue, param);
			if(orgBaseUnit != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgBaseUnit.getOrgUnitName());
        		reportSelectData.setId(orgBaseUnit.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "orgUnitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		List<OrgBaseUnit2> orgBaseUnitList = orgUnitBiz.findAll(param);
        if(orgBaseUnitList != null && !orgBaseUnitList.isEmpty()){
        	for(OrgBaseUnit2 orgBaseUnit : orgBaseUnitList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgBaseUnit.getOrgUnitName());
        		reportSelectData.setId(orgBaseUnit.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getVenderClassNameList(String defaultValue, String controlUnitNo) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			Scmsuppliergroup2 scmsuppliergroup = scmsuppliergroupBiz.select(Long.valueOf(defaultValue), param);
			if(scmsuppliergroup != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmsuppliergroup.getClassName());
        		reportSelectData.setId(String.valueOf(scmsuppliergroup.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "venderClassNameList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		List<Scmsuppliergroup2> scmsuppliergroupList = scmsuppliergroupBiz.findAll(param);
        if(scmsuppliergroupList != null && !scmsuppliergroupList.isEmpty()){
        	for(Scmsuppliergroup2 scmsuppliergroup : scmsuppliergroupList){
        		if(!scmsuppliergroup.isFlag() || !StringUtils.equals(controlUnitNo, scmsuppliergroup.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmsuppliergroup.getClassName());
        		reportSelectData.setId(String.valueOf(scmsuppliergroup.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getMaterialClassNameList(String defaultValue, String controlUnitNo) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.select(Long.valueOf(defaultValue), param);
			if(scmMaterialGroup != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
	    		reportSelectData.setName(scmMaterialGroup.getClassName());
	    		reportSelectData.setId(String.valueOf(scmMaterialGroup.getId()));
	    		reportSelectData.setpId("0");
	    		list.add(reportSelectData);
	    		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	        	String str= gson.toJson(list);
	            return str;
			}
			return null;
		}
		String key = "materialClassNameList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findAll(param);
        if(scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()){
        	for(ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList){
        		if(!scmMaterialGroup.isFlag() || !StringUtils.equals(controlUnitNo, scmMaterialGroup.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmMaterialGroup.getClassName());
        		reportSelectData.setId(String.valueOf(scmMaterialGroup.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getMaterialClassNameForParentList(String defaultValue, String controlUnitNo) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			ScmMaterialGroup scmMaterialGroup = scmMaterialGroupBiz.select(Long.valueOf(defaultValue), param);
			if(scmMaterialGroup != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
	    		reportSelectData.setName(scmMaterialGroup.getClassName());
	    		reportSelectData.setId(String.valueOf(scmMaterialGroup.getId()));
	    		reportSelectData.setpId("0");
	    		list.add(reportSelectData);
	    		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	        	String str= gson.toJson(list);
	            return str;
			}
			return null;
		}
		String key = "materialClassNameForParentList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findAll(param);
        if(scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()){
        	for(ScmMaterialGroup scmMaterialGroup : scmMaterialGroupList){
        		if(!scmMaterialGroup.isFlag() || !StringUtils.equals(controlUnitNo, scmMaterialGroup.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmMaterialGroup.getClassName());
        		reportSelectData.setId(String.valueOf(scmMaterialGroup.getId()));
        		reportSelectData.setpId(String.valueOf(scmMaterialGroup.getParentId()));
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getPurOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgPurchase2 orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgPurchase != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgPurchase.getOrgUnitName());
        		reportSelectData.setId(orgPurchase.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "purOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(orgUnitNo,param);
        if(orgPurchaseList != null && !orgPurchaseList.isEmpty()){
        	for(OrgPurchase2 orgPurchase : orgPurchaseList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgPurchase.getOrgUnitName());
        		reportSelectData.setId(orgPurchase.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getFinOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgCompany != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgCompany.getOrgUnitName());
        		reportSelectData.setId(orgCompany.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "finOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(orgUnitNo,param);
        if(orgCompanyList != null && !orgCompanyList.isEmpty()){
        	for(OrgCompany2 orgCompany : orgCompanyList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgCompany.getOrgUnitName());
        		reportSelectData.setId(orgCompany.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getVenderNameList(String defaultValue, String controlUnitNo) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			Scmsupplier2 scmsupplier = scmsupplierBiz.select(Long.valueOf(defaultValue), param);
			if(scmsupplier != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmsupplier.getVendorName());
        		reportSelectData.setId(String.valueOf(scmsupplier.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "venderNameList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		Page page = new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setShowCount(Integer.MAX_VALUE);
		param.setControlUnitNo(controlUnitNo);
        List<Scmsupplier2> scmsupplierList = scmsupplierBiz.findPage(page,param);
        if(scmsupplierList != null && !scmsupplierList.isEmpty()){
        	for(Scmsupplier2 scmsupplier : scmsupplierList){
        		if(!StringUtils.equals(controlUnitNo, scmsupplier.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmsupplier.getVendorName());
        		reportSelectData.setId(String.valueOf(scmsupplier.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getMaterialNameList(String defaultValue, String controlUnitNo) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			ScmMaterial2 scmMaterial = scmMaterialBiz.select(Long.valueOf(defaultValue), param);
			if(scmMaterial != null){
				ReportSelectData reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmMaterial.getItemName());
        		reportSelectData.setId(String.valueOf(scmMaterial.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "materialNameList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}		
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		page.setShowCount(Integer.MAX_VALUE);
		param.setControlUnitNo(controlUnitNo);
        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.findPage(page,param);
        if(scmMaterialList != null && !scmMaterialList.isEmpty()){
        	for(ScmMaterial2 scmMaterial : scmMaterialList){
        		if(!StringUtils.equals(controlUnitNo, scmMaterial.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData reportSelectData = new ReportSelectData();
        		String spec = scmMaterial.getSpec();
        		if (StringUtils.isNotBlank(spec)) {
        			reportSelectData.setName(scmMaterial.getItemName() + "(" + spec + ")");
        		} else {
        			reportSelectData.setName(scmMaterial.getItemName());
        		}
        		reportSelectData.setId(String.valueOf(scmMaterial.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	
    @Override
    public String getInvOrgUnitNoList(String defaultValue,String orgUnitNo, String controlUnitNo) {
        Param param = new Param();
        param.setOrgUnitNo(orgUnitNo);
        param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgStorage != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(orgStorage.getOrgUnitName());
                reportSelectData.setId(orgStorage.getOrgUnitNo());
                reportSelectData.setpId("0");
                list.add(reportSelectData);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                String str= gson.toJson(list);
                return str;
			}
			return null;
		}
    	String key = "invOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(orgUnitNo,param);
        if(orgStorageList != null && !orgStorageList.isEmpty()){
            for(OrgStorage2 orgStorage : orgStorageList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(orgStorage.getOrgUnitName());
                reportSelectData.setId(orgStorage.getOrgUnitNo());
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str= gson.toJson(list);
            ModelCacheMana.set(key, str);
            return str;
        }
        return null;
    }
    
    @Override
	public String getCostOrgunitNoList(String defaultValue, String orgUnitNo, String controlUnitNo) {
    	Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgCostCenter2 orgCostCenter2 = orgCostCenterBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgCostCenter2 != null){
				ReportSelectData reportSelectData = new ReportSelectData();
				reportSelectData.setName(orgCostCenter2.getOrgUnitName());
				reportSelectData.setId(orgCostCenter2.getOrgUnitNo());
				reportSelectData.setpId("0");
				list.add(reportSelectData);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String str = gson.toJson(list);
				return str;
			}
			return null;
		}
    	String key = "costOrgunitNoList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(orgUnitNo, param);
		if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
			for (OrgCostCenter2 orgCostCenter2 : orgCostCenterList) {
				ReportSelectData reportSelectData = new ReportSelectData();
				reportSelectData.setName(orgCostCenter2.getOrgUnitName());
				reportSelectData.setId(orgCostCenter2.getOrgUnitNo());
				reportSelectData.setpId("0");
				list.add(reportSelectData);
			}
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String str = gson.toJson(list);
			ModelCacheMana.set(key, str);
			return str;
		}
		return null;
	}

    @Override
    public String getWhNameList(String defaultValue, String orgUnitNo, String controlUnitNo) {
    	Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.select(Long.valueOf(defaultValue), param);
			if(scmInvWareHouse != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(scmInvWareHouse.getWhName());
                reportSelectData.setId(String.valueOf(scmInvWareHouse.getId()));
                reportSelectData.setpId("0");
                list.add(reportSelectData);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                String str= gson.toJson(list);
                return str;
			}
			return null;
		}
    	String key = "whNameList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(orgUnitNo.equals(controlUnitNo)){
        	//管理单元显示所有仓库
        	map.put("controlUnitNo", controlUnitNo);
        } else {
        	map.put("orgUnitNo", orgUnitNo);
            map.put("controlUnitNo", controlUnitNo);
        }
        List<ScmInvWareHouse> scmInvWareHouseList = scmInvWareHouseBiz.findAll(map, param);
        if(scmInvWareHouseList != null && !scmInvWareHouseList.isEmpty()){
            for(ScmInvWareHouse scmInvWareHouse : scmInvWareHouseList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(scmInvWareHouse.getWhName());
                reportSelectData.setId(String.valueOf(scmInvWareHouse.getId()));
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str= gson.toJson(list);
            ModelCacheMana.set(key, str);
            return str;
        }
        return null;
    }
    @Override
    public String getPurBizTypeList(String defaultValue, String scmBizType) {
    	Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put(Code.FN_CODE, new QueryParam(Code.FN_CODE,
	                QueryParam.QUERY_EQ, defaultValue));
	        List<Code> codeList = codeBiz.findAll(map, param);
	        if(codeList != null && !codeList.isEmpty()){
	            for(Code code : codeList){
	                ReportSelectData  reportSelectData = new ReportSelectData();
	                reportSelectData.setName(code.getName());
	                reportSelectData.setId(code.getCode());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	            }
	            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	            String str= gson.toJson(list);
	            return str;
	        }
	        return null;
		}
    	String key = "purBizTypeList_"+scmBizType;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(Code.FN_CATEGORY, new QueryParam(Code.FN_CATEGORY,
                QueryParam.QUERY_EQ, scmBizType));
        map.put(Code.FN_CODE, new QueryParam(Code.FN_CODE,
                QueryParam.QUERY_NE, "!"));
        List<Code> codeList = codeBiz.findAll(map, param);
        if(codeList != null && !codeList.isEmpty()){
            for(Code code : codeList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(code.getName());
                reportSelectData.setId(code.getCode());
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str= gson.toJson(list);
            ModelCacheMana.set(key, str);
            return str;
        }
        return null;
    }
    @Override
    public String getFormOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo, String type) {
    	Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			switch (StringUtils.lowerCase(type)) {
	        case "admintopurfrom":
	        	OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(defaultValue, param);
				if(orgAdmin != null){
					ReportSelectData reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgAdmin.getOrgUnitName());
	                reportSelectData.setId(orgAdmin.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    	        String str = gson.toJson(list);
	    	        return str;
				}
	        	break;
	        case "admintoinv":
	        	OrgAdmin2 orgAdmin2 = orgAdminBiz.selectByOrgUnitNo(defaultValue, param);
				if(orgAdmin2 != null){
					ReportSelectData reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgAdmin2.getOrgUnitName());
	                reportSelectData.setId(orgAdmin2.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    	        String str = gson.toJson(list);
	    	        return str;
				}
	        	break;
	        case "admintocostfrom":
	        	OrgAdmin2 orgAdmin3 = orgAdminBiz.selectByOrgUnitNo(defaultValue, param);
				if(orgAdmin3 != null){
					ReportSelectData reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgAdmin3.getOrgUnitName());
	                reportSelectData.setId(orgAdmin3.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    	        String str = gson.toJson(list);
	    	        return str;
				}
	        	break;
	        default:
	            break;
	        }
			return null;
		}
    	String key = "formOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo+"_"+type;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        String str = null;
        switch (StringUtils.lowerCase(type)) {
        case "admintopurfrom":
            List<OrgAdmin2> orgAdminList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOPUR, orgUnitNo, false, null, param);
            if (orgAdminList != null && orgAdminList.size() > 0) {
                for (OrgAdmin2 orgAdmin : orgAdminList) {
                    ReportSelectData reportSelectData = new ReportSelectData();
                    reportSelectData.setName(orgAdmin.getOrgUnitName());
                    reportSelectData.setId(orgAdmin.getOrgUnitNo());
                    reportSelectData.setpId("0");
                    list.add(reportSelectData);
                }
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                str= gson.toJson(list);
            }
            break;
        case "admintoinv":
            List<OrgAdmin2> orgAdminList2 = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOINV, orgUnitNo, false, null, param);
            if (orgAdminList2 != null && orgAdminList2.size() > 0) {
                for (OrgAdmin2 orgAdmin : orgAdminList2) {
                    ReportSelectData reportSelectData = new ReportSelectData();
                    reportSelectData.setName(orgAdmin.getOrgUnitName());
                    reportSelectData.setId(orgAdmin.getOrgUnitNo());
                    reportSelectData.setpId("0");
                    list.add(reportSelectData);
                }
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                str= gson.toJson(list);
            }
            break;
        case "admintocostfrom":
        	if(orgUnitNo.equals(controlUnitNo)){
        		//管理单元显示所有部门
        		List<OrgAdmin2> orgAdminList3 = orgAdminBiz.findChild(orgUnitNo,param);
                if(orgAdminList3 != null && !orgAdminList3.isEmpty()){
                	for (OrgAdmin2 orgAdmin : orgAdminList3) {
                        ReportSelectData reportSelectData = new ReportSelectData();
                        reportSelectData.setName(orgAdmin.getOrgUnitName());
                        reportSelectData.setId(orgAdmin.getOrgUnitNo());
                        reportSelectData.setpId("0");
                        list.add(reportSelectData);
                    }
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    str= gson.toJson(list);
                }
        	}else{
        		List<OrgAdmin2> orgAdminList3 = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOCOST, orgUnitNo, false, null, param);
                if (orgAdminList3 != null && orgAdminList3.size() > 0) {
                    for (OrgAdmin2 orgAdmin : orgAdminList3) {
                        ReportSelectData reportSelectData = new ReportSelectData();
                        reportSelectData.setName(orgAdmin.getOrgUnitName());
                        reportSelectData.setId(orgAdmin.getOrgUnitNo());
                        reportSelectData.setpId("0");
                        list.add(reportSelectData);
                    }
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    str= gson.toJson(list);
                }
        	}
            break;
        default:
            break;
        }
        ModelCacheMana.set(key, str);
        return str;
    }
    @Override
    public String getToOrgUnitNoList(String defaultValue, String orgUnitNo, String controlUnitNo, String type) {
    	Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			switch (StringUtils.lowerCase(type)) {
	        case "purtoinvto":
	        	OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(defaultValue, param);
				if(orgStorage != null){
					ReportSelectData  reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgStorage.getOrgUnitName());
	                reportSelectData.setId(orgStorage.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	                String str= gson.toJson(list);
	                return str;
				}
	        	break;
	        default:
	            break;
	        }
			return null;
		} else {
			if (orgUnitNo.equals(controlUnitNo)) {
				String str = null;
				String key = "toOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo+"_"+type;
				String data = ModelCacheMana.get(key);
				if (StringUtils.isNotBlank(data)) {
					return data;
				}
				
				switch (StringUtils.lowerCase(type)) {
		        case "purtoinvto":
		        	List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(orgUnitNo,param);
		        	if (orgStorageList != null && orgStorageList.size() > 0) {
		        		 for (OrgStorage2 orgStorage : orgStorageList) {
		                     ReportSelectData reportSelectData = new ReportSelectData();
		                     reportSelectData.setName(orgStorage.getOrgUnitName());
		                     reportSelectData.setId(orgStorage.getOrgUnitNo());
		                     reportSelectData.setpId("0");
		                     list.add(reportSelectData);
		                 }
		                 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		                 str= gson.toJson(list);
		                 ModelCacheMana.set(key, str);
		        	}
		        	break;
		        default:
		            break;
		        }
				return str;
			}	
		}
    	String key = "toOrgUnitNoList_"+controlUnitNo+"_"+orgUnitNo+"_"+type;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        String str = null;
        switch (StringUtils.lowerCase(type)) {
        case "purtoinvto":
            List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOINV, orgUnitNo, false, null, param);
            if (orgStorageList != null && orgStorageList.size() > 0) {
                for (OrgStorage2 orgStorage : orgStorageList) {
                    ReportSelectData reportSelectData = new ReportSelectData();
                    reportSelectData.setName(orgStorage.getOrgUnitName());
                    reportSelectData.setId(orgStorage.getOrgUnitNo());
                    reportSelectData.setpId("0");
                    list.add(reportSelectData);
                }
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                str= gson.toJson(list);
                ModelCacheMana.set(key, str);
            }
            break;
        default:
            break;
        }
        return str;
    }
    @Override
    public String getBuyerNameList(String defaultValue, String orgUnitNo, String controlUnitNo) {
    	Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			Usr2 usr = usrBiz.select(Long.valueOf(defaultValue), param);
			if(usr != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(usr.getName());
                reportSelectData.setId(String.valueOf(usr.getId()));
                reportSelectData.setpId("0");
                list.add(reportSelectData);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                String str= gson.toJson(list);
                return str;
			}
			return null;
		}
    	String key = "buyerNameList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}

        List<Usr2> usrList = usrBiz.findAll(param);
        if(usrList != null && !usrList.isEmpty()){
            for(Usr2 usr : usrList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(usr.getName());
                reportSelectData.setId(String.valueOf(usr.getId()));
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str= gson.toJson(list);
            ModelCacheMana.set(key, str);
            return str;
        }
        return null;
    }
	@Override
	public String getWhOrDept(String defaultValue, String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			if(StringUtils.isNumeric(defaultValue)){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.select(Long.valueOf(defaultValue), param);
				if(scmInvWareHouse != null){
					ReportSelectData  reportSelectData = new ReportSelectData();
	                reportSelectData.setName(scmInvWareHouse.getWhName());
	                reportSelectData.setId(String.valueOf(scmInvWareHouse.getId()));
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    	        String str = gson.toJson(list);
	    	        return str;
				}
			}else{
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(defaultValue, param);
				if(orgAdmin != null){
					ReportSelectData reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgAdmin.getOrgUnitName());
	                reportSelectData.setId(orgAdmin.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    	        String str = gson.toJson(list);
	    	        return str;
				}
			}
			return null;
		}
		String key = "whOrDept_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}	
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("orgUnitNo", orgUnitNo);
        map.put("controlUnitNo", controlUnitNo);
        List<ScmInvWareHouse> scmInvWareHouseList = scmInvWareHouseBiz.findAll(map, param);
        if(scmInvWareHouseList != null && !scmInvWareHouseList.isEmpty()){
            for(ScmInvWareHouse scmInvWareHouse : scmInvWareHouseList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(scmInvWareHouse.getWhName());
                reportSelectData.setId(String.valueOf(scmInvWareHouse.getId()));
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
        }
        List<OrgAdmin2> orgAdminList2 = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.ADMINTOINV, orgUnitNo, false, null, param);
        if (orgAdminList2 != null && orgAdminList2.size() > 0) {
            for (OrgAdmin2 orgAdmin : orgAdminList2) {
            	if(StringUtils.equals("2",orgAdmin.getOrgType())) {
	                ReportSelectData reportSelectData = new ReportSelectData();
	                reportSelectData.setName(orgAdmin.getOrgUnitName());
	                reportSelectData.setId(orgAdmin.getOrgUnitNo());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
            	}
            }
        }
        if(list!=null && !list.isEmpty()) {
	        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	        String str = gson.toJson(list);
	        ModelCacheMana.set(key, str);
	        return str;
        }
        return null;
	}

	@Override
	public String getCustNameList(String defaultValue, String orgUnitNo,String controlUnitNo) {
		Param param = new Param();
		param.setProductCode("iSCM");
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue) && StringUtils.isNumeric(defaultValue)){
			Customer2 customer = customerBiz.select(Long.valueOf(defaultValue), param);
			if(customer != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(customer.getCustName());
        		reportSelectData.setId(String.valueOf(customer.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "custNameList_"+controlUnitNo+"_"+orgUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		Page page = new Page();
		page.setModelClass(Customer2.class);
		page.setShowCount(Integer.MAX_VALUE);
        List<Customer2> customerList = customerBiz.findPage(page,param);
        if(customerList != null && !customerList.isEmpty()){
        	for(Customer2 customer : customerList){
        		if(!StringUtils.equals(controlUnitNo, customer.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(customer.getCustName());
        		reportSelectData.setId(String.valueOf(customer.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, data);
            return str;
        }
        return null;
	}
	
	@Override
	public String getMaterialStandardNameList(String controlUnitNo) {
		String key = "materialStandardNameList_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
		Param param = new Param();
		List<ScmMaterialGroupStandard> scmMaterialGroupStandardList = scmMaterialGroupStandardBiz.findAll(param);
        if(scmMaterialGroupStandardList != null && !scmMaterialGroupStandardList.isEmpty()){
        	List<ReportSelectData> list = new ArrayList<>();
        	for(ScmMaterialGroupStandard scmMaterialGroupStandard : scmMaterialGroupStandardList){
        		if(!"1".equals(scmMaterialGroupStandard.getStandardType()) || !StringUtils.equals(controlUnitNo, scmMaterialGroupStandard.getControlUnitNo())){
        			continue;
        		}
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmMaterialGroupStandard.getStandardName());
        		reportSelectData.setId(String.valueOf(scmMaterialGroupStandard.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	@Override
	public String getReqFinOrgUnitNoList(String defaultValue,
			String controlUnitNo) {
		Param param = new Param();
		param.setOrgUnitNo(controlUnitNo);
		param.setControlUnitNo(controlUnitNo);
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(defaultValue, param);
			if(orgCompany != null){
				ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgCompany.getOrgUnitName());
        		reportSelectData.setId(orgCompany.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
                return str;
			}
			return null;
		}
		String key = "finOrgUnitNoList_"+controlUnitNo+"_"+controlUnitNo;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        List<OrgCompany2> orgCompanyList = orgCompanyBiz.findChild(controlUnitNo,param);
        if(orgCompanyList != null && !orgCompanyList.isEmpty()){
        	for(OrgCompany2 orgCompany : orgCompanyList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(orgCompany.getOrgUnitName());
        		reportSelectData.setId(orgCompany.getOrgUnitNo());
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}
	@Override
	public String getAppraiseTypeList(String defaultValue, String appraiseType) {
		Param param = new Param();
		List<ReportSelectData> list = new ArrayList<>();
		if(StringUtils.isNotBlank(defaultValue)){
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put(Code.FN_CODE, new QueryParam(Code.FN_CODE,
	                QueryParam.QUERY_EQ, defaultValue));
	        List<Code> codeList = codeBiz.findAll(map, param);
	        if(codeList != null && !codeList.isEmpty()){
	            for(Code code : codeList){
	                ReportSelectData  reportSelectData = new ReportSelectData();
	                reportSelectData.setName(code.getName());
	                reportSelectData.setId(code.getCode());
	                reportSelectData.setpId("0");
	                list.add(reportSelectData);
	            }
	            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	            String str= gson.toJson(list);
	            return str;
	        }
	        return null;
		}
    	String key = "appraiseTypeList_"+appraiseType;
		String data = ModelCacheMana.get(key);
		if (StringUtils.isNotBlank(data)) {
			return data;
		}
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(Code.FN_CATEGORY, new QueryParam(Code.FN_CATEGORY,
                QueryParam.QUERY_EQ, appraiseType));
        map.put(Code.FN_CODE, new QueryParam(Code.FN_CODE,
                QueryParam.QUERY_NE, "!"));
        List<Code> codeList = codeBiz.findAll(map, param);
        if(codeList != null && !codeList.isEmpty()){
            for(Code code : codeList){
                ReportSelectData  reportSelectData = new ReportSelectData();
                reportSelectData.setName(code.getName());
                reportSelectData.setId(code.getCode());
                reportSelectData.setpId("0");
                list.add(reportSelectData);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str= gson.toJson(list);
            ModelCacheMana.set(key, str);
            return str;
        }
        return null;
	}

}

