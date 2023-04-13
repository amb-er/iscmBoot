package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;

public interface ScmInvMoveIssueBillEntryDAO extends BasicDAO<ScmInvMoveIssueBillEntry> {
	public List<ScmInvMoveIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException;
	public void deleteByOtId(HashMap<String, Object> map) throws DAOException;
    public void deleteById(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvMoveIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMoveIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
}
