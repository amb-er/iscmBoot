package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;

public interface ScmPurReceiveEntryDAO extends BasicDAO<ScmPurReceiveEntry> {
	public List<ScmPurReceiveEntry2> selectByPvId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPvId(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurReceiveEntry2> selectByCkId(HashMap<String, Object> map) throws DAOException;
	public void clearInvQty(HashMap<String, Object> map) throws DAOException;
}

