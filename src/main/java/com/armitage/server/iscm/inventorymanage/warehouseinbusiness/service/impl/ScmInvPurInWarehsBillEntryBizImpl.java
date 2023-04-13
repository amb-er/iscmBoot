package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmCostUseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialGroupBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntryAdvQuery;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillEntryBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReturnsEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgStorageBiz;
import org.springframework.stereotype.Service;

@Service("scmInvPurInWarehsBillEntryBiz")
public class ScmInvPurInWarehsBillEntryBizImpl extends BaseBizImpl<ScmInvPurInWarehsBillEntry2> implements ScmInvPurInWarehsBillEntryBiz {

	private ScmPurReceiveEntryBiz scmPurReceiveEntryBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private OrgStorageBiz orgStorageBiz;
	private OrgAdminBiz orgAdminBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz;
	private ScmPurReturnsEntryBiz scmPurReturnsEntryBiz;
	private ScmMaterialGroupBiz scmMaterialGroupBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmCostUseTypeBiz scmCostUseTypeBiz;

	public ScmCostUseTypeBiz getScmCostUseTypeBiz() {
		return scmCostUseTypeBiz;
	}

	public void setScmCostUseTypeBiz(ScmCostUseTypeBiz scmCostUseTypeBiz) {
		this.scmCostUseTypeBiz = scmCostUseTypeBiz;
	}
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setOrgStorageBiz(OrgStorageBiz orgStorageBiz) {
		this.orgStorageBiz = orgStorageBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setScmPurReceiveEntryBiz(ScmPurReceiveEntryBiz scmPurReceiveEntryBiz) {
		this.scmPurReceiveEntryBiz = scmPurReceiveEntryBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setScmInvPurInWarehsBillBiz(
			ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz) {
		this.scmInvPurInWarehsBillBiz = scmInvPurInWarehsBillBiz;
	}

	public void setScmPurReturnsEntryBiz(ScmPurReturnsEntryBiz scmPurReturnsEntryBiz) {
		this.scmPurReturnsEntryBiz = scmPurReturnsEntryBiz;
	}

	public void setScmMaterialGroupBiz(ScmMaterialGroupBiz scmMaterialGroupBiz) {
		this.scmMaterialGroupBiz = scmMaterialGroupBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}
	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgStorage2> orgStorageList = orgStorageBiz.findChild(param.getOrgUnitNo(), param);
		if (orgStorageList != null && !orgStorageList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgStorage2 orgStorage : orgStorageList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." + ScmInvPurInWarehsBill2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	
	@Override
	protected void afterSelect(ScmInvPurInWarehsBillEntry2 entity, Param param) throws AppException {
		if(entity != null){
			setConvertMap(entity,param);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry:(List<ScmInvPurInWarehsBillEntry2>)list){
				setConvertMap(scmInvPurInWarehsBillEntry,param);
			}
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = ((ScmInvPurInWarehsBillEntryDAO) dao).selectByWrId(map);
			if (scmInvPurInWarehsBillEntryList != null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
				StringBuffer itemids = new StringBuffer("");
				List<ScmMaterial2> invScmMaterial2s=new ArrayList<>();
				for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry2 : scmInvPurInWarehsBillEntryList) {
					if (StringUtils.isNotBlank(itemids.toString())) {
						itemids.append(",");
					}
					itemids.append(scmInvPurInWarehsBillEntry2.getItemId());
				}
				if (StringUtils.isNotBlank(itemids.toString())) {
					invScmMaterial2s=scmMaterialBiz.findByInvItemIds(param.getControlUnitNo(), param.getOrgUnitNo(), itemids.toString(), param);
				}
				for (ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry : scmInvPurInWarehsBillEntryList) {
					if (invScmMaterial2s != null && !invScmMaterial2s.isEmpty()) {
						for (ScmMaterial2 scmMaterial2 : invScmMaterial2s) {
							if (scmMaterial2.getId() == scmInvPurInWarehsBillEntry.getItemId()) {
								scmInvPurInWarehsBillEntry.setPeriodValid(scmMaterial2.getPeriodValid());
								scmInvPurInWarehsBillEntry.setPeriodValidDays(scmMaterial2.getPeriodValidDays());
							}
						}
					}
					setConvertMap(scmInvPurInWarehsBillEntry, param);
				}
			}
			return scmInvPurInWarehsBillEntryList;
		}
		return null;
	}

	@Override
	public void deleteByWrId(long wrId, Param param) throws AppException {
		if(wrId > 0){
			ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectDirect(wrId, param);
			List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = this.selectByWrId(wrId,param);
			if(StringUtils.equals("1", scmInvPurInWarehsBill.getBizType())){
				if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()) {
					StringBuffer ids = new StringBuffer("");
					long id=0;
					for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry:scmInvPurInWarehsBillEntryList) {
						scmInvPurInWarehsBillEntry.setNotWriteBack(true);
						if(StringUtils.isNotBlank(ids.toString()))
							ids.append(",");
						ids.append(scmInvPurInWarehsBillEntry.getSourceBillDtlId());
						this.delete(scmInvPurInWarehsBillEntry,param);
						if(scmInvPurInWarehsBillEntry.getSourceBillDtlId()>0)
							id = scmInvPurInWarehsBillEntry.getSourceBillDtlId();
					}
					if(StringUtils.isNotBlank(ids.toString())) {
						scmPurReceiveEntryBiz.clearInvQty(ids.toString(),param);
					}
					ScmPurReceiveEntry2 scmPurReceiveEntry = scmPurReceiveEntryBiz.selectDirect(id, param);
					scmPurReceiveEntryBiz.updateBillStatusByEntry(scmPurReceiveEntry, param);
				}
			}else {
				this.delete(scmInvPurInWarehsBillEntryList, param);
			}
		}
	}
	
	@Override
	public List<ScmInvPurInWarehsBillEntry2> checkStock(long wrId, Param param) throws AppException {
		if(wrId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("wrId", wrId);
			return ((ScmInvPurInWarehsBillEntryDAO) dao).checkStock(map);
		}
		return null;
	}

	@Override
	protected void afterAdd(ScmInvPurInWarehsBillEntry2 entity, Param param) throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectDirect(entity.getWrId(), param);
		if(StringUtils.equals("1", scmInvPurInWarehsBill.getBizType())){
			if(!entity.isNotWriteBack()) {
			scmPurReceiveEntryBiz.writeBackByInWarehs(null,entity,param);
			}
		}else if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType())){
			scmPurReturnsEntryBiz.writeBackByInWarehs(null,entity,param);
		}
		scmInvPurInWarehsBillBiz.updateVersion(scmInvPurInWarehsBill, param);
	}

	@Override
	protected void afterDelete(ScmInvPurInWarehsBillEntry2 entity, Param param)
			throws AppException {
		
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectDirect(entity.getWrId(), param);
		if(StringUtils.equals("1", scmInvPurInWarehsBill.getBizType())){
			//入库
//			if(!entity.isNotWriteBack()) {
//				scmPurReceiveEntryBiz.writeBackByInWarehs(entity,null,param);
//			}
		}else if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType())){
			//退货
			scmPurReturnsEntryBiz.writeBackByInWarehs(entity,null,param);
		}
		scmInvPurInWarehsBillBiz.updateVersion(scmInvPurInWarehsBill, param);
	}

	@Override
	protected void afterUpdate(ScmInvPurInWarehsBillEntry2 oldEntity,ScmInvPurInWarehsBillEntry2 newEntity, Param param)
			throws AppException {
		ScmInvPurInWarehsBill2 scmInvPurInWarehsBill = scmInvPurInWarehsBillBiz.selectDirect(newEntity.getWrId(), param);
		if(StringUtils.equals("1", scmInvPurInWarehsBill.getBizType())){
			scmPurReceiveEntryBiz.writeBackByInWarehs(oldEntity,newEntity,param);
		}else if(StringUtils.equals("6", scmInvPurInWarehsBill.getBizType())){
			scmPurReturnsEntryBiz.writeBackByInWarehs(oldEntity,newEntity,param);
		}
		scmInvPurInWarehsBillBiz.updateVersion(scmInvPurInWarehsBill, param);
	}
	
	private void setConvertMap(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsBillEntry, Param param){
		if (scmInvPurInWarehsBillEntry.getBuyerId() > 0){
			//采购员
			ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmInvPurInWarehsBillEntry.getBuyerId(), param);
			if (scmPurBuyer != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_BUYERID, scmPurBuyer);
			}
		}
		if (scmInvPurInWarehsBillEntry.getWareHouseId() > 0){
			//仓库
			ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmInvPurInWarehsBillEntry.getWareHouseId(), param);
			if (scmInvWareHouse != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, scmInvWareHouse);
				scmInvPurInWarehsBillEntry.setWareHouseName(scmInvWareHouse.getWhName());
				scmInvPurInWarehsBillEntry.setWareHouseNo(scmInvWareHouse.getWhNo());
			}
		}
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getOrgUnitNo())){
			//库存组织
			OrgStorage2 orgStorage = orgStorageBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getOrgUnitNo(), param);
			if (orgStorage != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_ORGUNITNO, orgStorage);
			}
		}
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getFinOrgUnitNo())){
			//财务组织
			OrgCompany2 orgCompany = orgCompanyBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getFinOrgUnitNo(), param);
			if (orgCompany != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_FINORGUNITNO, orgCompany);
				scmInvPurInWarehsBillEntry.setFinOrgUnitName(orgCompany.getOrgUnitName());
			}
		}
		if (scmInvPurInWarehsBillEntry.getCostUseTypeId() > 0 ) {
			ScmCostUseType scmCostUseType = scmCostUseTypeBiz.select(scmInvPurInWarehsBillEntry.getCostUseTypeId(), param);
			if (scmCostUseType != null) {
				ScmCostUseSet2 costUseSet = new ScmCostUseSet2();
				costUseSet.setCostUseTypeId(scmCostUseType.getId());
				costUseSet.setScmCostUseTypeName(scmCostUseType.getName());
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_COSTUSETYPEID, costUseSet);
			}
        }else {
        	scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_COSTUSETYPEID, new ScmCostUseSet2());
        }
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getUseOrgUnitNo())){
			//存货部门
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getUseOrgUnitNo(), param);
			if (orgAdmin != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_USEORGUNITNO, orgAdmin);
				scmInvPurInWarehsBillEntry.setUseOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
		if (scmInvPurInWarehsBillEntry.getTaxRate() != null){
			//税率
			PubSysBasicInfo pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmInvPurInWarehsBillEntry.getTaxRate().toString(), null, param);
			if (pubSysBasicInfo != null) {
				scmInvPurInWarehsBillEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_TAXRATESTR, pubSysBasicInfo);
			}
		}
		if (scmInvPurInWarehsBillEntry.getVendorId() > 0){
			//供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmInvPurInWarehsBillEntry.getVendorId(), param);
			if (scmsupplier != null) {
				scmInvPurInWarehsBillEntry.setVendorName(scmsupplier.getVendorName());
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_VENDORID, scmsupplier);
			}
		}
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getRequireOrgUnitNo())){
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getRequireOrgUnitNo(), param);
			if (orgAdmin != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_REQUIREORGUNITNO, orgAdmin);
				scmInvPurInWarehsBillEntry.setRequireOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getPurOrgUnitNo())){
			OrgPurchase2 orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmInvPurInWarehsBillEntry.getPurOrgUnitNo(), param);
			if(orgPurchase!=null)
				scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_PURORGUNITNO, orgPurchase);
		}
		if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntry.getLot())){
			ScmInvStock2 scmInvStock = new ScmInvStock2(true);
			scmInvStock.setLot(scmInvPurInWarehsBillEntry.getLot());
			scmInvPurInWarehsBillEntry.setConvertMap(ScmInvPurInWarehsBillEntry2.FN_LOT, scmInvStock);
		}
		//物资编码
		if (scmInvPurInWarehsBillEntry.getItemId()>0) {
			ScmMaterial2 scmMaterial2 = scmMaterialBiz.selectByItemId(scmInvPurInWarehsBillEntry.getItemId(), param);
			if (scmMaterial2 != null) {
				scmInvPurInWarehsBillEntry.setConvertMap(scmInvPurInWarehsBillEntry.FN_ITEMNO, scmMaterial2);
			}
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("wrId", wrId);
		return ((ScmInvPurInWarehsBillEntryDAO) dao).selectOutEffectRow(map);
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectCancelOutEffectRow(long wrId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("wrId", wrId);
		return ((ScmInvPurInWarehsBillEntryDAO) dao).selectCancelOutEffectRow(map);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmInvPurInWarehsBillEntryAdvQuery) {
				ScmInvPurInWarehsBillEntryAdvQuery scmInvPurInWarehsBillEntryAdvQuery = (ScmInvPurInWarehsBillEntryAdvQuery) page.getModel();
				if(scmInvPurInWarehsBillEntryAdvQuery.getVendorId()>0){
					page.getParam().put(ScmInvPurInWarehsBill2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmInvPurInWarehsBillEntryAdvQuery.getVendorId())));
				}
				if (StringUtils.isNotBlank(scmInvPurInWarehsBillEntryAdvQuery.getItemId())) {
					page.getParam().put(ScmInvPurInWarehsBillEntry.FN_ITEMID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry.class) + "." +ScmInvPurInWarehsBillEntry2.FN_ITEMID, QueryParam.QUERY_IN,scmInvPurInWarehsBillEntryAdvQuery.getItemId()));
				}
				if(scmInvPurInWarehsBillEntryAdvQuery.getBizDateFrom()!=null){
					if(scmInvPurInWarehsBillEntryAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getBizDateFrom())));
					}
				}else if(scmInvPurInWarehsBillEntryAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getBizDateTo())));
				}
				if(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateFrom()!=null){
					if(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateFrom())));
					}
				}else if(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmInvPurInWarehsBill2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmInvPurInWarehsBillEntryAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntryAdvQuery.getBizType())){
					page.getParam().put(ScmInvPurInWarehsBill2.FN_BIZTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBill2.class) + "." +ScmInvPurInWarehsBill2.FN_BIZTYPE, QueryParam.QUERY_EQ,scmInvPurInWarehsBillEntryAdvQuery.getBizType()));
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntryAdvQuery.getDeptOrWarehs())){
					if(StringUtils.equals("W", scmInvPurInWarehsBillEntryAdvQuery.getStoreType())) {
						page.getParam().put(ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "." +ScmInvPurInWarehsBillEntry2.FN_WAREHOUSEID, QueryParam.QUERY_EQ,scmInvPurInWarehsBillEntryAdvQuery.getDeptOrWarehs()));
					}else {
						page.getParam().put(ScmInvPurInWarehsBillEntry2.FN_USEORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmInvPurInWarehsBillEntry2.class) + "." +ScmInvPurInWarehsBillEntry2.FN_USEORGUNITNO, QueryParam.QUERY_EQ,scmInvPurInWarehsBillEntryAdvQuery.getDeptOrWarehs()));
					}
				}
				if(StringUtils.isNotBlank(scmInvPurInWarehsBillEntryAdvQuery.getClassId())) {
					StringBuffer classIds = new StringBuffer("");
					String[] split = scmInvPurInWarehsBillEntryAdvQuery.getClassId().split(",");
					for (String string : split) {
						if(StringUtils.isNotBlank(classIds.toString()))
							classIds.append(",");
						classIds.append(string);
						List<ScmMaterialGroup> scmMaterialGroupList = scmMaterialGroupBiz.findChild(Long.valueOf(string), param);
						if(scmMaterialGroupList!=null && !scmMaterialGroupList.isEmpty()) {
							for(ScmMaterialGroup scmMaterialGroup:scmMaterialGroupList) {
								if(StringUtils.isNotBlank(classIds.toString()))
									classIds.append(",");
								classIds.append(scmMaterialGroup.getId());
							}
						}
					}
					if(StringUtils.isNotBlank(classIds.toString()))
						page.getParam().put(ScmMaterialGroup.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterialGroup.class) + "." +ScmMaterialGroup.FN_ID, QueryParam.QUERY_IN,classIds.toString()));
				}
			}
		}
	}
	
	@Override
	public ScmInvPurInWarehsBillEntry2 updateBuildAPStatus(ScmInvPurInWarehsBillEntry2 entity, Param param) throws AppException {
		if(entity != null) {
			int i = ((ScmInvPurInWarehsBillEntryDAO) dao).updateBuildAPStatus(entity);
			if(i != 1){ 
				throw new AppException(Message.getMessage(param.getLang(),"com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.impl.ScmInvPurInWarehsBillEntryBizImpl.error.updateBuildAPStatus"));
			}
		}
		return entity;
	}
	
	@Override
	public BigDecimal getInvPrice(String orgUnitNo, String itemNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("itemNo", itemNo);
		ScmInvPurInWarehsBillEntry scmInvPurInWarehsBillEntry = ((ScmInvPurInWarehsBillEntryDAO)dao).getInvPrice(map);
		if (scmInvPurInWarehsBillEntry==null) {
			return BigDecimal.ZERO;
		}
		else {
			return scmInvPurInWarehsBillEntry.getPrice()==null?BigDecimal.ZERO:scmInvPurInWarehsBillEntry.getPrice();
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(String invOrgUnitNo, String itemIds, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orgUnitNo", invOrgUnitNo);
		map.put("itemIds", itemIds);
		return ((ScmInvPurInWarehsBillEntryDAO)dao).getInvPriceList(map);
	}
}

