package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvStockTransferBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvStockTransferBillEntryDAO")
public class ScmInvStockTransferBillEntryDAOImpl extends BasicDAOImpl<ScmInvStockTransferBillEntry> implements ScmInvStockTransferBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvStockTransferBillEntry2> selectByWtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.impl.ScmInvStockTransferBillEntryDAOImpl.error.selectByWtId", e);
		}
	}

	@Override
	public void deleteByWtId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByWtId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.AllocationApplication.dao.impl.ScmInvStockTransferBillEntryDAOImpl.error.deleteByWtId", e);
		}
		
	}
	
}
