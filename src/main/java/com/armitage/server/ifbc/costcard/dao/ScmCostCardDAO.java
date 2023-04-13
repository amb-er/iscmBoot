package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;

public interface ScmCostCardDAO extends BasicDAO<ScmCostCard> {

	public List<ScmCostCard2> selectReplaceScmCostCard(HashMap<String, Object> map) throws DAOException;

	public List<ScmCostCard2> findRepeat(HashMap<String, Object> map) throws DAOException;
}
