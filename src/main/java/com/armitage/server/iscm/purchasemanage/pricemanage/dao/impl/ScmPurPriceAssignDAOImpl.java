package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceAssignDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurPriceAssignDAO")
public class ScmPurPriceAssignDAOImpl extends BasicDAOImpl<ScmPurPriceAssign> implements ScmPurPriceAssignDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurPriceAssign2> selectByPmId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPmId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurPriceAssignDAOImpl.error.selectByPmId", e);
		}
	}

	
}
