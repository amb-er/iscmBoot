package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvSupplierLotDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvSupplierLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSupplierLotDAO")
public class ScmInvSupplierLotDAOImpl extends BasicDAOImpl<ScmInvSupplierLot> implements ScmInvSupplierLotDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public void addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByOtherInWarehsBill", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl.error.addByOtherInWarehsBill", e);
		}
	}

	@Override
	public void delByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".delByOtherInWarehsBill", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl.error.delByOtherInWarehsBill", e);
		}
	}

}

