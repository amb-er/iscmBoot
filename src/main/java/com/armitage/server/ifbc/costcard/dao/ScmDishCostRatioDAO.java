package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio2;

public interface ScmDishCostRatioDAO extends BasicDAO<ScmDishCostRatio> {
	public List<ScmDishCostRatio2> selectByCardId(HashMap<String, Object> map) throws DAOException;
}
