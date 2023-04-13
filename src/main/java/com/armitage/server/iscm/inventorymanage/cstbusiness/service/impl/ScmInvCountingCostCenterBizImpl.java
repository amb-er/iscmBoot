package com.armitage.server.iscm.inventorymanage.cstbusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterEntryBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingCostCenterProductBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgCostCenter2;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCostCenterBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvCountingCostCenterBiz")
public class ScmInvCountingCostCenterBizImpl extends BaseBizImpl<ScmInvCountingCostCenter2> implements ScmInvCountingCostCenterBiz {
	
	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvCountingTaskBiz scmInvCountingTaskBiz;
	private OrgCostCenterBiz orgCostCenterBiz;
	private ScmInvCountingCostCenterProductBiz scmInvCountingCostCenterProductBiz;	
	private ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private SysParamBiz sysParamBiz;
	private ScmMaterialBiz scmMaterialBiz;
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmInvCountingCostCenterEntryBiz(ScmInvCountingCostCenterEntryBiz scmInvCountingCostCenterEntryBiz) {
		this.scmInvCountingCostCenterEntryBiz = scmInvCountingCostCenterEntryBiz;
	}
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmInvCountingTaskBiz(ScmInvCountingTaskBiz scmInvCountingTaskBiz) {
		this.scmInvCountingTaskBiz = scmInvCountingTaskBiz;
	}
	
	public void setOrgCostCenterBiz(OrgCostCenterBiz orgCostCenterBiz) {
		this.orgCostCenterBiz = orgCostCenterBiz;
	}

	public void setScmInvCountingCostCenterProductBiz(
			ScmInvCountingCostCenterProductBiz scmInvCountingCostCenterProductBiz) {
		this.scmInvCountingCostCenterProductBiz = scmInvCountingCostCenterProductBiz;
	}

	public void setScmProductCostCardDetailHistoryBiz(
			ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz) {
		this.scmProductCostCardDetailHistoryBiz = scmProductCostCardDetailHistoryBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgCostCenter2> orgCostCenterList = orgCostCenterBiz.findChild(param.getOrgUnitNo(), param);
		if (orgCostCenterList != null && !orgCostCenterList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgCostCenter2 orgCostCenter : orgCostCenterList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgCostCenter.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter2.class) + "." + ScmInvCountingCostCenter2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter2.class) + "." + ScmInvCountingCostCenter2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter2.class) + "." + ScmInvCountingCostCenter2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvCountingCostCenter2.class) + "." + ScmInvCountingCostCenter2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterSelect(ScmInvCountingCostCenter2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	private void setConvertMap(ScmInvCountingCostCenter2 scmInvCountingCostCenter,Param param) {
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmInvCountingCostCenter.getCreator(), param);
			if (usr != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_CREATOR, usr);
				scmInvCountingCostCenter.setCreatorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmInvCountingCostCenter.getEditor(), param);
			if (usr != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_EDITOR, usr);
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getCountingPerson())){
			//盘点人
			Usr usr = usrBiz.selectByCode(scmInvCountingCostCenter.getCountingPerson(), param);
			if (usr != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_COUNTINGPERSON, usr);
				scmInvCountingCostCenter.setCountingPersonName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getCountingAgainPerson())){
			//复盘人
			Usr usr = usrBiz.selectByCode(scmInvCountingCostCenter.getCountingAgainPerson(), param);
			if (usr != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_COUNTINGAGAINPERSON, usr);
				scmInvCountingCostCenter.setCountingAgainPersonName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getCountingMonitorer())){
			//监盘人
			Usr usr = usrBiz.selectByCode(scmInvCountingCostCenter.getCountingMonitorer(), param);
			if (usr != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_COUNTINGMONITORER, usr);
				scmInvCountingCostCenter.setCountingMonitorerName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getUseOrgUnitNo())){
			//部门
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvCountingCostCenter.getUseOrgUnitNo(), param);
			if (orgAdmin != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_USEORGUNITNO, orgAdmin);
				scmInvCountingCostCenter.setUserOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
		if (StringUtils.isNotBlank(scmInvCountingCostCenter.getOrgUnitNo())){
			//库存组织
			OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmInvCountingCostCenter.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmInvCountingCostCenter.setConvertMap(ScmInvCountingCostCenter2.FN_ORGUNITNO, orgBaseUnit);
			}
		}
		
	}
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvCountingCostCenter2 scmInvCountingCostCenter : (List<ScmInvCountingCostCenter2>)list){
				setConvertMap(scmInvCountingCostCenter,param);
			}
		}
	}
	
	@Override
	protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmInvCountingCostCenter2 scmInvCountingCostCenter : (List<ScmInvCountingCostCenter2>)list){
				setConvertMap(scmInvCountingCostCenter,param);
			}
		}
	}


	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = (ScmInvCountingCostCenter2) bean.getList().get(0);
		if(scmInvCountingCostCenter != null && scmInvCountingCostCenter.getTableId() > 0){
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryBiz.selectByTableId(scmInvCountingCostCenter.getTableId(), param);
			if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
				bean.setList2(scmInvCountingCostCenterEntryList);
			}
			bean.setList3(scmInvCountingCostCenterProductBiz.selectByTableId(scmInvCountingCostCenter.getTableId(),param));
		}
	}

	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = (ScmInvCountingCostCenter2) bean.getList().get(0);
		if(scmInvCountingCostCenter != null && scmInvCountingCostCenter.getTableId() > 0){
			String regOrgUnitNo="";
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCountingCostCenter.getOrgUnitNo(), false, null, param);
			if(orgCompanyList!=null && !orgCompanyList.isEmpty()) {
				List<OrgResource2> orgResourceList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.RESTOFIN, orgCompanyList.get(0).getOrgUnitNo(), false, null, param);
				if(orgResourceList!=null && !orgResourceList.isEmpty())
					regOrgUnitNo = orgResourceList.get(0).getOrgUnitNo();
			}
			//新增盘存明细
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = bean.getList2();
			List<ScmInvCountingCostCenterProduct2> scmInvCountingCostCenterProductList = bean.getList3();
			if(scmInvCountingCostCenterProductList!=null && !scmInvCountingCostCenterProductList.isEmpty()) {
				int lineId = 1;
				for(ScmInvCountingCostCenterProduct2 scmInvCountingCostCenterProduct:scmInvCountingCostCenterProductList) {
					scmInvCountingCostCenterProduct.setLineId(lineId);
					scmInvCountingCostCenterProduct.setTableId(scmInvCountingCostCenter.getTableId());
					lineId = lineId+1;
					scmInvCountingCostCenterProductBiz.add(scmInvCountingCostCenterProduct, param);
				}
				generateProductAddData(regOrgUnitNo,scmInvCountingCostCenter.getBizDate(),scmInvCountingCostCenterEntryList,scmInvCountingCostCenterProductList,param);
			}
			if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
				int lineId = 1;
				for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList){
					scmInvCountingCostCenterEntry.setLineId(lineId);
					scmInvCountingCostCenterEntry.setTableId(scmInvCountingCostCenter.getTableId());
					scmInvCountingCostCenterEntry.setAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setTaxAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setProductAddAmt(scmInvCountingCostCenterEntry.getProductAddQty().multiply(scmInvCountingCostCenterEntry.getPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setProductAddTaxAmt(scmInvCountingCostCenterEntry.getProductAddQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setDifferAmt(scmInvCountingCostCenterEntry.getAmt().subtract(scmInvCountingCostCenterEntry.getAccountAmt()).add(scmInvCountingCostCenterEntry.getProductAddAmt()));
					scmInvCountingCostCenterEntry.setDifferPieQty(scmInvCountingCostCenterEntry.getPieQty().subtract(scmInvCountingCostCenterEntry.getAccountPieQty()));
					scmInvCountingCostCenterEntry.setDifferQty(scmInvCountingCostCenterEntry.getQty().subtract(scmInvCountingCostCenterEntry.getAccountQty()).add(scmInvCountingCostCenterEntry.getProductAddQty()));
					scmInvCountingCostCenterEntry.setDifferTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAccountTaxAmt()).add(scmInvCountingCostCenterEntry.getProductAddTaxAmt()));
					scmInvCountingCostCenterEntryBiz.add(scmInvCountingCostCenterEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = (ScmInvCountingCostCenter2) bean.getList().get(0);
		if(scmInvCountingCostCenter != null && scmInvCountingCostCenter.getTableId() > 0){
			String regOrgUnitNo="";
			String finOrgUnitNo="";
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.COSTTOFIN, scmInvCountingCostCenter.getOrgUnitNo(), false, null, param);
			if(orgCompanyList!=null && !orgCompanyList.isEmpty()) {
				finOrgUnitNo = orgCompanyList.get(0).getOrgUnitNo();
				List<OrgResource2> orgResourceList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.RESTOFIN, orgCompanyList.get(0).getOrgUnitNo(), false, null, param);
				if(orgResourceList!=null && !orgResourceList.isEmpty())
					regOrgUnitNo = orgResourceList.get(0).getOrgUnitNo();
			}
			//更新盘存明细
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = bean.getList2();
			List<ScmInvCountingCostCenterProduct2> scmInvCountingCostCenterProductList = bean.getList3();
			if(scmInvCountingCostCenterProductList!=null && !scmInvCountingCostCenterProductList.isEmpty()) {
				int lineId = 1;
				for(ScmInvCountingCostCenterProduct2 scmInvCountingCostCenterProduct:scmInvCountingCostCenterProductList) {
					scmInvCountingCostCenterProduct.setLineId(lineId);
					scmInvCountingCostCenterProduct.setTableId(scmInvCountingCostCenter.getTableId());
					lineId = lineId+1;
				}
			}
			if(sysParamBiz.getValue(finOrgUnitNo, "SCM_InvAssistTask","N", param).equals("Y")) {
				addProductItems(scmInvCountingCostCenter,regOrgUnitNo,scmInvCountingCostCenter.getBizDate(),scmInvCountingCostCenterEntryList,scmInvCountingCostCenterProductList,param);
			}
			scmInvCountingCostCenterProductBiz.update(scmInvCountingCostCenter, scmInvCountingCostCenterProductList, ScmInvCountingCostCenterProduct.FN_TABLEID, param);
			generateProductAddData(regOrgUnitNo,scmInvCountingCostCenter.getBizDate(),scmInvCountingCostCenterEntryList,scmInvCountingCostCenterProductList,param);
			if(scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()){
				int lineId = 1;
				generateLot(scmInvCountingCostCenter,scmInvCountingCostCenterEntryList,param);
				for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry : scmInvCountingCostCenterEntryList){
					scmInvCountingCostCenterEntry.setLineId(lineId);
					scmInvCountingCostCenterEntry.setTableId(scmInvCountingCostCenter.getTableId());
					scmInvCountingCostCenterEntry.setAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setTaxAmt(scmInvCountingCostCenterEntry.getQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setProductAddAmt(scmInvCountingCostCenterEntry.getProductAddQty().multiply(scmInvCountingCostCenterEntry.getPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setProductAddTaxAmt(scmInvCountingCostCenterEntry.getProductAddQty().multiply(scmInvCountingCostCenterEntry.getTaxPrice()).setScale(2, RoundingMode.HALF_UP));
					scmInvCountingCostCenterEntry.setDifferAmt(scmInvCountingCostCenterEntry.getAmt().subtract(scmInvCountingCostCenterEntry.getAccountAmt()).add(scmInvCountingCostCenterEntry.getProductAddAmt()));
					scmInvCountingCostCenterEntry.setDifferPieQty(scmInvCountingCostCenterEntry.getPieQty().subtract(scmInvCountingCostCenterEntry.getAccountPieQty()));
					scmInvCountingCostCenterEntry.setDifferQty(scmInvCountingCostCenterEntry.getQty().subtract(scmInvCountingCostCenterEntry.getAccountQty()).add(scmInvCountingCostCenterEntry.getProductAddQty()));
					scmInvCountingCostCenterEntry.setDifferTaxAmt(scmInvCountingCostCenterEntry.getTaxAmt().subtract(scmInvCountingCostCenterEntry.getAccountTaxAmt()).add(scmInvCountingCostCenterEntry.getProductAddTaxAmt()));
					lineId = lineId+1;
				}
				scmInvCountingCostCenterEntryBiz.update(scmInvCountingCostCenter, scmInvCountingCostCenterEntryList, ScmInvCountingCostCenterEntry2.FN_TABLEID, param);
			}
			
		}
	}
	
	/**
	 * 
	 * @param scmInvCountingCostCenter 盘存部门
	 * @param regOrgUnitNo资源组织
	 * @param bizDate业务日期
	 * @param scmInvCountingCostCenterEntryList盘存明细
	 * @param scmInvCountingCostCenterProductList盘存半成品明细
	 * @param param
	 */
	private void addProductItems(ScmInvCountingCostCenter2 scmInvCountingCostCenter, String regOrgUnitNo, Date bizDate,
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList,
			List<ScmInvCountingCostCenterProduct2> scmInvCountingCostCenterProductList, Param param) {
		String invOrgunitno = "";
		List<OrgStorage2> orgStorage2s = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmInvCountingCostCenter.getOrgUnitNo(), false, null, param);
		if (orgStorage2s != null && !orgStorage2s.isEmpty()) {
			invOrgunitno = orgStorage2s.get(0).getOrgUnitNo();
		}
		if (scmInvCountingCostCenterProductList != null && !scmInvCountingCostCenterProductList.isEmpty()) {
			StringBuffer productItem = new StringBuffer();//配方历史物资
			StringBuffer costItems = new StringBuffer();//盘存明细物资
			StringBuffer addItems = new StringBuffer();//需要新增的盘存物资
			if (scmInvCountingCostCenterEntryList != null && !scmInvCountingCostCenterEntryList.isEmpty()) {
				for (ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry2 : scmInvCountingCostCenterEntryList) {
					costItems.append(",").append(scmInvCountingCostCenterEntry2.getItemId());
				}
				if (StringUtils.isNotBlank(costItems.toString())) {
					costItems.append(",");
				}
			}
			for (ScmInvCountingCostCenterProduct scmInvCountingCostCenterProduct : scmInvCountingCostCenterProductList) {
				List<ScmProductCostCardDetailHistory> scmProductCostCardDetailHistoryList = scmProductCostCardDetailHistoryBiz
						.selectByProductIdAndDate(regOrgUnitNo, scmInvCountingCostCenterProduct.getItemId(), bizDate,param);
				if (scmProductCostCardDetailHistoryList != null && !scmProductCostCardDetailHistoryList.isEmpty()) {
					for (ScmProductCostCardDetailHistory scmProductCostCardDetailHistory : scmProductCostCardDetailHistoryList) {
						if (StringUtils.isNotBlank(productItem.toString())) {
							productItem.append(",");
						}
						productItem.append(scmProductCostCardDetailHistory.getItemId());
					}
				}
			}
			if (StringUtils.isNotBlank(productItem.toString()) && StringUtils.isNotBlank(costItems.toString())) {
				List<String> produciItemList = Arrays.asList(productItem.toString().split(","));
				for (String string : produciItemList) {
					if (!StringUtils.contains(costItems.toString(), ","+string+",")) {
						//不存在盘存明细中的配方明细物资
						if (StringUtils.isNotBlank(addItems.toString())) {
							addItems.append(",");
						}
						addItems.append(string);
					}
				}
			}
			if (StringUtils.isNotBlank(addItems.toString())) {
				Page page = new Page();
				page.setModelClass(ScmMaterial2.class);
				page.setShowCount(Integer.MAX_VALUE);
				ArrayList argList = new ArrayList();
				argList.add("controlUnitNo="+param.getControlUnitNo());
				argList.add("orgUnitNo="+invOrgunitno);
				page.setSqlCondition("scmmaterial.id in ("+addItems.toString()+")");
				List<ScmMaterial2> scmMaterial2List = scmMaterialBiz.queryPage(page, argList, "findByInvAllPage", param);
				if (scmMaterial2List != null && !scmMaterial2List.isEmpty()) {
					int i=scmInvCountingCostCenterEntryList.size()+1;
					for (ScmMaterial2 scmMaterial2 : scmMaterial2List) {
						ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry2 = new ScmInvCountingCostCenterEntry2(true);
						scmInvCountingCostCenterEntry2.setTableId(scmInvCountingCostCenter.getTableId());
						scmInvCountingCostCenterEntry2.setLineId(i+1);
						scmInvCountingCostCenterEntry2.setItemId(scmMaterial2.getId());
						scmInvCountingCostCenterEntry2.setUnit(scmMaterial2.getUnitId());
						scmInvCountingCostCenterEntry2.setUsrAdd(true);
						i=i+1;
						scmInvCountingCostCenterEntryList.add(scmInvCountingCostCenterEntry2);
					}
				}
			}
		}

	}

	private void generateProductAddData(String orgUnitNo,Date bizDate,List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList,
			List<ScmInvCountingCostCenterProduct2> scmInvCountingCostCenterProductList,Param param) {
		HashMap<Long,BigDecimal> addQtyMap = new HashMap<Long,BigDecimal>();
		HashMap<Long,Long> addItemUnitMap = new HashMap<Long,Long>();
		if(scmInvCountingCostCenterProductList!=null && !scmInvCountingCostCenterProductList.isEmpty()) {
			for(ScmInvCountingCostCenterProduct scmInvCountingCostCenterProduct:scmInvCountingCostCenterProductList) {
				List<ScmProductCostCardDetailHistory> scmProductCostCardDetailHistoryList = scmProductCostCardDetailHistoryBiz
						.selectByProductIdAndDate(orgUnitNo,scmInvCountingCostCenterProduct.getItemId(), bizDate, param);
				if(scmProductCostCardDetailHistoryList!=null && !scmProductCostCardDetailHistoryList.isEmpty()) {
					BigDecimal convertRate = BigDecimal.ONE;
					boolean exists=false;
					for(ScmProductCostCardDetailHistory scmProductCostCardDetailHistory:scmProductCostCardDetailHistoryList) {
						addItemUnitMap.put(scmProductCostCardDetailHistory.getItemId(), scmProductCostCardDetailHistory.getCstUnit());
						if(scmProductCostCardDetailHistory.getProductUnit()!=scmInvCountingCostCenterProduct.getUnit() && !exists) {
							BigDecimal productConvRate = ScmMaterialUtil.getConvRate(scmInvCountingCostCenterProduct.getItemId(), scmProductCostCardDetailHistory.getProductUnit(), param);//加工单位转换系数
							BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvCountingCostCenterProduct.getItemId(), scmInvCountingCostCenterProduct.getUnit(), param);//库存单位转换系数
							convertRate = invConvRate.divide(productConvRate, 4, RoundingMode.HALF_UP);
							exists = true;
						}
						BigDecimal addQty = scmInvCountingCostCenterProduct.getQty().multiply(convertRate)
								.divide(scmProductCostCardDetailHistory.getProductQty(),4,RoundingMode.HALF_UP)
								.multiply(scmProductCostCardDetailHistory.getQty()).setScale(4, RoundingMode.HALF_UP);
						if(addQtyMap.containsKey(scmProductCostCardDetailHistory.getItemId())) {
							addQtyMap.put(scmProductCostCardDetailHistory.getItemId(),addQtyMap.get(scmProductCostCardDetailHistory.getItemId()).add(addQty));
						}else {
							addQtyMap.put(scmProductCostCardDetailHistory.getItemId(), addQty);
						}
					}
				}
			}
		}
		if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()) {
			for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry:scmInvCountingCostCenterEntryList) {
				BigDecimal convertRate = BigDecimal.ONE;
				Long historyUnit = addItemUnitMap.get(scmInvCountingCostCenterEntry.getItemId());
				if (historyUnit != null) {
					if (historyUnit != scmInvCountingCostCenterEntry.getUnit()) {
						BigDecimal productConvRate = ScmMaterialUtil.getConvRate(scmInvCountingCostCenterEntry.getItemId(), historyUnit, param);//加工单位转换系数
						BigDecimal invConvRate = ScmMaterialUtil.getConvRate(scmInvCountingCostCenterEntry.getItemId(), scmInvCountingCostCenterEntry.getUnit(), param);//库存单位转换系数
						convertRate = productConvRate.divide(invConvRate, 4, RoundingMode.HALF_UP);
						BigDecimal addQty = addQtyMap.get(scmInvCountingCostCenterEntry.getItemId());
						if (addQty != null) {
							addQtyMap.put(scmInvCountingCostCenterEntry.getItemId(), addQty.multiply(convertRate));
						}
					}
				}
				scmInvCountingCostCenterEntry.setProductAddQty(addQtyMap.get(scmInvCountingCostCenterEntry.getItemId())==null?BigDecimal.ZERO:addQtyMap.get(scmInvCountingCostCenterEntry.getItemId()));
			}
		}
	}

	private void generateLot(ScmInvCountingCostCenter2 scmInvCountingCostCenter,List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList,Param param){
		ScmInvCountingTask2 scmInvCountingTask = scmInvCountingTaskBiz.select(scmInvCountingCostCenter.getTaskId(), param);
		if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()){
            String addLot = CodeAutoCalUtil.autoAddOne("00");
            for(int i= 0;i<scmInvCountingCostCenterEntryList.size();i++){
            	if(!scmInvCountingCostCenterEntryList.get(i).isUsrAdd()){
            		continue;
            	}
                if(StringUtils.isBlank(scmInvCountingCostCenterEntryList.get(i).getLot())){
                    scmInvCountingCostCenterEntryList.get(i).setLot((scmInvCountingTask.getTaskNo())+"-"+addLot);
                    long itemId = scmInvCountingCostCenterEntryList.get(i).getItemId();
                    String outLot = addLot;
                    for(int j= i+1;j<scmInvCountingCostCenterEntryList.size();j++){
                    	if(StringUtils.isBlank(scmInvCountingCostCenterEntryList.get(j).getLot())){
                    		if(itemId == scmInvCountingCostCenterEntryList.get(j).getItemId()){
                    			outLot = CodeAutoCalUtil.autoAddOne(outLot);
                               	scmInvCountingCostCenterEntryList.get(j).setLot((scmInvCountingTask.getTaskNo())+"-"+outLot);
                            }
                    	}
                    }
                }
            }
        }
	}
	
	@Override
	protected void afterDelete(ScmInvCountingCostCenter2 entity, Param param) throws AppException {
		if(entity != null && entity.getTableId() > 0){
			//删除盘存明细
			scmInvCountingCostCenterEntryBiz.deleteByTableId(entity.getTableId(), param);
			//删除盘存半成品
			scmInvCountingCostCenterProductBiz.deleteByTableId(entity.getTableId(), param);
		}
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmInvCountingCostCenter2 scmInvCountingCostCenter = (ScmInvCountingCostCenter2) bean.getList().get(0);
		if(scmInvCountingCostCenter != null && scmInvCountingCostCenter.getTaskId() > 0){
			ScmInvCountingTask2 scmInvCountingTask = scmInvCountingTaskBiz.select(scmInvCountingCostCenter.getTaskId(), param);
			if(scmInvCountingTask!=null && !StringUtils.equals(scmInvCountingTask.getStatus(),"B") && StringUtils.contains("O,C", scmInvCountingTask.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.ScmInvCountingCostCenter.handlecounting.statusError", new String[]{scmInvCountingTask.getTaskNo()}));
			}
		}
	}
	
	@Override
	public void deleteNotExist(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			((ScmInvCountingCostCenterDAO) dao).deleteNotExist(map);
		}
	}
	@Override
	public List<ScmInvCountingCostCenter2> findDifference(long taskId, Param param) throws AppException {
		if(taskId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("taskId", taskId);
			return ((ScmInvCountingCostCenterDAO) dao).findDifference(map);
		}
		return null;
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(long taskId,boolean showSum, Param param) throws AppException {
		return scmInvCountingCostCenterEntryBiz.queryCountCostTaskDiff(taskId,showSum,param);
	}

	@Override
	public List<ScmInvCountingCostCenter2> selectByTaskNo(String taskNo,String controlUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("taskNo", taskNo);
		return ((ScmInvCountingCostCenterDAO) dao).selectByTaskNo(map);
	}

	@Override
	public ScmInvCountingCostCenter2 selectByTaskNoAndUseOrgUnitNo(String taskNo, String deptNo, String controlUnitNo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", controlUnitNo);
		map.put("taskNo", taskNo);
		map.put("useOrgUnitNo", deptNo);
		ScmInvCountingCostCenter2 scmInvCountingCostCenter =  ((ScmInvCountingCostCenterDAO) dao).selectByTaskNoAndUseOrgUnitNo(map);
		if(scmInvCountingCostCenter!=null){
			this.setConvertMap(scmInvCountingCostCenter, param);
		}
		return scmInvCountingCostCenter;
	}
	
}
