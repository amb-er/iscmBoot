package com.armitage.server.iscm.inventorymanage.inventorydata.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;

public interface ScmInvBalDAO extends BasicDAO<ScmInvBal> {
	public void addByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelOtherInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void addByOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException;
	public void addByOtherInWarehsBillForInvDataSync(HashMap<String, Object> map) throws DAOException;
	public void addByOtherInWarehsBillForCancel(HashMap<String, Object> map) throws DAOException;
	public void updateByOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException;
	public void updateByOtherInWarehsBillForDataSnyc(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelOtherInWarehsBillForInvProfit(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelOtherInWarehsBillForDataSnyc(HashMap<String, Object> map) throws DAOException;
	public void addByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelPurInWarehsBill(HashMap<String, Object> map) throws DAOException;
	public void addByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	public void updateByPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelPurInWarehsBillOut(HashMap<String, Object> map) throws DAOException;
	
	public void updateByMoveBillOutSub(HashMap<String, Object> map) throws DAOException;
	public void updateByMoveBillInPlus(HashMap<String, Object> map) throws DAOException;
	public void updateByMoveBillInSub(HashMap<String, Object> map) throws DAOException;
	public void updateByMoveBillOutPlus(HashMap<String, Object> map) throws DAOException;
	public void addByMoveBillIn(HashMap<String, Object> map) throws DAOException;
	public void addByPostMoveBillForOut(HashMap<String, Object> map) throws DAOException;
	public void addByMoveBillOut(HashMap<String, Object> map) throws DAOException;
	public void addByCancelMoveBillForIn(HashMap<String, Object> map) throws DAOException;

    public void updateBySaleIssuePostPlus(HashMap<String, Object> map);
    public void updateBySaleIssueUnPost(HashMap<String, Object> map);
    public void addBySaleIssuePost(HashMap<String, Object> map);
    public void updateBySaleIssuePostSub(HashMap<String, Object> map);

	
	//其他出库
	public void updateByOtherIssueOutSub(HashMap<String, Object> map) throws AppException;
	public void updateByOtherIssueInSub(HashMap<String, Object> map) throws AppException;
	public void addByOtherIssueInForCancelDataSnyc(HashMap<String, Object> map) throws AppException;
	public void addByOtherIssueOutForDataSnyc(HashMap<String, Object> map) throws AppException;
	
	public void addByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
	public void addByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException;
	public void addByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException;
	public void addByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void addByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillOut(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillOutOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillReturn(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillReturnOrgunitNo(HashMap<String, Object> map) throws DAOException;
    
    public void updateByMaterialReqBillIn(HashMap<String, Object> map) throws DAOException;
    public int updateByMaterialReqBillInOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBillOrgunitNo(HashMap<String, Object> map) throws DAOException;
    public void updateByMaterialReqBill(HashMap<String, Object> map) throws DAOException;
    
    //期初结存
    public void updateByInitBill(HashMap<String, Object> map) throws DAOException;
    public void addByInitBill(HashMap<String, Object> map) throws DAOException;
    public void updateByCancelInitBill(HashMap<String, Object> map) throws DAOException;

    
    public void updateByMoveIssuePost(HashMap<String, Object> map) throws DAOException;
    
    //部门期初结存
    public void updateByCstInitBill(HashMap<String, Object> map) throws DAOException;
    public void addByCstInitBill(HashMap<String, Object> map) throws DAOException;
    public void updateByCancelCstInitBill(HashMap<String, Object> map) throws DAOException;
    
    //成本中心盘存
    public void addByCostCenter(HashMap<String, Object> map) throws DAOException;
    public void updateByCostCenter(HashMap<String, Object> map) throws DAOException;

    public void updateByMoveIssueUnPost(HashMap<String, Object> map) throws DAOException;
    
    //调拨入库
    public void updateByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    public void addByMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    public void updateByCancelMoveInWarehsBill(HashMap<String, Object> map) throws DAOException;
    
    //成本中心耗用
    public void updateByCostConsume(HashMap<String, Object> map) throws DAOException;
    public void updateByCancelCostConsume(HashMap<String, Object> map) throws DAOException;
    public void addByCostConsumPost(HashMap<String, Object> map) throws DAOException;
    public void addByCostConsumCancelPost(HashMap<String, Object> map) throws DAOException;
    
    
    //月末处理
	public void calcEndBal(HashMap<String, Object> map) throws DAOException;
	public void buildNextPeriodStartBal(HashMap<String, Object> map) throws DAOException;
	public void clearStartBal(HashMap<String, Object> map) throws DAOException;
	public void delDeplete(HashMap<String, Object> map) throws DAOException;
	public void updateByDepositInReturn(HashMap<String, Object> map) throws DAOException;
	public void updateByCancelDepositInReturn(HashMap<String, Object> map) throws DAOException;
	
	//成本中心报损
    public void updateByCstFrmLoss(HashMap<String, Object> map) throws DAOException;
    public void updateByCancelCstFrmLoss(HashMap<String, Object> map) throws DAOException;
	
	
	
    
}

