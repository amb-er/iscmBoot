package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao;

import java.util.HashMap;
import java.util.List;
import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;

public interface ScmInvStockTransferBillEntryDAO extends BasicDAO<ScmInvStockTransferBillEntry> {
	public List<ScmInvStockTransferBillEntry2> selectByWtId(HashMap<String, Object> map) throws DAOException;
	public void deleteByWtId(HashMap<String, Object> map) throws DAOException;
}
