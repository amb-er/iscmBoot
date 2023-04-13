package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmDishProfitRate;

public interface ScmDishProfitRateDAO extends BasicDAO<ScmDishProfitRate> {
	public ScmDishProfitRate selectProfitRateByCostCard(HashMap<String, Object> map) throws DAOException;
}
