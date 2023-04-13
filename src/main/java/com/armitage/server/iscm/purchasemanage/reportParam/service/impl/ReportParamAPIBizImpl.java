package com.armitage.server.iscm.purchasemanage.reportParam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.ScmsuppliergroupBiz;
import com.armitage.server.iscm.purchasemanage.reportParam.model.ReportSelectData;
import com.armitage.server.iscm.purchasemanage.reportParam.service.ReportParamAPIBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service("reportParamAPIBiz")
public class ReportParamAPIBizImpl extends BaseBizImpl implements ReportParamAPIBiz {

	private OrgUnitBiz orgUnitBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmsuppliergroupBiz scmsuppliergroupBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
    
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
    @Override
    public String getOrgUnitNoKeyList(String orgUnitNo) {
        
        StringBuffer sb = new StringBuffer();
        Param param = new Param();
        if (orgUnitNo != null && orgUnitNo.length() > 0) {
            OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgUnitNo, param);
            // 拼接keyList, 格式>  name1:No1,name2:No2
            sb = sb.append(orgBaseUnit.getOrgUnitName() + ":" + orgBaseUnit.getOrgUnitNo());
        }     
        return sb.toString() + "," + "全部:0";
    }
    
	@Override
	public String getReqOrgUnitNoList(String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
        if (StringUtils.isBlank(orgUnitNo)) {
        	Page page = new Page();
			page.setModelClass(OrgAdmin2.class);
			page.setShowCount(Integer.MAX_VALUE);
			param.setControlUnitNo(controlUnitNo);
            List<OrgAdmin2> orgAdminList = orgAdminBiz.findPage(page,param);
            if(orgAdminList != null && !orgAdminList.isEmpty()){
            	List<ReportSelectData> list = new ArrayList<>();
            	for(OrgAdmin2 orgAdmin : orgAdminList){
            		ReportSelectData  reportSelectData = new ReportSelectData();
            		if (StringUtils.isBlank(orgAdmin.getOrgUnitName()) && StringUtils.isNotBlank(orgAdmin.getOrgUnitNo())) {
                        OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgAdmin.getOrgUnitNo(), param);
                        reportSelectData.setName(orgBaseUnit.getOrgUnitName());
            		}else{
            			reportSelectData.setName(orgAdmin.getOrgUnitName());
            		}
            		reportSelectData.setId(orgAdmin.getOrgUnitNo());
            		reportSelectData.setpId("0");
            		list.add(reportSelectData);
            	}
            	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
            	//System.out.println("申购组织："+ str);
                return str;
            }
        }
        return null;
	}
    
    
	@Override
	public String getOrgUnitNoList(String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
        if (StringUtils.isBlank(orgUnitNo)) {
            List<OrgBaseUnit2> orgBaseUnitList = orgUnitBiz.findAll(param);
            if(orgBaseUnitList != null && !orgBaseUnitList.isEmpty()){
            	List<ReportSelectData> list = new ArrayList<>();
            	for(OrgBaseUnit2 orgBaseUnit : orgBaseUnitList){
            		ReportSelectData  reportSelectData = new ReportSelectData();
            		reportSelectData.setName(orgBaseUnit.getOrgUnitName());
            		reportSelectData.setId(orgBaseUnit.getOrgUnitNo());
            		reportSelectData.setpId("0");
            		list.add(reportSelectData);
            	}
            	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
            	//System.out.println("组织："+ str);
                return str;
            }
        }
        return null;
	}
	
	@Override
	public String getVenderClassNameList(String controlUnitNo) {
		Param param = new Param();
		List<Scmsuppliergroup2> scmsuppliergroupList = scmsuppliergroupBiz.findAll(param);
        if(scmsuppliergroupList != null && !scmsuppliergroupList.isEmpty()){
        	List<ReportSelectData> list = new ArrayList<>();
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
        	//System.out.println("供应商类别："+ str);
            return str;
        }
        return null;
	}
	@Override
	public String getMaterialClassNameList(String controlUnitNo) {
		Param param = new Param();
		List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findAll(param);
        if(scmMaterialGroupList != null && !scmMaterialGroupList.isEmpty()){
        	List<ReportSelectData> list = new ArrayList<>();
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
        	//System.out.println("物资类别："+ str);
            return str;
        }
        return null;
	}
	
	@Override
	public String getPurOrgUnitNoList(String orgUnitNo, String controlUnitNo) {
		Param param = new Param();
        if (StringUtils.isBlank(orgUnitNo)) {
        	Page page = new Page();
			page.setModelClass(OrgPurchase2.class);
			page.setShowCount(Integer.MAX_VALUE);
			param.setControlUnitNo(controlUnitNo);
            List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findPage(page,param);
            if(orgPurchaseList != null && !orgPurchaseList.isEmpty()){
            	List<ReportSelectData> list = new ArrayList<>();
            	for(OrgPurchase2 orgPurchase : orgPurchaseList){
            		ReportSelectData  reportSelectData = new ReportSelectData();
            		if (StringUtils.isBlank(orgPurchase.getOrgUnitName()) && StringUtils.isNotBlank(orgPurchase.getOrgUnitNo())) {
                        OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(orgPurchase.getOrgUnitNo(), param);
                        reportSelectData.setName(orgBaseUnit.getOrgUnitName());
            		}else{
            			reportSelectData.setName(orgPurchase.getOrgUnitName());
            		}
            		reportSelectData.setId(orgPurchase.getOrgUnitNo());
            		reportSelectData.setpId("0");
            		list.add(reportSelectData);
            	}
            	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            	String str= gson.toJson(list);
            	//System.out.println("采购组织："+ str);
                return str;
            }
        }
        return null;
	}
	
	@Override
	public String getVenderNameList(String controlUnitNo) {
		Param param = new Param();
		Page page = new Page();
		page.setModelClass(Scmsupplier2.class);
		page.setShowCount(Integer.MAX_VALUE);
		param.setControlUnitNo(controlUnitNo);
        List<Scmsupplier2> scmsupplierList = scmsupplierBiz.findPage(page,param);
        if(scmsupplierList != null && !scmsupplierList.isEmpty()){
        	List<ReportSelectData> list = new ArrayList<>();
        	for(Scmsupplier2 scmsupplier : scmsupplierList){
        		ReportSelectData  reportSelectData = new ReportSelectData();
        		reportSelectData.setName(scmsupplier.getVendorName());
        		reportSelectData.setId(String.valueOf(scmsupplier.getId()));
        		reportSelectData.setpId("0");
        		list.add(reportSelectData);
        	}
        	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	String str= gson.toJson(list);
        	//System.out.println("供应商："+ str);
            return str;
        }
        return null;
	}
}

