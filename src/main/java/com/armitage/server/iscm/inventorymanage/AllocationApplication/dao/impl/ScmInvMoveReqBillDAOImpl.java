package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.ScmInvMoveReqBillDAO;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveReqBillDAO")
public class ScmInvMoveReqBillDAOImpl extends BasicDAOImpl<ScmInvMoveReqBill> implements ScmInvMoveReqBillDAO{

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	
	@Override
	public ScmInvMoveReqBill selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl.ScmInvMoveReqBillDaoImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmInvMoveReqBill> selectByReqId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByReqId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.AllocationApplication.dao.impl.ScmInvMoveReqBillDaoImpl.error.selectByReqId", e);
		}
	}
}
