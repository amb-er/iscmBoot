package com.armitage.server.iscm.basedata.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialUnitRelationBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.system.service.SysParamBiz;

public class ScmMaterialUtil {
	private static ScmMaterialUnitRelationBiz scmMaterialUnitRelationBiz = (ScmMaterialUnitRelationBiz) AppContextUtil.getBean("scmMaterialUnitRelationBiz");
	private static ScmMaterialPriceBiz scmMaterialPriceBiz = (ScmMaterialPriceBiz) AppContextUtil.getBean("scmMaterialPriceBiz");
	private static ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
	private static SysParamBiz sysParamBiz = (SysParamBiz) AppContextUtil.getBean("sysParamBiz");
	
	public static BigDecimal getConvRate(long itemId,long unitId,Param param){
		ScmMaterialUnitRelation2 scmMaterialUnitRelation = scmMaterialUnitRelationBiz.selectByItemAndUnit(itemId, unitId, param);
		if(scmMaterialUnitRelation!=null)
			return scmMaterialUnitRelation.getConvrate();
		return BigDecimal.ONE;
	}
	
	public static ScmMaterialPrice getMaterialPrice(String purOrgUnitNo,long vendorId, String itemIds,long unitId,Date bizDate,String finOrgUnitNo,String pmId,Param param){
		List<ScmMaterialPrice> scmMaterialPriceList = ScmMaterialUtil.getMaterialPriceList(purOrgUnitNo,vendorId,itemIds,unitId,bizDate,finOrgUnitNo,pmId, param);
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			return scmMaterialPriceList.get(0);
		}
		return null;
	}

	public static List<ScmMaterialPrice> getMaterialPriceList(String purOrgUnitNo,long vendorId, String itemIds,long unitId,Date bizDate,String finOrgUnitNo, String pmId, Param param){
		ScmPurPriceQuery scmPurPriceQuery = new ScmPurPriceQuery();
		scmPurPriceQuery.setVendorId(vendorId);
		scmPurPriceQuery.setItemIds(itemIds);
		scmPurPriceQuery.setPurOrgUnitNo(purOrgUnitNo);
		scmPurPriceQuery.setBizDate(bizDate);
		scmPurPriceQuery.setFinOrgUnitNo(finOrgUnitNo);
		scmPurPriceQuery.setPmId(pmId);
		return scmMaterialPriceBiz.getMaterialPrice(scmPurPriceQuery, param);
	}

	public static ScmMaterialPrice getMaterialSalePrice(String invOrgUnitNo,long itemId,Date bizDate,Param param){
		List<ScmMaterialPrice> scmMaterialPriceList = ScmMaterialUtil.getMaterialSalePriceList(invOrgUnitNo,String.valueOf(itemId),bizDate, param);
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			return scmMaterialPriceList.get(0);
		}
		return null;
	}
	
	public static List<ScmMaterialPrice> getMaterialSalePriceList(String invOrgUnitNo,String itemIds,Date bizDate,Param param){
		ScmInvPriceQuery scmInvPriceQuery = new ScmInvPriceQuery();
		scmInvPriceQuery.setItemIds(itemIds);
		scmInvPriceQuery.setInvOrgUnitNo(invOrgUnitNo);
		scmInvPriceQuery.setBizDate(bizDate);
		return scmMaterialPriceBiz.getMaterialSalePrice(scmInvPriceQuery, param);
	}
	
	public static ScmMaterialPrePrice getPreMaterialPrice(String purOrgUnitNo,long vendorId, String itemIds,long unitId,Date bizDate,String finOrgUnitNo,String pmId,Param param){
		List<ScmMaterialPrePrice> scmMaterialPriceList = ScmMaterialUtil.getPreMaterialPriceList(purOrgUnitNo,vendorId,itemIds,unitId,bizDate,finOrgUnitNo,pmId, param);
		if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()){
			return scmMaterialPriceList.get(0);
		}
		return null;
	}

	public static List<ScmMaterialPrePrice> getPreMaterialPriceList(String purOrgUnitNo,long vendorId, String itemIds,long unitId,Date bizDate,String finOrgUnitNo, String pmId, Param param){
		ScmPurPriceQuery scmPurPriceQuery = new ScmPurPriceQuery();
		scmPurPriceQuery.setVendorId(vendorId);
		scmPurPriceQuery.setItemIds(itemIds);
		scmPurPriceQuery.setPurOrgUnitNo(purOrgUnitNo);
		scmPurPriceQuery.setBizDate(bizDate);
		scmPurPriceQuery.setFinOrgUnitNo(finOrgUnitNo);
		scmPurPriceQuery.setPmId(pmId);
		return scmMaterialPriceBiz.getPreMaterialPrice(scmPurPriceQuery, param);
	}
	
	public static String getNotRawMaterail(String finOrgUnitNo, String itemIds, Param param){
		String value = sysParamBiz.getValue(finOrgUnitNo, "SCM_InvAssistTask", "N", param);
		if(StringUtils.equals(value, "N")) return "";
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmMaterial2.FN_ID, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmMaterial2.class)+"."+ScmMaterial2.FN_ID,QueryParam.QUERY_IN,itemIds));
		List<String> arglist = new ArrayList<>();
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		arglist.add("orgUnitNo="+ finOrgUnitNo);
		List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, arglist, "findByFinAllPage", param);
		if(scmMaterialList!=null && !scmMaterialList.isEmpty()) {
			StringBuffer itemNames = new StringBuffer("");
			for(ScmMaterial2 scmMaterial2 : scmMaterialList) {
				if(!StringUtils.equals(scmMaterial2.getType(), "3")) {
					if(StringUtils.isNotBlank(itemNames.toString()))
						itemNames.append(",");
					itemNames.append(scmMaterial2.getItemName());
				}
			}
			if(StringUtils.isNotBlank(itemNames.toString()))
				return itemNames.toString();
		}
		return "";
	}
}
