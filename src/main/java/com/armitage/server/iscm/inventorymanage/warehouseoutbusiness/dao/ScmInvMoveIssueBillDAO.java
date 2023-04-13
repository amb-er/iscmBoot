package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBill2;

public interface ScmInvMoveIssueBillDAO extends BasicDAO<ScmInvMoveIssueBill> {
	public ScmInvMoveIssueBill selectMaxIdByDate(HashMap<String,Object> map) throws DAOException;

	public List<ScmInvMoveIssueBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveIssueBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMoveIssueBill2> checkWareHouseFree(long otId) throws DAOException;

    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveIssueBill2> selectByMoveIn(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
}
