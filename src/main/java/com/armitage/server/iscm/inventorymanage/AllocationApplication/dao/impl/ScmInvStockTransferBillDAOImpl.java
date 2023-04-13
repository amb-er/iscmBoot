package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvStockTransferBillDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvStockTransferBillDAO")
public class ScmInvStockTransferBillDAOImpl extends BasicDAOImpl<ScmInvStockTransferBill> implements ScmInvStockTransferBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvStockTransferBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl.ScmInvStockTransferBillDAOImpl.error.selectMaxIdByDate");
		}
	}
}