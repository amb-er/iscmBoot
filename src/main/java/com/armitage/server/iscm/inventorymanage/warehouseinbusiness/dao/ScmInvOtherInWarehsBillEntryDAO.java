package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;

public interface ScmInvOtherInWarehsBillEntryDAO extends BasicDAO<ScmInvOtherInWarehsBillEntry> {
	public List<ScmInvOtherInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException;
	public void deleteByWrId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherInWarehsBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherInWarehsBillEntry2> checkStock(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherInWarehsBillEntry2> selectReturnEffectRow(HashMap<String, Object> map) throws DAOException;
}

