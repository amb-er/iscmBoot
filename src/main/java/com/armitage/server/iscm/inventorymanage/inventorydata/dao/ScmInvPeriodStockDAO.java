package com.armitage.server.iscm.inventorymanage.inventorydata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvPeriodStock;

public interface ScmInvPeriodStockDAO extends BasicDAO<ScmInvPeriodStock> {

	public void turnoffStock(HashMap<String, Object> map) throws DAOException;

	public void turnbackStock(HashMap<String, Object> map) throws DAOException;

}
