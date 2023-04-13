package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;

public interface ScmInvCountingCostCenterEntryDAO extends BasicDAO<ScmInvCountingCostCenterEntry> {
	public List<ScmInvCountingCostCenterEntry2> selectByTableId(HashMap<String, Object> map) throws DAOException;
	public void deleteByTableId(HashMap<String, Object> map) throws DAOException;
	public void addByItemIdList(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingCostCenterEntry2> selectByTaskId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(HashMap<String, Object> map) throws DAOException;
	public int selectByTaskIdAndFinOrg(HashMap<String, Object> map) throws DAOException;
	public void refreshAccount(HashMap<String, Object> map) throws DAOException;
	public int checkAccount(HashMap<String, Object> map) throws DAOException;
	public void addAccount(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingCostCenterEntry2> selectByIdleCauseId(HashMap<String, Object> map) throws DAOException;
}
