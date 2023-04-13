package com.armitage.server.ifbc.costcard.dao.impl;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmItemCostPriceHistoryDAO")
public class ScmItemCostPriceHistoryDAOImpl extends BasicDAOImpl<ScmItemCostPriceHistory> implements ScmItemCostPriceHistoryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void batchAddItemPriceHistory(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".batchAddItemPriceHistory",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmItemCostPriceHistoryDAOImpl.error.batchAddItemPriceHistory", e);
		}
	}

}

