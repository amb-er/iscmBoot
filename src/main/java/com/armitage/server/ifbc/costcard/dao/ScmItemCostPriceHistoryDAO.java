package com.armitage.server.ifbc.costcard.dao;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceHistory;

public interface ScmItemCostPriceHistoryDAO extends BasicDAO<ScmItemCostPriceHistory> {
	public void batchAddItemPriceHistory(HashMap<String, Object> map) throws DAOException;
}

