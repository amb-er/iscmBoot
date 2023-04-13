package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;

public interface ScmCstFrmLossEntryDAO extends BasicDAO<ScmCstFrmLossEntry> {
	public List<ScmCstFrmLossEntry2> selectByBillId(HashMap<String, Object> map) throws DAOException;
    public void deleteByBillId(HashMap<String, Object> map) throws DAOException;
    public List<ScmCstFrmLossEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
}
