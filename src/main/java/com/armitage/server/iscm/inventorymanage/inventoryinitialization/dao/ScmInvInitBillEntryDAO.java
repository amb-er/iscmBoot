package com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry2;

public interface ScmInvInitBillEntryDAO extends BasicDAO<ScmInvInitBillEntry>{
	public List<ScmInvInitBillEntry2> selectByInitId(HashMap<String, Object> map) throws DAOException;
	public void deleteByInitId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvInitBillEntry2> selectCancelEffectRow(HashMap<String, Object> map) throws DAOException;
}
