package com.armitage.server.iscm.inventorymanage.inventorydata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvSupplierLot;

public interface ScmInvSupplierLotDAO extends BasicDAO<ScmInvSupplierLot> {

	public void addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;

	public void delByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	
}

