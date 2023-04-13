package com.armitage.server.iscm.inventorymanage.AllocationApplication.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;

public interface ScmInvMoveReqBillEntryDAO extends BasicDAO<ScmInvMoveReqBillEntry>{
	public List<ScmInvMoveReqBillEntry2> selectByReqId(HashMap<String, Object> map) throws DAOException;
	public void deleteByReqId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByReqId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMoveReqBillEntry2> findGrantPage(HashMap<String, Object> map) throws DAOException;
}
