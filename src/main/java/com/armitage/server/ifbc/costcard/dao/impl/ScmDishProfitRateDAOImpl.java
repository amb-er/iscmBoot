package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmDishProfitRateDAO;
import com.armitage.server.ifbc.costcard.model.ScmDishProfitRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmDishProfitRateDAO")
public class ScmDishProfitRateDAOImpl extends BasicDAOImpl<ScmDishProfitRate> implements ScmDishProfitRateDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmDishProfitRate selectProfitRateByCostCard(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectProfitRateByCostCard", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmDishProfitRateDAOImpl.error.selectProfitRateByCostCard", e);
		}
	}

}
