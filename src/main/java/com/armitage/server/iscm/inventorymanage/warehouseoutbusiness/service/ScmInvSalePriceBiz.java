package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceAdd;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;

public interface ScmInvSalePriceBiz extends BaseBiz<ScmInvSalePrice> {
	public ScmInvSalePrice submit(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public ScmInvSalePrice undoSubmit(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public ScmInvSalePrice release(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public ScmInvSalePrice undoRelease(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public ScmInvSalePrice finish(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public ScmInvSalePrice undoFinish(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
	public List<ScmInvSalePrice2> findByBizDateAndItemId(HashMap<String, Object> map, Param param) throws AppException;
	public List<ScmMaterialPrice> getPrice(String invOrgUnitNo, String itemIds,Date bizDate, Param param);

	public ScmInvSalePrice doAddInvSalePrice(ScmInvSalePriceAdd scmInvSalePriceAdd,Param param) throws AppException ;
	public ScmInvSalePrice updatePrtCount(ScmInvSalePrice scmInvSalePrice, Param param) throws AppException;
}
