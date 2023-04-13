package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvPurInWarehsBillEntryDAO")
public class ScmInvPurInWarehsBillEntryDAOImpl extends BasicDAOImpl<ScmInvPurInWarehsBillEntry> implements ScmInvPurInWarehsBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWrId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.selectByWrId", e);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> checkStock(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkStock",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.checkStock", e);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.selectOutEffectRow", e);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> selectCancelOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCancelOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.selectCancelOutEffectRow", e);
		}
	}

	@Override
	public int updateBuildAPStatus(
			ScmInvPurInWarehsBillEntry2 entity)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateBuildAPStatus", entity);
		} catch(Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.updateBuildAPStatus", e);
		}
	}
	
	@Override
	public ScmInvPurInWarehsBillEntry getInvPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getInvPrice", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.getInvPrice", e);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getInvPriceList",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvPurInWarehsBillEntryDAOImpl.error.getInvPriceList", e);
		}
	}
}

