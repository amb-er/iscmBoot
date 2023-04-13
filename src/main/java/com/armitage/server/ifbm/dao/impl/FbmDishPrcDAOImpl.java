package com.armitage.server.ifbm.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.dao.FbmDishPrcDAO;
import com.armitage.server.ifbm.model.FbmDishPrc;
import com.armitage.server.ifbm.model.FbmDishPrc2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmDishPrcDAO")
public class FbmDishPrcDAOImpl extends BasicDAOImpl<FbmDishPrc> implements FbmDishPrcDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIfbm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public FbmDishPrc2 selectByDishAndSpecId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByDishAndSpecId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmDishPrcDAOImpl.error.selectByDishAndSpecId", e);
		}
	}

	@Override
	public FbmDishPrc2 selectByDishSpecCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByDishSpecCode", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmDishPrcDAOImpl.error.selectByDishSpecCode", e);
		}
	}

	@Override
	public List<FbmDishPrc2> selectDishSalePrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectDishSalePrice", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmDishPrcDAOImpl.error.selectDishSalePrice", e);
		}
	}

}
