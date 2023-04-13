package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMaterialRequestBillEntryDAO")
public class ScmInvMaterialRequestBillEntryDAOImpl extends BasicDAOImpl<ScmInvMaterialRequestBillEntry> implements ScmInvMaterialRequestBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public List<ScmInvMaterialRequestBillEntry2> selectByReqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByReqId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAOImpl.error.selectByReqId", e);
		}
	}

	@Override
	public void deleteByReqId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByReqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAOImpl.error.deleteByReqId", e);
		}
	}

	@Override
	public void updateRowStatusByReqId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByReqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAOImpl.error.updateRowStatusByPrId", e);
		}
	}

	@Override
	public void updateRowStatusByLineId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByLineId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialRequestBillEntryDAOImpl.error.updateRowStatusByLineId", e);
		}
	}

}

