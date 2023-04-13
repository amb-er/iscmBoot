package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurReceiveEntryDAO")
public class ScmPurReceiveEntryDAOImpl extends BasicDAOImpl<ScmPurReceiveEntry> implements ScmPurReceiveEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurReceiveEntry2> selectByPvId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPvId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAOImpl.error.selectByPvId", e);
		}
	}

	@Override
	public void updateRowStatusByPvId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPvId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAOImpl.error.updateRowStatusByPvId", e);
		}
	}

	@Override
	public List<ScmPurReceiveEntry2> selectByCkId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCkId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAOImpl.error.selectByCkId", e);
		}
	}

	@Override
	public void clearInvQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".clearInvQty", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveEntryDAOImpl.error.clearInvQty", e);
		}
	}

}

