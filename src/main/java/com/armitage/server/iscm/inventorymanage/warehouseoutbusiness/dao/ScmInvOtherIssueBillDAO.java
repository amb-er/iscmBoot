package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;

public interface ScmInvOtherIssueBillDAO extends BasicDAO<ScmInvOtherIssueBill> {
	public ScmInvOtherIssueBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvOtherIssueBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvOtherIssueBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvOtherIssueBill2> selectByPoId(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvOtherIssueBill2> checkWareHouseFree(long otId) throws DAOException;
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;
    
    public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
}
 