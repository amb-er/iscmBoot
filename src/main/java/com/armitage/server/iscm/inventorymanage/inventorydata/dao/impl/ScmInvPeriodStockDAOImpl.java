package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvPeriodStockDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvPeriodStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvPeriodStockDAO")
public class ScmInvPeriodStockDAOImpl extends BasicDAOImpl<ScmInvPeriodStock> implements ScmInvPeriodStockDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void turnoffStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".turnoffStock", map);

		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvPeriodStockDAOImpl.error.turnoffStock", e);
		}
	}

	@Override
	public void turnbackStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".turnbackStock", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvPeriodStockDAOImpl.error.turnbackStock", e);
		}
	}

}
