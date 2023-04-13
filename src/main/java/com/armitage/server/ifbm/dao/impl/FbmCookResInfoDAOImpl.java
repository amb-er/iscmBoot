package com.armitage.server.ifbm.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.dao.FbmCookResInfoDAO;
import com.armitage.server.ifbm.model.FbmCookResInfo;
import com.armitage.server.ifbm.model.FbmCookResInfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmCookResInfoDAO")
public class FbmCookResInfoDAOImpl extends BasicDAOImpl<FbmCookResInfo> implements FbmCookResInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIfbm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<FbmCookResInfo2> selectCookSalePrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCookSalePrice", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbm.dao.impl.FbmCookResInfoDAOImpl.error.selectCookSalePrice", e);
		}
	}

}
