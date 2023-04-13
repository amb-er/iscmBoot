package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvOtherInWarehsBillEntryDAO")
public class ScmInvOtherInWarehsBillEntryDAOImpl extends BasicDAOImpl<ScmInvOtherInWarehsBillEntry> implements ScmInvOtherInWarehsBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWrId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.selectByWrId", e);
		}
	}

	@Override
	public void deleteByWrId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByWrId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.deleteByWrId", e);
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectInvQty",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.selectInvQty", e);
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.selectOutEffectRow", e);
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> checkStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkStock",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.checkStock", e);
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBillEntry2> selectReturnEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectReturnEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillEntryDAOImpl.error.selectReturnEffectRow", e);
		}
	}
}

