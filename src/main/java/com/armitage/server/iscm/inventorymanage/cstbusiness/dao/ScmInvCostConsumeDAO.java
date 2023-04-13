package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;

public interface ScmInvCostConsumeDAO extends BasicDAO<ScmInvCostConsume> {
    public ScmInvCostConsume2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvCostConsume2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvCostConsume2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvCostConsume2> checkCostCenterFree(long id) throws DAOException;
	public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvCostConsume2> selectGenerateBill(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvCostConsume2> selectGenerateBillBySourceType(HashMap<String, Object> map) throws DAOException;

	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws DAOException;

}
