
package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurSupplyInfoQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgUnitRelation;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSupplyPlanBiz")
public class ScmInvSupplyPlanBizImpl extends BaseBizImpl<ScmInvSupplyPlan2> implements ScmInvSupplyPlanBiz {

	private OrgAdminBiz orgAdminBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	private UsrBiz usrBiz;
	private ScmInvSupplyPlanEntryBiz scmInvSupplyPlanEntryBiz;
	private ScmInvSupplyRuleBiz scmInvSupplyRuleBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private ScmInvSupplyRuleEntryBiz scmInvSupplyRuleEntryBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	
	private EmployeeBiz employeeBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	
	private ScmMaterialPriceBiz scmMaterialPriceBiz;

	public ScmMaterialPriceBiz getScmMaterialPriceBiz() {
		return scmMaterialPriceBiz;
	}

	public void setScmMaterialPriceBiz(ScmMaterialPriceBiz scmMaterialPriceBiz) {
		this.scmMaterialPriceBiz = scmMaterialPriceBiz;
	}

	public OrgUnitRelationBiz getOrgUnitRelationBiz() {
		return orgUnitRelationBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public EmployeeBiz getEmployeeBiz() {
		return employeeBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	public ScmPurRequireEntryBiz getScmPurRequireEntryBiz() {
		return scmPurRequireEntryBiz;
	}

	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}

	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public ScmPurRequireBiz getScmPurRequireBiz() {
		return scmPurRequireBiz;
	}

	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
	}

	public ScmInvSupplyRuleEntryBiz getScmInvSupplyRuleEntryBiz() {
		return scmInvSupplyRuleEntryBiz;
	}

	public void setScmInvSupplyRuleEntryBiz(ScmInvSupplyRuleEntryBiz scmInvSupplyRuleEntryBiz) {
		this.scmInvSupplyRuleEntryBiz = scmInvSupplyRuleEntryBiz;
	}

	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public ScmPurBuyerBiz getScmPurBuyerBiz() {
		return scmPurBuyerBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}
	public ScmInvSupplyRuleBiz getScmInvSupplyRuleBiz() {
		return scmInvSupplyRuleBiz;
	}

	public void setScmInvSupplyRuleBiz(ScmInvSupplyRuleBiz scmInvSupplyRuleBiz) {
		this.scmInvSupplyRuleBiz = scmInvSupplyRuleBiz;
	}

	public ScmInvSupplyPlanEntryBiz getScmInvSupplyPlanEntryBiz() {
		return scmInvSupplyPlanEntryBiz;
	}

	public OrgAdminBiz getOrgAdminBiz() {
		return orgAdminBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public ScmInvWareHouseBiz getScmInvWareHouseBiz() {
		return scmInvWareHouseBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public OrgPurchaseBiz getOrgPurchaseBiz() {
		return orgPurchaseBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public ScmPurchaseTypeBiz getScmPurchaseTypeBiz() {
		return scmPurchaseTypeBiz;
	}

	public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}

	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmInvSupplyPlanEntryBiz(ScmInvSupplyPlanEntryBiz scmInvSupplyPlanEntryBiz) {
		this.scmInvSupplyPlanEntryBiz = scmInvSupplyPlanEntryBiz;
	}
	
	protected void beforeFindPage(Page page, Param param)throws AppException {
		page.getParam().put(ScmInvSupplyPlan.FN_ORGUNITNO, new QueryParam("ScmInvSupplyPlan."+ ScmInvSupplyPlan.FN_ORGUNITNO,QueryParam.QUERY_EQ,param.getOrgUnitNo()));
		page.getParam().put(ScmInvSupplyPlan.FN_CONTROLUNITNO, new QueryParam("ScmInvSupplyPlan."+ ScmInvSupplyPlan.FN_CONTROLUNITNO,QueryParam.QUERY_EQ,param.getControlUnitNo()));
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		generateRequire(bean, param);
	}
	
	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		List<ScmInvSupplyPlan2> scmInvSupplyPlanList = list;
		if(scmInvSupplyPlanList != null && !scmInvSupplyPlanList.isEmpty()){
			for(ScmInvSupplyPlan2 ScmInvSupplyPlan : scmInvSupplyPlanList){
				setConvertMap(ScmInvSupplyPlan,param);
			}
		}
	}
	
	@Override
	public void generateRequire(CommonBean bean, Param param) throws AppException {
		if(bean.getList() !=null && !bean.getList().isEmpty()) {
			ScmInvSupplyPlan2 scmInvSupplyPlan = (ScmInvSupplyPlan2) bean.getList().get(0);
			List<ScmInvSupplyPlanEntry2> scmInvSupplyPlanEntry2s = bean.getList2();
			HashMap<String, Object> map = new HashMap<String, Object>();
			//获取库存组织
			ScmInvWareHouse selectByKey = scmInvWareHouseBiz.selectByKey(scmInvSupplyPlan.getWareHouseId()+"", param);
			map.put("controlUnitNo", param.getControlUnitNo());
			map.put("purOrgUnitNo", scmInvSupplyPlan.getPurOrgUnitNo());
			map.put("invOrgUnitNo", selectByKey.getOrgUnitNo());
			ScmPurRequire2 scmPurRequire = new ScmPurRequire2();
			scmPurRequire.setCreator(scmInvSupplyPlan.getExecutor());
			scmPurRequire.setOrgUnitNo(scmInvSupplyPlan.getReqOrgUnitNo());
			scmPurRequire.setBizType(scmInvSupplyPlan.getBizType());
			scmPurRequire.setControlUnitNo(scmInvSupplyPlan.getControlUnitNo());
			scmPurRequire.setPurOrgUnitNo(scmInvSupplyPlan.getPurOrgUnitNo());
			scmPurRequire.setToWareHouse(true);
			scmPurRequire.setReceiveWareHouseId(scmInvSupplyPlan.getWareHouseId());
			scmPurRequire.setStatus("I");
			scmPurRequire.setBillType("0");
			Usr usr = usrBiz.selectByCode(param.getUsrCode(), param);
			Employee employee = employeeBiz.select(usr.getEmpid(), param);
			scmPurRequire.setApplicants(employee.getEmpNo());
			scmPurRequire.setConvertMap(ScmPurRequire.FN_APPLICANTS, employee);
			scmPurRequire.setApplyDate(new Date());
			scmPurRequire.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
			HashMap relationMap = new HashMap<String, String>();
			relationMap.put(OrgUnitRelation.FN_RELATIONTYPE,OrgUnitRelationType.ADMINTOFIN);
			relationMap.put(OrgUnitRelation.FN_FROMORGUNITNO,scmPurRequire.getOrgUnitNo());
			Object select = orgUnitRelationBiz.select(relationMap, param);
			if(select != null) {
				if(select instanceof OrgUnitRelation) {
					OrgUnitRelation orgUnitRelation = (OrgUnitRelation) select;
					scmPurRequire.setFinOrgUnitNo(orgUnitRelation.getToOrgUnitNo());
				}else {
					List<OrgUnitRelation> orgUnitRelations = (List<OrgUnitRelation>) select;
			        if(orgUnitRelations==null){
			            throw new  AppException("iscm.inventorymanage.common.adminOrgUnit.notfinorg");
			        }
					scmPurRequire.setFinOrgUnitNo(orgUnitRelations.get(0).getToOrgUnitNo());
				}
			}
			scmPurRequireBiz.setConvertMapBiz(scmPurRequire, param);
//			构建请购单明细
			List<ScmPurRequireEntry2> scmPurRequireEntries = new ArrayList<>();
			int i = 1;
            List<OrgUnitRelation> orgStorageList = orgUnitRelationBiz.findByType(OrgUnitRelationType.ADMINTOINV,scmInvSupplyPlan.getReqOrgUnitNo(),param);
            String invOrgUnitNo="0";
            if(orgStorageList!=null && !orgStorageList.isEmpty()){
            	boolean exists=false;
                for(OrgUnitRelation orgStorage:orgStorageList){
                    if(orgStorage.isIsDefault()){
                        invOrgUnitNo = orgStorage.getToOrgUnitNo();
                        exists=true;
                        break;
                    }
                }
                if(!exists)
                    invOrgUnitNo = orgStorageList.get(0).getToOrgUnitNo();
            }
            StringBuffer itemIds = new StringBuffer("");
			for(ScmInvSupplyPlanEntry2 scmInvSupplyPlanEntry2 : scmInvSupplyPlanEntry2s) {
				map.put("itemId",scmInvSupplyPlanEntry2.getItemId());
				List<ScmMaterial2> scmMaterial2s = ((ScmInvSupplyPlanDAO)dao).SelectScmMaterialByItemIds(map);
				ScmPurRequireEntry2 scmPurRequireEntry = new ScmPurRequireEntry2(true);
				scmPurRequireEntry.setItemId(scmInvSupplyPlanEntry2.getItemId());
				scmPurRequireEntry.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
				scmPurRequireEntry.setLineId(i++);
				scmPurRequireEntry.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
				scmPurRequireEntry.setRecStorageOrgUnitNo(selectByKey.getOrgUnitNo());
				scmPurRequireEntry.setWareHouseId(scmPurRequire.getReceiveWareHouseId());
				scmPurRequireEntry.setQty(scmInvSupplyPlanEntry2.getQty());
				scmPurRequireEntry.setRowStatus("I");
				scmPurRequireEntry.setAmt(new BigDecimal("0"));
				scmPurRequireEntry.setOrderQty(new BigDecimal("0"));
				scmPurRequireEntry.setPrice(new BigDecimal("0"));
				scmPurRequireEntry.setItemName(scmInvSupplyPlanEntry2.getItemName());
				scmPurRequireEntry.setItemNo(scmInvSupplyPlanEntry2.getItemNo());
				scmPurRequireEntry.setPieUnit(scmInvSupplyPlanEntry2.getPieUnitId());
				scmPurRequireEntry.setBaseUnit(scmMaterial2s.get(0).getBaseUnitId());
				scmPurRequireEntry.setPurUnit(scmMaterial2s.get(0).getBaseUnitId());
				scmPurRequireEntry.setConvrate(BigDecimal.ONE);
				scmPurRequireEntry.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
				scmPurRequireEntry.setSupplyCycle(scmMaterial2s.get(0).getPurSupplyCycle());
				scmPurRequireEntry.setSpec(scmMaterial2s.get(0).getSpec());
				scmPurRequireEntry.setRecentPrice(BigDecimal.ONE);
				scmPurRequireEntry.setStockQty(BigDecimal.ONE);
				if (scmInvSupplyPlanEntry2.getPieUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmInvSupplyPlanEntry2.getPieUnitId(), param);
					if (scmMeasureUnit!=null){
						scmPurRequireEntry.setPieUnitName(scmMeasureUnit.getUnitName());
						scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_PIEUNIT, scmMeasureUnit);
					}
				}
//				if (scmMaterial2s.get(0).getUnitId()>0){
//					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial2s.get(0).getUnitId(), param);
//					if (scmMeasureUnit!=null){
//						scmPurRequireEntry.setUnitName(scmMeasureUnit.getUnitName());
//						scmPurRequireEntry.setConvertMap(ScmMaterial2.FN_UNITID, scmMeasureUnit);
//					}
//				}
				if (scmMaterial2s.get(0).getBuyerId()>0) {//采购员
					ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmMaterial2s.get(0).getBuyerId(), param);
			        if (scmPurBuyer != null) {
			        	scmPurRequireEntry.setBuyerName(scmPurBuyer.getBuyerName());
			        	scmPurRequireEntry.setPurGroupId(scmPurBuyer.getPurGroupId());
			        	scmPurRequireEntry.setConvertMap(ScmMaterialPurchase2.FN_BUYERID, scmPurBuyer);
			        } 
				}
				
				long vendorId = 0;
				ScmPurSupplyInfoQuery scmPurSupplyInfoQuery =  new ScmPurSupplyInfoQuery();
				scmPurSupplyInfoQuery.setItemId(scmPurRequireEntry.getItemId());
				scmPurSupplyInfoQuery.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
				scmPurSupplyInfoQuery.setInvOrgUnitNo(selectByKey.getOrgUnitNo());
				scmPurSupplyInfoQuery.setBizDate(scmPurRequire.getApplyDate());
//				根据物资id，采购组织，库存组织 查货源管理
				ScmMaterialPrice materialSupplyInfo = scmMaterialPriceBiz.getMaterialSupplyInfo(scmPurSupplyInfoQuery, param);
				if (materialSupplyInfo != null) {
					vendorId = materialSupplyInfo.getVendorId();
					scmPurRequireEntry.setDirectPurchase(materialSupplyInfo.isDirectPurchase());
	                scmPurRequireEntry.setVendorId(materialSupplyInfo.getVendorId());
	                scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, materialSupplyInfo);
	            }else{
	                scmPurRequireEntry.setVendorId(0);
	            }
				ScmPurPriceQuery scmPurPriceQuery = new ScmPurPriceQuery();
	            scmPurPriceQuery.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
	            scmPurPriceQuery.setVendorId(scmPurRequireEntry.getVendorId());
	            scmPurPriceQuery.setItemIds(String.valueOf(scmPurRequireEntry.getItemId()));
	            scmPurPriceQuery.setBizDate(scmPurRequire.getApplyDate());
	            scmPurPriceQuery.setUnitId(scmMaterial2s.get(0).getPurUnitId());
	            scmPurPriceQuery.setFinOrgUnitNo(scmPurRequire.getFinOrgUnitNo());
	            scmPurPriceQuery.setPmId("");
//	          根据采购组织，供应商，物资，时间，财务组织，采购单位 查价格
	            List<ScmMaterialPrice> scmMaterialPriceList = scmMaterialPriceBiz.getPreParePrice(scmPurPriceQuery, param);
	            ScmMaterialPrice scmMaterialPrice=new ScmMaterialPrice();
	            if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty())
	            {
	            	scmMaterialPrice = scmMaterialPriceList.get(0);
	            	if (scmMaterialPrice != null) {
	                    scmPurRequireEntry.setPrice(scmMaterialPrice.getTaxPrice().setScale(6, RoundingMode.HALF_UP));
	                    scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
	                    scmPurRequireEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
	                    scmPurRequireEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
	                    scmPurRequireEntry.setPriceBillNo(scmMaterialPrice.getPriceBillNo());
	                    scmPurRequireEntry.setPriceBillStatus(scmMaterialPrice.getPriceBillStatus());
	                    if (!(vendorId == 0)) {
	                        if (!(scmMaterialPrice.getPrice().compareTo(BigDecimal.ZERO) == 1) && StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())) {
	                            scmPurRequireEntry.setVendorId(materialSupplyInfo.getVendorId());
	                            scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, materialSupplyInfo);
	                        }else {
	                            scmPurRequireEntry.setVendorId(scmMaterialPrice.getVendorId());
	                            scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, scmMaterialPrice);
	                        }
	                    }else {
	                        scmPurRequireEntry.setVendorId(scmMaterialPrice.getVendorId());
	                        scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, scmMaterialPrice);
	                    }
	                    if ((scmMaterialPrice.getPreVendorId1() > 0 || scmMaterialPrice.getPreVendorId2() > 0
	                            || scmMaterialPrice.getPreVendorId3() > 0) && scmMaterialPrice.getVendorId() > 0) {
	                        scmPurRequireEntry.setMasterVendorId(scmMaterialPrice.getVendorId());
	                        scmPurRequireEntry.setMasterPrice(scmMaterialPrice.getTaxPrice());
	                        scmPurRequireEntry.setPreVendorId1(scmMaterialPrice.getPreVendorId1());
	                        scmPurRequireEntry.setPrePrice1(scmMaterialPrice.getPreTaxPrice1());
	                        scmPurRequireEntry.setPreVendorId2(scmMaterialPrice.getPreVendorId2());
	                        scmPurRequireEntry.setPrePrice2(scmMaterialPrice.getPreTaxPrice2());
	                        scmPurRequireEntry.setPreVendorId3(scmMaterialPrice.getPreVendorId3());
	                        scmPurRequireEntry.setPrePrice3(scmMaterialPrice.getPreTaxPrice3());
	                    }
	                } else {
	                    scmPurRequireEntry.setMasterVendorId(0);
	                    scmPurRequireEntry.setPrice(BigDecimal.ZERO);
	                    scmPurRequireEntry.setRefPriceStatus("0");
	                    scmPurRequireEntry.setPriceBillId(0);
	                    scmPurRequireEntry.setPriceBillNo(null);
	                    scmPurRequireEntry.setPriceBillStatus(null);
	                    scmPurRequireEntry.setVendorId(0);
	                    scmPurRequireEntry.reMoveConvertMap(ScmPurRequireEntry2.FN_VENDORID);
	                }
	            }
				scmPurRequireEntries.add(scmPurRequireEntry);
				if(StringUtils.isNotBlank(itemIds.toString()))
					itemIds.append(",");
				itemIds.append(String.valueOf(scmPurRequireEntry.getItemId()));
			}
            ScmInvPriceQuery scmInvPriceQuery = new ScmInvPriceQuery();
            scmInvPriceQuery.setInvOrgUnitNo(invOrgUnitNo);
            scmInvPriceQuery.setReqOrgUnitNo(scmInvSupplyPlan.getReqOrgUnitNo());
            scmInvPriceQuery.setItemIds(itemIds.toString());
            scmInvPriceQuery.setWarehouseId(scmPurRequire.getReceiveWareHouseId());
			List<ScmMaterialPrice> recentPriceAndStocks = scmMaterialPriceBiz.getRecentPriceAndStockList(scmInvPriceQuery,param); 
			if(recentPriceAndStocks !=null && !recentPriceAndStocks.isEmpty()) {
				for(ScmMaterialPrice scmMaterialPrice : recentPriceAndStocks) {
					for(ScmPurRequireEntry2 scmPurRequireEntry :scmPurRequireEntries) {
						if(scmPurRequireEntry.getItemId() == scmMaterialPrice.getItemId()) {
				            scmPurRequireEntry.setStockQty(scmMaterialPrice==null || scmMaterialPrice.getInvQty()==null?BigDecimal.ZERO:scmMaterialPrice.getInvQty());
				            scmPurRequireEntry.setRecentPrice(scmMaterialPrice==null || scmMaterialPrice.getPrice()==null?BigDecimal.ZERO:scmMaterialPrice.getPrice());
				            break;
						}
					}
				}
			}
			List<ScmPurRequire> scmPurRequires =  new ArrayList<ScmPurRequire>();
			scmPurRequires.add(scmPurRequire);
			CommonBean scmPurRequireBean = new CommonBean();
			scmPurRequireBean.setList(scmPurRequires);
			scmPurRequireBean.setList2(scmPurRequireEntries);
			scmPurRequireBiz.add(scmPurRequireBean, param);
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvSupplyPlan2 scmInvSupplyPlan;
		List<ScmInvSupplyPlanEntry2> scmInvSupplyEntrys = null;
		if(bean.getList()!=null && !bean.getList().isEmpty() && bean.getList().get(0) instanceof ScmInvSupplyPlan) {
			scmInvSupplyPlan = (ScmInvSupplyPlan2)bean.getList().get(0);
			setConvertMap(scmInvSupplyPlan,param);
			scmInvSupplyEntrys = scmInvSupplyPlanEntryBiz.selectByPlanId(scmInvSupplyPlan.getId(),param);
		}
		
		if(scmInvSupplyEntrys != null && !scmInvSupplyEntrys.isEmpty()) {
			bean.setList2(scmInvSupplyEntrys);
		}
	}
	
	private void setConvertMap(ScmInvSupplyPlan2 scmInvSupplyPlan,Param param) throws AppException {
		if (scmInvSupplyPlan != null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(scmInvSupplyPlan.getReqOrgUnitNo())){
				//申请组织
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvSupplyPlan.getReqOrgUnitNo(), param);
				if (orgAdmin != null) {
					scmInvSupplyPlan.setConvertMap(scmInvSupplyPlan.FN_REQORGUNITNO, orgAdmin);
				}
			}
			if (StringUtils.isNotBlank(scmInvSupplyPlan.getExecutor())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmInvSupplyPlan.getExecutor(), param);
				if (usr != null) {
					if(scmInvSupplyPlan.getDataDisplayMap()==null){
						scmInvSupplyPlan.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmInvSupplyPlan.getDataDisplayMap().put(scmInvSupplyPlan.FN_EXECUTOR, SimpleDataDisplayUtil.convert(usr));
					scmInvSupplyPlan.setConvertMap(scmInvSupplyPlan.FN_EXECUTOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmInvSupplyPlan.getPurOrgUnitNo())){
				//采购组织
				OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmInvSupplyPlan.getPurOrgUnitNo(), param);
				if (orgPurchase != null) {
					scmInvSupplyPlan.setConvertMap(scmInvSupplyPlan.FN_PURORGUNITNO, orgPurchase);
				}
			}
			if (scmInvSupplyPlan.getWareHouseId() > 0){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvSupplyPlan.getWareHouseId(), param);
				if(scmInvWareHouse!=null){
					scmInvWareHouse.setWhName(scmInvWareHouse.getWhName());
					scmInvSupplyPlan.setConvertMap(scmInvSupplyPlan.FN_WAREHOUSEID,scmInvWareHouse);
				}
			}
			if(StringUtils.isNotBlank(scmInvSupplyPlan.getBizType())){
				ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmInvSupplyPlan.getBizType(),param);
				if(scmPurchaseType!=null){
					scmInvSupplyPlan.setConvertMap(scmInvSupplyPlan.FN_BIZTYPE,scmPurchaseType);
				}
			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		if(bean.getList2()!=null && !bean.getList2().isEmpty()) {
			List<ScmInvSupplyPlanEntry> scmInvSupplyPlanEntries = bean.getList2();
			ScmInvSupplyPlan scmInvSupplyPlan =(ScmInvSupplyPlan)(bean.getList().get(0));
			for(ScmInvSupplyPlanEntry scmInvSupplyPlanEntry : scmInvSupplyPlanEntries) {
				scmInvSupplyPlanEntry.setPlantId(scmInvSupplyPlan.getId());
				scmInvSupplyPlanEntryBiz.add(scmInvSupplyPlanEntry, param);
			}
			ScmInvSupplyRule scmInvSupplyRule = scmInvSupplyRuleBiz.select(scmInvSupplyPlan.getRuleId(), param);
			scmInvSupplyRule.setExecTime(scmInvSupplyPlan.getExecTime());
			scmInvSupplyRuleBiz.update(scmInvSupplyRule, param);
		}
	}
	
	@Override
	public List<ScmInvSupplyPlan2> selectByRuleId(long id) throws AppException {
		List<ScmInvSupplyPlan2> scmInvSupplyPlans = ((ScmInvSupplyPlanDAO)dao).selectByRuleId(id);
		return scmInvSupplyPlans;
	}
	
	@Override
	public void setConvertMapBiz(ScmInvSupplyPlan2 scmInvSupplyPlan, Param param) {
		setConvertMap(scmInvSupplyPlan, param);
	}
}
