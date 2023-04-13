package com.armitage.server.iscm.inventorymanage.inventorydata.service;


import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvCost;

public interface ScmInvCostBiz extends BaseBiz<ScmInvCost> {
	//其他入库
	public void addByOtherInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByOtherInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByCancelOtherInWarehsBill(long wrId, Param param) throws AppException;
	//采购入库
	public void addByPurInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByPurInWarehsBill(long wrId,Param param) throws AppException;
	public void updateByPurInWarehsBillOut(long wrId, Param param) throws AppException;
	public void updateByCancelPurInWarehsBillOut(long wrId, Param param) throws AppException;
	public void updateByCancelPurInWarehsBill(long wrId, Param param) throws AppException;
	
    public void addByMoveBillIn(long wtId, Param param) throws AppException;
    public void updateByMoveBillOutSub(long wtId, Param param) throws AppException;
    public void updateByMoveBillOutPlus(long wtId, Param param) throws AppException;
    public void updateByMoveBillInSub(long wtId, Param param) throws AppException;
    public void updateByMoveBillInPlus(long wtId, Param param) throws AppException;
    public void addByMoveBillOut(long wtId, Param param) throws AppException;
    
    public void updateBySaleIssuePostSub(long otId, Param param) throws AppException;
    public void updateBySaleIssueUnPost(long otId, String flag, Param param);
    public void addBySaleIssuePost(long otId, Param param) throws AppException;
    public void updateBySaleIssuePostPlus(long otId, Param param) throws AppException;

    
    //其他出库
    public void updateByOtherIssueOutSub(long otId, Param param) throws AppException;
    public void updateByOtherIssueInSub(long otId, Param param) throws AppException;
    
    public void addByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillOut(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillOutOrgunitNo(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillReturn(long otId, Param param) throws AppException;
    public void updateByMaterialReqBillReturnOrgunitNo(long otId, Param param) throws AppException;
    //调拨入库
	public void updateByMoveInWarehsBill(long wrId, Param param) throws AppException;
	public void addByMoveInWarehsBill(long wrId, Param param) throws AppException;
	public void updateByCancelMoveInWarehsBill(long wrId, Param param) throws AppException;
	//调拨出库
	public void updateByMoveIssueUnPost(long otId, Param param) throws AppException;
	public void updateByMoveIssuePost(long otId, Param param) throws AppException;
	public void updateByCostCenter(long taskId, String orgUnitNo, Param param) throws AppException;
	public void addByCostCenter(long taskId, String orgUnitNo,Param param) throws AppException;
	public void updateByDepositInReturn(long wrId, Param param) throws AppException;
	public void updateByCancelDepositInReturn(long wrId, Param param) throws AppException;

}

