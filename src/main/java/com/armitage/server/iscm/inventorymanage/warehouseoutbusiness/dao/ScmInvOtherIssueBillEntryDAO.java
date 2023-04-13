package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;

public interface ScmInvOtherIssueBillEntryDAO extends BasicDAO<ScmInvOtherIssueBillEntry> {
	public List<ScmInvOtherIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherIssueBillEntry2> selectMaxLineId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
}
