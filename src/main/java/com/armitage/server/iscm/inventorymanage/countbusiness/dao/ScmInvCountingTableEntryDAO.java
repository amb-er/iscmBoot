package com.armitage.server.iscm.inventorymanage.countbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;

public interface ScmInvCountingTableEntryDAO extends BasicDAO<ScmInvCountingTableEntry> {
	public List<ScmInvCountingTableEntry2> selectByTableId(HashMap<String, Object> map) throws DAOException;
	public void deleteByTableId(HashMap<String, Object> map) throws DAOException;
	public void addByItemIdList(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTableEntry2> selectByTaskId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(HashMap<String, Object> map) throws DAOException;
	public int checkAccount(long taskId) throws DAOException;
	public List<ScmInvCountingTableEntry2> selectForOtherInWh(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTableEntry2> selectForOtherOutWh(HashMap<String, Object> map) throws DAOException;
	public void updateLot(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingTableEntry2> findAccount(HashMap<String, Object> map) throws DAOException;
	public void refreshAccount(HashMap<String, Object> map) throws DAOException;
	public void addAccount(HashMap<String, Object> map) throws DAOException;
}

