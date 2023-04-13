package com.armitage.server.ifbc.costcard.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmItemCostPriceDAO")
public class ScmItemCostPriceDAOImpl extends BasicDAOImpl<ScmItemCostPrice> implements ScmItemCostPriceDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public int batchAddItemPrice(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".batchAddItemPrice",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmItemCostPriceDAOImpl.error.batchAddItemPrice", e);
		}
	}
	
	@Override
	public List<ScmItemCostPrice2> selectItemCost(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.selectList(simpleName + ".selectItemCost",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmItemCostPriceDAOImpl.error.selectItemCost", e);
		}
	}

}

