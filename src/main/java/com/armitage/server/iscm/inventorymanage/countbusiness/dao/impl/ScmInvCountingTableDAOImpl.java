package com.armitage.server.iscm.inventorymanage.countbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingTableDAO")
public class ScmInvCountingTableDAOImpl extends BasicDAOImpl<ScmInvCountingTable> implements ScmInvCountingTableDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteNotExist(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteNotExist", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAOImpl.error.deleteNotExist", e);
		}
	}

	@Override
	public List<ScmInvCountingTable2> findDifference(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findDifference",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAOImpl.error.findDifference", e);
		}
	}

	@Override
	public List<ScmInvCountingTable2> selectByTaskNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAOImpl.error.selectByTaskNo", e);
		}
	}

	@Override
	public ScmInvCountingTable2 selectByTaskNoAndWhNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskNoAndWhNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableDAOImpl.error.selectByTaskNoAndWhNo", e);
		}
	}

}

