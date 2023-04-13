
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

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
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplyInfoDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfoAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyRecOrgBiz;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.OrgPurchaseBiz;
import org.springframework.stereotype.Service;

@Service("scmPurSupplyInfoBiz")
public class ScmPurSupplyInfoBizImpl extends BaseBizImpl<ScmPurSupplyInfo2> implements ScmPurSupplyInfoBiz {
	private ScmPurSupplyRecOrgBiz scmPurSupplyRecOrgBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	public void setScmPurSupplyRecOrgBiz(ScmPurSupplyRecOrgBiz scmPurSupplyRecOrgBiz) {
		this.scmPurSupplyRecOrgBiz = scmPurSupplyRecOrgBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		if (orgPurchaseList != null && !orgPurchaseList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgPurchase2 orgPurchase : orgPurchaseList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgPurchase.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurSupplyInfo2.class) + "." + ScmPurSupplyInfo2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurSupplyInfo2.class) + "." + ScmPurSupplyInfo2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{

			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurSupplyInfo2.class) + "." + ScmPurSupplyInfo2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurSupplyInfo2.class) + "." + ScmPurSupplyInfo2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	public List<ScmPurSupplyInfo2> findVendor(ScmPurSupplyInfo2 scmPurSupplyInfo, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", scmPurSupplyInfo.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		if(scmPurSupplyInfo.getVendorId()>0){
			map.put("vendorId", scmPurSupplyInfo.getVendorId());
		}
		if(StringUtils.isNotBlank(scmPurSupplyInfo.getItemName())) {
			map.put("itemName", "%"+scmPurSupplyInfo.getItemName()+"%");
		}
		List<ScmPurSupplyInfo2> scmPurSupplyInfoList = ((ScmPurSupplyInfoDAO)dao).findVendor(map);
		return scmPurSupplyInfoList;
	}

	@Override
	protected void afterUpdate(ScmPurSupplyInfo2 oldEntity,
			ScmPurSupplyInfo2 newEntity, Param param)
			throws AppException {
		scmPurSupplyRecOrgBiz.updateBySupplyInfo(newEntity,newEntity.getScmPurSupplyRecOrgList(),param);
		
	}
	

	@Override
	protected void afterFindPage(List list, Page page, Param param)	throws AppException {
		for(ScmPurSupplyInfo2 scmPurSupplyInfo:(List<ScmPurSupplyInfo2>)list){
			setConvertMap(scmPurSupplyInfo,param);
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurSupplyInfo2 scmPurSupplyInfo = (ScmPurSupplyInfo2) bean.getList().get(0);
		List<ScmPurSupplyInfo2> scmPurSupplyInfoList = new ArrayList();
		ScmPurSupplyInfo2 scmPurSupplyInfo2 = new ScmPurSupplyInfo2();
		BeanUtil.copyProperties(scmPurSupplyInfo2, scmPurSupplyInfo);
		setConvertMap(scmPurSupplyInfo2,param);
		scmPurSupplyInfoList.add(scmPurSupplyInfo2);
		Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplyInfo.getVendorId(), param);
		if(scmsupplier!=null){
			scmPurSupplyInfo.setVendorName(scmsupplier.getVendorName());
			scmPurSupplyInfo.setConvertMap(ScmPurSupplyInfo2.FN_VENDORID, scmsupplier);
		}
		scmPurSupplyInfo.setScmPurSupplyInfoList(scmPurSupplyInfoList);
	}

	private void setConvertMap(ScmPurSupplyInfo2 scmPurSupplyInfo, Param param)throws AppException {
		ScmMaterial2 scmMaterial = scmMaterialBiz.findByPurItemId(param.getControlUnitNo(),param.getOrgUnitNo(),scmPurSupplyInfo.getItemId(),param);
		if(scmMaterial!=null){
			scmPurSupplyInfo.setItemNo(scmMaterial.getItemNo());
			scmPurSupplyInfo.setItemName(scmMaterial.getItemName());
			scmPurSupplyInfo.setSpec(scmMaterial.getSpec());
			if(scmMaterial.getPurUnitId()>0){
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(scmMaterial.getPurUnitId(), param);
				if(scmMeasureUnit!=null)
					scmPurSupplyInfo.setPurUnitName(scmMeasureUnit.getUnitName());
			}
			List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList = scmPurSupplyRecOrgBiz.selectBySupplyInfoId(scmPurSupplyInfo.getId(), param);
			if(scmPurSupplyRecOrgList!=null && !scmPurSupplyRecOrgList.isEmpty()){
				StringBuffer orgUnitNos= new StringBuffer("");
				StringBuffer orgUnitNames= new StringBuffer("");
				for(ScmPurSupplyRecOrg2 scmPurSupplyRecOrg:scmPurSupplyRecOrgList){
					if(StringUtils.isNotBlank(orgUnitNos.toString()))
						orgUnitNos.append(",");
					orgUnitNos.append(scmPurSupplyRecOrg.getReceiveOrgUnitNo());
					if(StringUtils.isNotBlank(orgUnitNames.toString()))
						orgUnitNames.append(",");
					orgUnitNames.append(scmPurSupplyRecOrg.getReceiveOrgUnitName());
				}
				scmPurSupplyInfo.setOrgUnitNos(orgUnitNos.toString());
				scmPurSupplyInfo.setOrgUnitNames(orgUnitNames.toString());
				scmPurSupplyInfo.setScmPurSupplyRecOrgList(scmPurSupplyRecOrgList);
			}
		}
	}

	@Override
	public ScmPurSupplyInfo2 getSupplyInfoByItem(String purOrgUnitNo,String invOrgUnitNo,Long itemId,Date bizDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("purOrgUnitNo", purOrgUnitNo);
		map.put("invOrgUnitNo", invOrgUnitNo);
		map.put("itemId", itemId);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurSupplyInfoDAO)dao).getSupplyInfoByItem(map);
	}
	
	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItemList(String purOrgUnitNo, String invOrgUnitNo, String itemIdList,Date bizDate,Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("purOrgUnitNo", purOrgUnitNo);
		map.put("invOrgUnitNo", invOrgUnitNo);
		map.put("itemId", itemIdList);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurSupplyInfoDAO)dao).getSupplyInfoByItemList(map);
	}

	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurSupplyInfoAdvQuery) {
				ScmPurSupplyInfoAdvQuery scmPurSupplyInfoAdvQuery = (ScmPurSupplyInfoAdvQuery)page.getModel();
				if(scmPurSupplyInfoAdvQuery.getVendorId()>0){
					page.getParam().put(ScmPurSupplyInfo2.FN_VENDORID,new QueryParam(ScmPurSupplyInfo2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurSupplyInfoAdvQuery.getVendorId())));
				}
				if(scmPurSupplyInfoAdvQuery.getItemId()>0){
					page.getParam().put(ScmPurSupplyInfo2.FN_ITEMID,new QueryParam(ScmPurSupplyInfo2.FN_ITEMID, QueryParam.QUERY_EQ,String.valueOf(scmPurSupplyInfoAdvQuery.getItemId())));
				}
			}
		}
	}

	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItems(String purOrgUnitNo, String invOrgUnitNo, String itemIds,Date bizDate, Param param)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("purOrgUnitNo", purOrgUnitNo);
		map.put("invOrgUnitNo", invOrgUnitNo);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurSupplyInfoDAO)dao).getSupplyInfoByItems(map);
	}

	@Override
	public void generateBySupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException {
		if(scmPurSupplierSource!=null) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("billId", scmPurSupplierSource.getId());
			((ScmPurSupplyInfoDAO)dao).addBySupplierSource(map);
			scmPurSupplyRecOrgBiz.addBySupplierSource(scmPurSupplierSource, param);
		}
	}

	@Override
	public ScmPurSupplyInfo2 getSupplyInfoByItemAndVendor(String purOrgUnitNo, String invOrgUnitNo, long vendorId, long itemId,
			Date bizDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("purOrgUnitNo", purOrgUnitNo);
		map.put("invOrgUnitNo", invOrgUnitNo);
		map.put("vendorId", vendorId);
		map.put("itemId", itemId);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurSupplyInfoDAO)dao).getSupplyInfoByItemAndVendor(map);
	}

	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItemSAndVendorS(List<ScmPurPriceQuery> list, Param param) throws AppException {
		if (list != null && !list.isEmpty()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ScmPurPriceQuery", list);
			List<ScmPurSupplyInfo2> scmPurSupplyInfoList = ((ScmPurSupplyInfoDAO)dao).getSupplyInfoByItemSAndVendorS(map);
			if(scmPurSupplyInfoList!=null && !scmPurSupplyInfoList.isEmpty()) {
				for(ScmPurSupplyInfo2 scmPurSupplyInfo:scmPurSupplyInfoList) {
					this.setConvertMap(scmPurSupplyInfo, param);
				}
			}
			return scmPurSupplyInfoList;
		}
		return null;
	}
	
}
	
