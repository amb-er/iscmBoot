package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;

public interface ScmInvPurInWarehsBillDAO extends BasicDAO<ScmInvPurInWarehsBill> {
	public ScmInvPurInWarehsBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	public int checkfollowupbill(long wrId) throws DAOException;
	public int checkStock(long wrId) throws DAOException;
	public List<ScmInvPurInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvPurInWarehsBill2> selectByPurReceive(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> selectByPurReturns(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException;
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> selectByPurOrder(HashMap<String, Object> map) throws DAOException;
	public BigDecimal getTotalPurInWarehsQty(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmInvPurInWarehsBill2> checkWareHouseFree(long wrId) throws DAOException;
	public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> checkCostCenterFree(long wrId) throws DAOException;
	public int costCenterMaterialFree(HashMap<String, Object> map) throws DAOException;
	public ScmInvPurInWarehsBill2 selectPoNoAndPvNoById(HashMap<String, Object> map) throws DAOException;
	public void updatePushed(HashMap<String, Object> map) throws DAOException;
	public void updateConfirmStatus(HashMap<String, Object> map) throws DAOException;
	public void updateVersion(HashMap<String, Object> map) throws DAOException;
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
	
	public List<Map<String,Object>> countCostUnPostBill(HashMap<String, Object> map);
	public ScmInvPurInWarehsBill2 selectByWrNo(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBill2> querySupSupplyOfMaterialDetails(HashMap<String, Object> map, Param param) throws DAOException;
	public List getTotalPurInWarehsQtyByItems(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurBillDrillResult> selectDrillBillsISCM(List<String> list) throws DAOException;
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;
}

