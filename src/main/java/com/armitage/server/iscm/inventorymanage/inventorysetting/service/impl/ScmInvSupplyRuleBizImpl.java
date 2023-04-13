
package com.armitage.server.iscm.inventorymanage.inventorysetting.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleWSBean;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyPlanBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleEntryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSupplyRuleBiz")
public class ScmInvSupplyRuleBizImpl extends BaseBizImpl<ScmInvSupplyRule> implements ScmInvSupplyRuleBiz {

	
	private UsrBiz usrBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmPurRequireBiz scmPurRequireBiz;
	private ScmInvSupplyRuleEntryBiz scmInvSupplyRuleEntryBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmInvSupplyPlanBiz scmInvSupplyPlanBiz;
	
	public ScmInvSupplyPlanBiz getScmInvSupplyPlanBiz() {
		return scmInvSupplyPlanBiz;
	}
	public void setScmInvSupplyPlanBiz(ScmInvSupplyPlanBiz scmInvSupplyPlanBiz) {
		this.scmInvSupplyPlanBiz = scmInvSupplyPlanBiz;
	}
	public ScmPurBuyerBiz getScmPurBuyerBiz() {
		return scmPurBuyerBiz;
	}
	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}
	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public ScmPurRequireBiz getScmPurRequireBiz() {
		return scmPurRequireBiz;
	}

	public void setScmPurRequireBiz(ScmPurRequireBiz scmPurRequireBiz) {
		this.scmPurRequireBiz = scmPurRequireBiz;
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

	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
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

	public ScmInvSupplyRuleEntryBiz getScmInvSupplyRuleEntryBiz() {
		return scmInvSupplyRuleEntryBiz;
	}

	public void setScmInvSupplyRuleEntryBiz(ScmInvSupplyRuleEntryBiz scmInvSupplyRuleEntryBiz) {
		this.scmInvSupplyRuleEntryBiz = scmInvSupplyRuleEntryBiz;
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmInvSupplyRule scmInvSupplyRule;
		List<ScmInvSupplyRuleEntry2> scmInvSupplyEntrys = null;
		if(bean.getList() !=null && !bean.getList().isEmpty() && bean.getList().get(0) instanceof ScmInvSupplyRule) {
			scmInvSupplyRule = (ScmInvSupplyRule)(bean.getList().get(0));
			setConvertMap(scmInvSupplyRule,param);
			scmInvSupplyEntrys = scmInvSupplyRuleEntryBiz.selectByRuleId(scmInvSupplyRule.getId(),param);
		}
		if(scmInvSupplyEntrys != null && !scmInvSupplyEntrys.isEmpty()) {
			bean.setList2(scmInvSupplyEntrys);
		}
	}
	
	@Override
	protected void afterDelete(ScmInvSupplyRule entity, Param param) throws AppException {
		scmInvSupplyRuleEntryBiz.deleteByRuleIds(entity.getId(), param);
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list!=null && !list.isEmpty()) {
			List<ScmInvSupplyRule> scmInvSupplyRuleList = list;
			if(scmInvSupplyRuleList != null && !scmInvSupplyRuleList.isEmpty()){
				for(ScmInvSupplyRule scmInvSupplyRule : scmInvSupplyRuleList){
					setConvertMap(scmInvSupplyRule,param);
				}
			}
		}
	}
	
	protected void beforeFindPage(Page page, Param param)throws AppException {
		page.getParam().put(ScmInvSupplyRule.FN_ORGUNITNO, new QueryParam(ScmInvSupplyRule.FN_ORGUNITNO,QueryParam.QUERY_EQ,param.getOrgUnitNo()));
		page.getParam().put(ScmInvSupplyRule.FN_CONTROLUNITNO, new QueryParam(ScmInvSupplyRule.FN_CONTROLUNITNO,QueryParam.QUERY_EQ,param.getControlUnitNo()));
	}
	
	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		if(bean.getList()!=null && !bean.getList().isEmpty()) {
			 ScmInvSupplyRule scmInvSupplyRule = (ScmInvSupplyRule)(bean.getList().get(0));
			 HashMap<String,Object> map = new HashMap();
			 map.put("orgUnitNo", scmInvSupplyRule.getOrgUnitNo());
			 map.put("wareHouseId", scmInvSupplyRule.getWareHouseId());
			 List<Long> scmInvSupplyRuleId = ((ScmInvSupplyRuleDAO)dao).selectByOrgInv(map);
			 ScmInvWareHouse selectByKey = scmInvWareHouseBiz.selectByKey(scmInvSupplyRule.getWareHouseId()+"", param);
			 if(scmInvSupplyRuleId!=null && scmInvSupplyRuleId.size() > 0 ) {
				 throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.AlreadyExist",new String[] {selectByKey.getWhName()+""});
			 }
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmInvSupplyRule scmInvSupplyRule = (ScmInvSupplyRule)(bean.getList().get(0));
		if (bean.getList2() != null && !bean.getList2().isEmpty()) {
			List<ScmInvSupplyRuleEntry2> scmInvSupplyRuleEntry2s = bean.getList2();
			List<ScmInvSupplyRuleEntry> scmInvSupplyRuleEntrys = new ArrayList<ScmInvSupplyRuleEntry>();
			for(ScmInvSupplyRuleEntry2 scmInvSupplyRuleEntry2 : scmInvSupplyRuleEntry2s) {
				if(!(scmInvSupplyRuleEntry2.getItemId() > 0)) {
					continue;
				}
				ScmInvSupplyRuleEntry scmInvSupplyRuleEntry = new ScmInvSupplyRuleEntry();
				BeanUtils.copyProperties(scmInvSupplyRuleEntry2, scmInvSupplyRuleEntry);
				scmInvSupplyRuleEntry.setRuleId(scmInvSupplyRule.getId());
				scmInvSupplyRuleEntrys.add(scmInvSupplyRuleEntry);
			}
			scmInvSupplyRuleEntryBiz.add(scmInvSupplyRuleEntrys, param);
		} 
	}

	@Override
	protected void beforeDelete(ScmInvSupplyRule entity, Param param) throws AppException {
		List<ScmInvSupplyPlan2> scmInvSupplyPlans = scmInvSupplyPlanBiz.selectByRuleId(entity.getId());
		if(scmInvSupplyPlans != null && !scmInvSupplyPlans.isEmpty()) {
			throw new AppException("iscm.inventorymanage.inventorysetting.service.ScmInvSupplyRuleBizImpl.ExistencePlan");
		}	
	}
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			scmInvSupplyRuleEntryBiz.deleteByRuleIds(((ScmInvSupplyRule)bean.getList().get(0)).getId(), param);
			afterAdd(bean, param);
		}
	}

	private void setConvertMap(ScmInvSupplyRule scmInvSupplyRule,Param param) throws AppException {
			if (scmInvSupplyRule != null){
				HashMap<String,Object> cacheMap = new HashMap<String,Object>();
				if (StringUtils.isNotBlank(scmInvSupplyRule.getReqOrgUnitNo())){
					//申请组织
					OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvSupplyRule.getReqOrgUnitNo(), param);
					if (orgAdmin != null) {
						scmInvSupplyRule.setConvertMap(scmInvSupplyRule.FN_REQORGUNITNO, orgAdmin);
					}
				}
				if (StringUtils.isNotBlank(scmInvSupplyRule.getCreator())){
					//制单人
					Usr usr = usrBiz.selectByCode(scmInvSupplyRule.getCreator(), param);
					if (usr != null) {
						if(scmInvSupplyRule.getDataDisplayMap()==null){
							scmInvSupplyRule.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
						}
						scmInvSupplyRule.getDataDisplayMap().put(scmInvSupplyRule.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
						scmInvSupplyRule.setConvertMap(scmInvSupplyRule.FN_CREATOR, usr);
					}
				}
				if (StringUtils.isNotBlank(scmInvSupplyRule.getPurOrgUnitNo())){
					//采购组织
					OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmInvSupplyRule.getPurOrgUnitNo(), param);
					if (orgPurchase != null) {
						scmInvSupplyRule.setConvertMap(scmInvSupplyRule.FN_PURORGUNITNO, orgPurchase);
					}
				}
				if (scmInvSupplyRule.getWareHouseId()>0){
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvSupplyRule.getWareHouseId(), param);
					if(scmInvWareHouse!=null){
						scmInvWareHouse.setWhName(scmInvWareHouse.getWhName());
						scmInvSupplyRule.setConvertMap(scmInvSupplyRule.FN_WAREHOUSEID,scmInvWareHouse);
					}
				}
				if(StringUtils.isNotBlank(scmInvSupplyRule.getBizType())){
					ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmInvSupplyRule.getBizType(),param);
					if(scmPurchaseType!=null){
						scmInvSupplyRule.setConvertMap(scmInvSupplyRule.FN_BIZTYPE,scmPurchaseType);
					}
				}
			}
		}

	@Override
	public ScmInvSupplyRuleWSBean generatePlan(ScmInvSupplyRule scmInvSupplyRule, Param param) throws AppException {
		List<Long> entryIds = scmInvSupplyRuleEntryBiz.selectEntryIdsByRuleId(scmInvSupplyRule.getId(),param);
		if(entryIds == null || entryIds.isEmpty())
			throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.NonEntry");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("purOrgUnitNo", scmInvSupplyRule.getPurOrgUnitNo());
		map.put("invOrgUnitNo", scmInvSupplyRule.getOrgUnitNo());
		map.put("ruleId", scmInvSupplyRule.getId());
		String entryId = entryIds.toString();
		entryId = entryId.substring(1, entryId.length()-1);
		entryId = entryId.replaceAll(" ", "");
		map.put("itemIds", entryId);
		ScmInvSupplyPlan2 scmInvSupplyPlan = new ScmInvSupplyPlan2();
		List<ScmMaterial2> scmMaterial2s = ((ScmInvSupplyRuleDAO)dao).findAllBillQtyByItemIds(map);
		String itemString = null;
		int sum = 0;
		if(scmMaterial2s == null || scmMaterial2s.isEmpty()) {
			throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.NonEntryOrMaxQtyNotExist");
		}else {
			StringBuffer itemIdString = new StringBuffer();
			boolean message = false;
			for(Long entrId : entryIds) {
				if(!scmMaterial2s.stream()
						.filter(item->item.getId()== entrId)
						.findAny().isPresent()
				  )
					{
					itemIdString.append(entrId+",");
					sum++;
					message = true;
					}
			}
			if(message) {
				String ids = (itemIdString.toString()).substring(0, itemIdString.length()-1);
				itemString = ((ScmInvSupplyRuleDAO)dao).selectByIds(ids);
				if(sum == 0 || entryIds.size() == sum) {
					throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.AllUnExist",new String[] {itemString});
				}
			}
			scmInvSupplyPlan.setExecutor(scmInvSupplyRule.getCreator());
			scmInvSupplyPlan.setExecTime(new Date());;
			scmInvSupplyPlan.setOrgUnitNo(scmInvSupplyRule.getOrgUnitNo());
			scmInvSupplyPlan.setReqOrgUnitNo(scmInvSupplyRule.getReqOrgUnitNo());
			scmInvSupplyPlan.setBizType(scmInvSupplyRule.getBizType());
			scmInvSupplyPlan.setControlUnitNo(scmInvSupplyRule.getControlUnitNo());
			scmInvSupplyPlan.setPurOrgUnitNo(scmInvSupplyRule.getPurOrgUnitNo());
			scmInvSupplyPlan.setWareHouseId(scmInvSupplyRule.getWareHouseId());
			scmInvSupplyPlan.setRuleName(scmInvSupplyRule.getRuleName());
			scmInvSupplyPlan.setRuleId(scmInvSupplyRule.getId());
			scmInvSupplyPlanBiz.setConvertMapBiz(scmInvSupplyPlan, param);
			
		}
		List<ScmInvSupplyPlanEntry2> scmInvSupplyPlanEntrys = new ArrayList<>();
		for(ScmMaterial2 scmMaterial2 : scmMaterial2s) {
			BigDecimal reQty;
			if(scmMaterial2.getMaxQty()!=null && scmMaterial2.getMaxQty().compareTo(BigDecimal.ZERO)>0) {
				reQty = scmMaterial2.getMaxQty();
				if(scmMaterial2.getStockQty()!=null && scmMaterial2.getStockQty().compareTo(BigDecimal.ZERO)>0) {
					reQty = reQty.subtract(scmMaterial2.getStockQty());
				}
				if(scmInvSupplyRule.getIncludeOr()) {
					if(scmMaterial2.getPoQty()!=null && scmMaterial2.getPoQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.subtract(scmMaterial2.getPoQty());
					}
					if(scmMaterial2.getPrQty()!=null && scmMaterial2.getPrQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.subtract(scmMaterial2.getPrQty());
					}
					if(scmMaterial2.getPvQty()!=null && scmMaterial2.getPvQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.subtract(scmMaterial2.getPvQty());
					}
					if(scmMaterial2.getInvreturnQty()!=null && scmMaterial2.getInvreturnQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.add(scmMaterial2.getInvreturnQty());
					}
					if(scmMaterial2.getInvpurOutQty()!=null && scmMaterial2.getInvpurOutQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.add(scmMaterial2.getInvpurOutQty());
					}
				}
				if(scmInvSupplyRule.getIncludeWr()) {
					if(scmMaterial2.getInvpurQty()!=null && scmMaterial2.getInvpurQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.subtract(scmMaterial2.getInvpurQty());
					}
					if(scmMaterial2.getInvotherQty()!=null && scmMaterial2.getInvotherQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.subtract(scmMaterial2.getInvotherQty());
					}
					if(scmMaterial2.getInvsaleOutQty()!=null && scmMaterial2.getInvsaleOutQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.add(scmMaterial2.getInvsaleOutQty());
					}
					if(scmMaterial2.getInvmaterialOutQty()!=null && scmMaterial2.getInvmaterialOutQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.add(scmMaterial2.getInvmaterialOutQty());
					}
					if(scmMaterial2.getInvotherOutQty()!=null && scmMaterial2.getInvotherOutQty().compareTo(BigDecimal.ZERO)>0) {
						reQty = reQty.add(scmMaterial2.getInvotherOutQty());
					}
				}
				if(!(reQty.compareTo(BigDecimal.ZERO) > 0)) {
					continue;
				}
				ScmInvSupplyPlanEntry2 scmInvSupplyPlanEntry = new ScmInvSupplyPlanEntry2();
				scmInvSupplyPlanEntry.setItemId(scmMaterial2.getId());
				scmInvSupplyPlanEntry.setLineId(scmMaterial2.getLineId());
				//获取库存组织
				ScmInvWareHouse selectByKey = scmInvWareHouseBiz.selectByKey(scmInvSupplyPlan.getWareHouseId()+"", param);
				scmInvSupplyPlanEntry.setQty(reQty);
				scmInvSupplyPlanEntry.setItemName(scmMaterial2.getItemName());
				scmInvSupplyPlanEntry.setClassName(scmMaterial2.getClassName());
				scmInvSupplyPlanEntry.setItemNo(scmMaterial2.getItemNo());
				scmInvSupplyPlanEntry.setBaseUnit(scmMaterial2.getBaseUnitId());
				scmInvSupplyPlanEntry.setPurUnitId(scmMaterial2.getPurUnitId());
				scmInvSupplyPlanEntry.setPieUnitId(scmMaterial2.getPieUnitId());
				if (scmMaterial2.getPurUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial2.getPurUnitId(), param);
					if (scmMeasureUnit!=null){
						scmInvSupplyPlanEntry.setPurUnitName(scmMeasureUnit.getUnitName());
						scmInvSupplyPlanEntry.setConvertMap(ScmMaterial2.FN_PURUNITID, scmMeasureUnit);
					}
				}
				if (scmMaterial2.getPieUnitId()>0){
					ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial2.getPieUnitId(), param);
					if (scmMeasureUnit!=null){
						scmInvSupplyPlanEntry.setPieUnitName(scmMeasureUnit.getUnitName());
						scmInvSupplyPlanEntry.setConvertMap(ScmMaterial2.FN_PIEUNITNAME, scmMeasureUnit);
					}
				}
				scmInvSupplyPlanEntry.setSpec(scmMaterial2.getSpec());
				scmInvSupplyPlanEntrys.add(scmInvSupplyPlanEntry);
			}else {
				throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.MaxQty_NonExistent");
			}
		}
		if(scmInvSupplyPlanEntrys == null || scmInvSupplyPlanEntrys.isEmpty()) {
			throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.doNotSupply");
		}
		List<ScmInvSupplyPlan2> scmInvSupplyPlans =  new ArrayList<ScmInvSupplyPlan2>();
		scmInvSupplyPlans.add(scmInvSupplyPlan);
		ScmInvSupplyRuleWSBean bean = new ScmInvSupplyRuleWSBean();
		bean.setList(scmInvSupplyPlans);
		bean.setList2(scmInvSupplyPlanEntrys);
		if(itemString != null) {
			bean.setObject10(itemString);
		}
 		return bean;
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			 ScmInvSupplyRule scmInvSupplyRule = (ScmInvSupplyRule)(bean.getList().get(0));
			 HashMap<String,Object> map = new HashMap();
			 map.put("orgUnitNo", scmInvSupplyRule.getOrgUnitNo());
			 map.put("wareHouseId", scmInvSupplyRule.getWareHouseId());
			 List<Long> scmInvSupplyRuleId = ((ScmInvSupplyRuleDAO)dao).selectByOrgInv(map);
			 ScmInvWareHouse selectByKey = scmInvWareHouseBiz.selectByKey(scmInvSupplyRule.getWareHouseId()+"", param);
			 if(scmInvSupplyRuleId != null && scmInvSupplyRuleId.size() > 0 && scmInvSupplyRule.getId() != scmInvSupplyRuleId.get(0)) {
				 throw new AppException("iscm.inventorymanage.inventorysetting.ScmInvSupplyRuleBizImpl.AlreadyExist",new String[] {selectByKey.getWhName()+""});
			 }
		}
	}
}
