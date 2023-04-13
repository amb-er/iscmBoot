package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;

public interface ScmCookCostCardDAO extends BasicDAO<ScmCookCostCard> {
	public List<ScmCookCostCard2> selectReplaceScmCookCostCard(HashMap<String, Object> map) throws DAOException;
	public List<ScmCookCostCard2> selectByCookIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmCookCostCard2> findRepeat(HashMap<String, Object> map) throws DAOException;
}