package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;

public interface ScmInvMaterialReqBillDAO extends BasicDAO<ScmInvMaterialReqBill> {
	public ScmInvMaterialReqBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMaterialReqBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvMaterialReqBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMaterialReqBill2> checkWareHouseFree(long otId) throws DAOException;

    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMaterialReqBill2> inventoryWareHouseFree(long otId) throws DAOException;

    public int inventoryMaterialFree(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
    public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map);

	public List<ScmInvMaterialDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;
	
}

