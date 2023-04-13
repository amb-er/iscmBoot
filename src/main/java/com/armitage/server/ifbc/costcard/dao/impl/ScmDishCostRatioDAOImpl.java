package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmDishCostRatioDAO;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmDishCostRatioDAO")
public class ScmDishCostRatioDAOImpl extends BasicDAOImpl<ScmDishCostRatio> implements ScmDishCostRatioDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmDishCostRatio2> selectByCardId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCardId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmDishCostRatioDAOImpl.error.selectByCardId");
		}
	}

}
