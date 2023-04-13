package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;

public interface ScmInvMoveReqBillDAO extends BasicDAO<ScmInvMoveReqBill>{
	public ScmInvMoveReqBill selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	/**
	 * 根据reqId查询
	 * @param map
	 * @return
	 * @throws DAOException
	 */
	public List<ScmInvMoveReqBill> selectByReqId(HashMap<String, Object> map) throws DAOException;
}
