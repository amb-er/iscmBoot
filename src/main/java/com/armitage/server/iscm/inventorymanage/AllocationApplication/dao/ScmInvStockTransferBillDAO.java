package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;

public interface ScmInvStockTransferBillDAO extends BasicDAO<ScmInvStockTransferBill> {
	public ScmInvStockTransferBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	
}
