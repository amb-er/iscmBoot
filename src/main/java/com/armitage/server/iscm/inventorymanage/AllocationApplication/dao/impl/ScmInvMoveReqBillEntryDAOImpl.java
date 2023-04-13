package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.plugin.PagePlugin;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveReqBillEntryDAO")
public class ScmInvMoveReqBillEntryDAOImpl extends BasicDAOImpl<ScmInvMoveReqBillEntry> implements ScmInvMoveReqBillEntryDAO{

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvMoveReqBillEntry2> selectByReqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByReqId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDaoImpl.error.selectByReqId", e);
		}
	}

	@Override
	public void deleteByReqId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByReqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDaoImpl.error.deleteByReqId", e);
		}
	}

	@Override
	public void updateRowStatusByReqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByReqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDaoImpl.error.updateRowStatusByReqId", e);
		}
	}

	@Override
	public List<ScmInvMoveReqBillEntry2> findGrantPage(HashMap<String, Object> map) throws DAOException {
		if (map == null || !map.containsKey(PagePlugin.PAGE) || map.get(PagePlugin.PAGE) == null)
			return new ArrayList();
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findGrantPage", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillEntryDaoImpl.error.findGrantPage", e);
		}
	}
}
