package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;

public interface ScmInvOtherInWarehsBillDAO extends BasicDAO<ScmInvOtherInWarehsBill> {
	public ScmInvOtherInWarehsBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	public int checkStock(long wrId) throws DAOException;
	public List<ScmInvOtherInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvOtherInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvOtherInWarehsBill2> checkWareHouseFree(long wrId) throws DAOException;
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
}

