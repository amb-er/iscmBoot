package com.armitage.server.iscm.inventorymanage.inventorydata.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvBal2;

public interface ScmInvBalBiz extends BaseBiz<ScmInvBal> {
	public void addByOtherInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByOtherInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByCancelOtherInWarehsBill(long wrId,Param param) throws AppException;
	public void addByOtherInWarehsBillForInvProfit(long wrId,Param param) throws AppException;
	public void addByOtherInWarehsBillForInvDataSync(long wrId, Param param) throws AppException;
	public void addByOtherInWarehsBillForCancel(long wrId,Param param) throws AppException;
	public void updateByOtherInWarehsBillForInvProfit(long wrId,Param param) throws AppException;
	public void updateByOtherInWarehsBillForDataSnyc(long wrId,Param param) throws AppException;
	public void updateByCancelOtherInWarehsBillForInvProfit(long wrId,Param param) throws AppException;
	public void updateByCancelOtherInWarehsBillForDataSnyc(long wrId,Param param) throws AppException;
	public void addByPurInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByPurInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByCancelPurInWarehsBill(long wrId,Param param) throws AppException;
	public void addByPurInWarehsBillOut(long wrId, Param param) throws AppException;
	public void updateByPurInWarehsBillOut(long wrId,Param param) throws AppException;
	public void updateByCancelPurInWarehsBillOut(long wrId,Param param) throws AppException;
	
	public void addByMoveBillIn(long wtId, Param param) throws AppException;
	public void addByPostMoveBillForOut(long wtId, Param param) throws AppException;
    public void updateByMoveBillOutSub(long wtId, Param param) throws AppException;
    public void updateByMoveBillOutPlus(long wtId, Param param) throws AppException;
    public void updateByMoveBillInSub(long wtId, Param param) throws AppException;
    public void updateByMoveBillInPlus(long wtId, Param param) throws AppException;
    public void addByMoveBillOut(long wtId, Param param) throws AppException;
    public void addByCancelMoveBillForIn(long wtId, Param param) throws AppException;
    
    public void updateBySaleIssuePostPlus(long otId, Param param);
    public void updateBySaleIssueUnPost(long otId, String flag, Param param);
    public void addBySaleIssuePost(long otId, Param param);
    public void updateBySaleIssuePostSub(long otId, Param param);
    
    //其他出库
    public void updateByOtherIssueOutSub(long otId, Param param) throws AppException;
    public void updateByOtherIssueInSub(long otId, Param param) throws AppException;
    public void addByOtherIssueInForCancelDataSnyc(long otId, Param param) throws AppException;
    public void addByOtherIssueOutForDataSnyc(long otId, Param param) throws AppException;
    
    public void addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public void addByMaterialReqBillOut(long otId, Param param) throws AppException;
    public void addByMaterialReqBillIn(long otId, Param param) throws AppException;
    public void addByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException;
    public void addByMaterialReqBillReturn(long otId, Param param) throws AppException;
    public int updateByMaterialReqBillOut(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillReturn(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException;
    
    //更新期间结存
    public void updateByMaterialReqBillIn(long otId, Param param) throws AppException;
    //更新部门期间结存
    public int updateByMaterialReqBillInOrgunitNo(long otId, Param param) throws AppException;
    //更新部门期间结存
    public void updateByMaterialReqBillOrgunitNo(long otId, Param param) throws AppException;
    //更新仓库期间结存
    public void updateByMaterialReqBill(long otId, Param param) throws AppException;
    
    //期初结存
    public void updateByInitBill(long initId,Param param) throws AppException;
    public void addByInitBill(long initId,Param param) throws AppException;
    public void updateByCancelInitBill(long initId,Param param) throws AppException;

    public void updateByMoveIssuePost(long otId, Param param) throws AppException;
    public void updateByMoveIssueUnPost(long otId, Param param) throws AppException;
    
    //部门期初结存
    public void updateByCstInitBill(long initId,Param param) throws AppException;
    public void addByCstInitBill(long initId,Param param) throws AppException;
    public void updateByCancelCstInitBill(long initId,Param param) throws AppException;
    
    //调拨入库
    public void updateByMoveInWarehsBill(long wrId, Param param) throws AppException;
    public void addByMoveInWarehsBill(long wrId, Param param) throws AppException;
    public void updateByCancelMoveInWarehsBill(long wrId, Param param) throws AppException;
    
    //成本中心盘存
    public void addByCostCenter(long taskId,String finOrgUnitNo,long periodId,int accountYear,int accountPeriod, Param param) throws AppException;
    public void updateByCostCenter(long taskId,String finOrgUnitNo,int accountYear,int accountPeriod, Param param) throws AppException;
    
    //成本中心耗用
    public void updateByCostConsume(long dcId,Param param) throws AppException;
    public void updateByCancelCostConsume(long dcId,Param param) throws AppException;
    public void addByCostConsumPost(long dcId, Param param) throws AppException;
    public void addByCostConsumCancelPost(long dcId, Param param) throws AppException;
    
    //月末处理
	public void calcEndBal(String orgUnitNo, long periodId,Param param) throws AppException;
	public void buildNextPeriodStartBal(String orgUnitNo, long periodId,long nextPeriodId,int accountYear,int accountPeriod,Param param) throws AppException;
	public void clearStartBal(String orgUnitNo, long periodId,Param param) throws AppException;
	public void delDeplete(String orgUnitNo, long periodId, Param param) throws AppException;
	public void updateByDepositInReturn(long wrId, Param param) throws AppException;
	public void updateByCancelDepositInReturn(long wrId, Param param) throws AppException;
	
	//成本中心报损
    public void updateByCstFrmLoss(long billId,Param param) throws AppException;
    public void updateByCancelCstFrmLoss(long billId,Param param) throws AppException;

	
	

    
}

