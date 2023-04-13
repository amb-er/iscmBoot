package com.armitage.server.iscm.inventorymanage.countbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;

public interface ScmInvCountingTableDAO extends BasicDAO<ScmInvCountingTable> {
	public void deleteNotExist(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTable2> findDifference(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTable2> selectByTaskNo(HashMap<String, Object> map) throws DAOException;
	public ScmInvCountingTable2 selectByTaskNoAndWhNo(HashMap<String, Object> map) throws DAOException;
}

