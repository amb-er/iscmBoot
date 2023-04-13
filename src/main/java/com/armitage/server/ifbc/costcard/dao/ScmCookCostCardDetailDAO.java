package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetail2;

public interface ScmCookCostCardDetailDAO extends BasicDAO<ScmCookCostCardDetail> {
	public List<ScmCookCostCardDetail2> selectByCardId(HashMap<String, Object> map) throws DAOException;
	public int replaceItemByCardIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmCookCostCardDetail2> checkItemCostPrice(HashMap<String, Object> map) throws DAOException;
}