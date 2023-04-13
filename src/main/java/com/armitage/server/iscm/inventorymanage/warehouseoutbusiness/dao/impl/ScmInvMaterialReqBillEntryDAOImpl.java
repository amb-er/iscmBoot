package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMaterialReqBillEntryDAO")
public class ScmInvMaterialReqBillEntryDAOImpl extends BasicDAOImpl<ScmInvMaterialReqBillEntry> implements ScmInvMaterialReqBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.selectByOtId", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> checkStockByOutWareHouse(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkStockByOutWareHouse",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.checkStockByOutWareHouse", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> checkStockByReturnWareHouse(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkStockByReturnWareHouse",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.checkStockByReturnWareHouse", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.selectOutEffectRow", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectReturnEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectReturnEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.selectReturnEffectRow", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectDeptRow(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCancelOutEffectDeptRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.selectCancelOutEffectDeptRow", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectWareHsRow(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCancelOutEffectWareHsRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.selectCancelOutEffectWareHsRow", e);
		}
	}

	@Override
	public List<ScmInvMaterialReqBillEntry2> deleteByOtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".deleteByOtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillEntryDAOImpl.error.deleteByOtId", e);
		}
	}
}


