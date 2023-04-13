package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;

public interface ScmInvCountingTaskDAO extends BasicDAO<ScmInvCountingTask> {
	public ScmInvCountingTask2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
    
    public List<ScmInvCountingTask2> selectByDate(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvCountingTask2> selectByOrgUnitNoAndWareHouseId(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvCountingTask2> selectByOrgUnitNoAndUseOrgUnitNo(HashMap<String, Object> map) throws DAOException;

	public int updateFinishStatus(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvCountingTask2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public ScmInvCountingTask2 queryByTaskNo(HashMap<String, Object> map) throws DAOException;
    
}
