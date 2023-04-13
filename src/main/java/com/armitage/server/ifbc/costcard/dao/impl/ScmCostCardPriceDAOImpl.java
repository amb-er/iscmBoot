package com.armitage.server.ifbc.costcard.dao.impl;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostCardPriceDAO")
public class ScmCostCardPriceDAOImpl extends BasicDAOImpl<ScmCostCardPrice> implements ScmCostCardPriceDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmCostCardPrice selectByScmCostCardPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByScmCostCardPrice", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCostCardPriceDAOImpl.error.selectByScmCostCardPrice", e);
		}
	}
	
	@Override
	public int batchAddCostPrice(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".batchAddCostPrice",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmCostCardPriceDAOImpl.error.batchAddCostPrice", e);
		}
	}

	@Override
	public int batchAddSalePrice(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".batchAddSalePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmCostCardPriceDAOImpl.error.batchAddSalePrice");
		}
	}

	@Override
	public int addPeriodSalePrice(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".addPeriodSalePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmCostCardPriceDAOImpl.error.addPeriodSalePrice");
		}
	}

}

