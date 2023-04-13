package com.armitage.server.iscm.inventorymanage.inventorydata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvSupplierLot;

public interface ScmInvSupplierLotBiz extends BaseBiz<ScmInvSupplierLot> {

	public void addByOtherInWarehsBill(long wrId, Param param) throws AppException;

	public void delByOtherInWarehsBill(long wrId, Param param) throws AppException;
	
}
