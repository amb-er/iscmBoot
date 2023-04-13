package com.armitage.server.ifbc.costcard.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice2;

public interface ScmItemCostPriceDAO extends BasicDAO<ScmItemCostPrice> {
	public int batchAddItemPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmItemCostPrice2> selectItemCost(HashMap<String, Object> map) throws DAOException;

}

