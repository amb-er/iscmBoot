package com.armitage.server.iscm.inventorymanage.inventorydata.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvSupplierLotDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvSupplierLot;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvSupplierLotBiz;
import org.springframework.stereotype.Service;

@Service("scmInvSupplierLotBiz")
public class ScmInvSupplierLotBizImpl extends BaseBizImpl<ScmInvSupplierLot> implements ScmInvSupplierLotBiz {

	@Override
	public void addByOtherInWarehsBill(long wrId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wrId", wrId);
		((ScmInvSupplierLotDAO)dao).addByOtherInWarehsBill(map);
	}

	@Override
	public void delByOtherInWarehsBill(long wrId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wrId", wrId);
		((ScmInvSupplierLotDAO)dao).delByOtherInWarehsBill(map);
	}

}
