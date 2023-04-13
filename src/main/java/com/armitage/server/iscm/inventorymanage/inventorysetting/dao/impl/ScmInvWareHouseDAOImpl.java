package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvWareHouseDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvWareHouseDAO")
public class ScmInvWareHouseDAOImpl extends BasicDAOImpl<ScmInvWareHouse> implements ScmInvWareHouseDAO {


	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvWareHouse selectMaxId() throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxId");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseDAOImpl.error.selectMaxId");
		}
	}

	@Override
	public List<ScmInvWareHouse> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseDAOImpl.error.selectByOrgUnitNo", e);
		}
	}

	@Override
	public ScmInvWareHouse selectByWhNo(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByWhNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseDAOImpl.error.selectByWhNo", e);
		}
	}

	@Override
	public List<ScmInvWareHouse> selectByWhName(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWhName", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl.ScmInvWareHouseDAOImpl.error.selectByWhName");
		}
	}
}
