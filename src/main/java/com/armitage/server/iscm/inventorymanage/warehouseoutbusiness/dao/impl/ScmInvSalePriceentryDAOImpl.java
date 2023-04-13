package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceentryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSalePriceentryDAO")
public class ScmInvSalePriceentryDAOImpl extends BasicDAOImpl<ScmInvSalePriceentry> implements ScmInvSalePriceentryDAO{

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByPmId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceentryDaoImpl.error.deleteByPmId", e);
		}
	}

	@Override
	public List<ScmInvSalePriceentry2> selectByPmId(HashMap<String, Object> map) throws DAOException {
		// TODO Auto-generated method stub
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPmId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceentryDaoImpl.error.selectByPmId", e);
		}
	}

	@Override
	public void updateRowStatusByPmId(HashMap<String, Object> map) throws DAOException {
		// TODO Auto-generated method stub
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPmId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSalePriceentryDaoImpl.error.updateRowStatusByPmId", e);
		}
	}
}
