package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;

public interface ScmInvCostConsumeEntryDAO extends BasicDAO<ScmInvCostConsumeEntry> {
    public List<ScmInvCostConsumeEntry2> selectByDcId(HashMap<String, Object> map) throws DAOException;
    public void deleteByDcId(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvCostConsumeEntry2> checkStockByReturnWareHouse(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCostConsumeEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;

}
