package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;

public interface ScmCstInitBillEntryDAO extends BasicDAO<ScmCstInitBillEntry> {
    public List<ScmCstInitBillEntry2> selectByInitId(HashMap<String, Object> map) throws DAOException;
    public void deleteByInitId(HashMap<String, Object> map) throws DAOException;
	public List<ScmCstInitBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmCstInitBillEntry2> selectCancelPostEffectRow(HashMap<String, Object> map) throws DAOException;

}
