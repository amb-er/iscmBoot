package com.armitage.server.ifbm.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.dao.FbmSellDetailDAO;
import com.armitage.server.ifbm.model.FbmSellCookDetail;
import com.armitage.server.ifbm.model.FbmSellDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmSellDetailDAO")
public class FbmSellDetailDAOImpl extends BasicDAOImpl<FbmSellDetail> implements FbmSellDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIfbm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<FbmSellDetail> selectDishSellDetail(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectDishSellDetail",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmSellDetailDAOImpl.error.selectDishSellDetail", e);
		}
	}

	@Override
	public List<FbmSellCookDetail> selectCookSellDetail(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCookSellDetail",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmSellDetailDAOImpl.error.selectCookSellDetail", e);
		}
	}

}

