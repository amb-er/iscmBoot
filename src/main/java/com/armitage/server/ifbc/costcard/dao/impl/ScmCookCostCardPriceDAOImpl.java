package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardPriceDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCookCostCardPriceDAO")
public class ScmCookCostCardPriceDAOImpl extends BasicDAOImpl<ScmCookCostCardPrice> implements ScmCookCostCardPriceDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmCookCostCardPrice selectCurrPriceByCookId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectCurrPriceByCookId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardPriceDAOImpl.error.selectCurrPriceByCookId", e);
		}
	}
	
	@Override
	public int batchAddCostPrice(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".batchAddCostPrice",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmCookCostCardPriceDAOImpl.error.batchAddCostPrice", e);
		}
	}

	@Override
	public ScmCookCostCardPrice selectByScmCookCostCardPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByScmCookCostCardPrice", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCostCardPriceDAOImpl.error.selectByScmCookCostCardPrice", e);
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
			return sqlSession.insert(simpleName + ".addPeriodSalePrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmCostCardPriceDAOImpl.error.addPeriodSalePrice");
		}
	}

}
