package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;

public interface ScmInvMoveInWarehsBillDAO extends BasicDAO<ScmInvMoveInWarehsBill> {

    public ScmInvMoveInWarehsBill selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMoveInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMoveInWarehsBill2> checkWareHouseFree(long wrId) throws DAOException;

    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveInWarehsBill2> selectByMoveIssue(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map);


}

