package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;

public interface ScmInvCountingCostCenterDAO extends BasicDAO<ScmInvCountingCostCenter> {
	public void deleteNotExist(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingCostCenter2> findDifference(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvCountingCostCenter2> selectByTaskNo(HashMap<String, Object> map) throws DAOException;
	public ScmInvCountingCostCenter2 selectByTaskNoAndUseOrgUnitNo(HashMap<String, Object> map) throws DAOException;
}
