
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurReturnsDAO")
public class ScmPurReturnsDAOImpl extends BasicDAOImpl<ScmPurReturns> implements ScmPurReturnsDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	
	@Override
	public ScmPurReturns selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReturnsDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmPurReturns2> selectByPurInwareHouse(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurInwareHouse", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReturnsDAOImpl.error.selectByPurInwareHouse");
		}
	}

	@Override
	public List<ScmPurReturns2> selectByPurInwareHouseReturn(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurInwareHouseReturn", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReturnsDAOImpl.error.selectByPurInwareHouseReturn");
		}
	}
}
