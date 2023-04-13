package com.armitage.server.iscm.inventorymanage.inventorydata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvCost;

public interface ScmInvCostDAO extends BasicDAO<ScmInvCost> {
	public void addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	
    public void updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException;
    public void updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException;
    public void addByMoveBillIn(HashMap<String, Object> map) throws DAOException;
    public void addByMoveBillOut(HashMap<String, Object> map) throws DAOException;
    public void updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException;
    public void updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException;
    
    public void updateBySaleIssuePostSub(HashMap<String, Object> map) throws DAOException;
    public void updateBySaleIssueUnPost(HashMap<String, Object> map) throws DAOException;
    public void addBySaleIssuePost(HashMap<String, Object> map) throws DAOException;
    public void updateBySaleIssuePostPlus(HashMap<String, Object> map) throws DAOException;

    //其他出库
    public void updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException;
    public void updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException;
    
    public void addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException;
    //调拨入库
	public void updateByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void addByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
	//调拨出库
	public void updateByMoveIssueUnPost(HashMap<String, Object> map) throws DAOException;
	public void updateByMoveIssuePost(HashMap<String, Object> map) throws DAOException;
	public void updateByCostCenter(HashMap<String, Object> map) throws DAOException;
	public void addByCostCenter(HashMap<String, Object> map) throws DAOException;
	public void updateByDepositInReturn(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException;

}

