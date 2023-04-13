
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;

public interface ScmPurSupplyInfoBiz extends BaseBiz<ScmPurSupplyInfo2> {
	public List<ScmPurSupplyInfo2> findVendor(ScmPurSupplyInfo2 scmPurSupplyInfo,Param param) throws AppException;

	public ScmPurSupplyInfo2 getSupplyInfoByItemAndVendor(String purOrgUnitNo,String invOrgUnitNo,long vendorId,long itemId,Date bizDate, Param param) throws AppException;

	public ScmPurSupplyInfo2 getSupplyInfoByItem(String purOrgUnitNo,String invOrgUnitNo,Long itemId,Date bizDate, Param param) throws AppException;
	
	public List<ScmPurSupplyInfo2> getSupplyInfoByItems(String purOrgUnitNo,String invOrgUnitNo,String itemIds,Date bizDate, Param param) throws AppException;

	public List<ScmPurSupplyInfo2> getSupplyInfoByItemList(String purOrgUnitNo, String invOrgUnitNo, String itemIdList,Date bizDate,Param param) throws AppException;

	public void generateBySupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;

	public List<ScmPurSupplyInfo2> getSupplyInfoByItemSAndVendorS(List<ScmPurPriceQuery> list, Param param) throws AppException;
}
