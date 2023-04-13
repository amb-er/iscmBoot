package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;

public interface ScmCostCardDetailDAO extends BasicDAO<ScmCostCardDetail> {
	public List<ScmCostCardDetail2> selectByCardId(HashMap<String, Object> map) throws DAOException;
	public int replaceItemByCardIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmCostCardDetail2> checkItemCostPrice(HashMap<String, Object> map) throws DAOException;
}
