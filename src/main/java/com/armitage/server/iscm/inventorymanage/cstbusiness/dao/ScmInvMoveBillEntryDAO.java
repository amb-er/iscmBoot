package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;

public interface ScmInvMoveBillEntryDAO extends BasicDAO<ScmInvMoveBillEntry> {
    public List<ScmInvMoveBillEntry2> selectByWtId(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveBillEntry2> selectCancelEffectRow(HashMap<String, Object> map) throws DAOException;
}
