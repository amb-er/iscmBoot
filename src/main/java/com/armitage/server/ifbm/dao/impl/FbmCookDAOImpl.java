package com.armitage.server.ifbm.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.dao.FbmCookDAO;
import com.armitage.server.ifbm.model.FbmCook;
import com.armitage.server.ifbm.model.FbmCook2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmCookDAO")
public class FbmCookDAOImpl extends BasicDAOImpl<FbmCook> implements FbmCookDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIfbm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public List<FbmCook2> selectByDishId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByDishId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmCookDAOImpl.error.selectByDishId", e);
		}
	}

}

