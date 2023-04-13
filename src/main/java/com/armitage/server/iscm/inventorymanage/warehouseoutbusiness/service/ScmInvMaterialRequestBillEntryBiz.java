package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;

public interface ScmInvMaterialRequestBillEntryBiz extends BaseBiz<ScmInvMaterialRequestBillEntry2> {
	/**
	 * 根据reqId查询
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialRequestBillEntry2> selectByReqId(long reqId, Param param) throws AppException;
	
	/**
	 * 根据reqId删除
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByReqId(long reqId, Param param) throws AppException;

	public void writeBackByReqOut(ScmInvMaterialReqBillEntry2 oldEntity, ScmInvMaterialReqBillEntry2 newEntity, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param reqId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @throws AppException
	 */
	public void updateRowStatusByReqId(long reqId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	/**
	 * 跟进领料申请Id，行号更新明细行状态
	 * @param reqId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param lineId
	 * @param param
	 * @throws AppException
	 */
	public void updateRowStatusByLineId(long reqId, String status, String checker, Date checkDate, int lineId, Param param) throws AppException;
	public void updateBillStatusByEntry(ScmInvMaterialRequestBillEntry2 scmInvMaterialRequestBillEntry, Param param) throws AppException;

	/**
	 * 下达更新行状态
	 * @param scmInvMaterialRequestBill
	 * @param param
	 */
	public void doRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException;
	/**
	 * 取消下达更新行状态
	 * @param scmInvMaterialRequestBill
	 * @param param
	 * @throws AppException
	 */
	public void doUndoRelease(ScmInvMaterialRequestBill2 scmInvMaterialRequestBill, Param param) throws AppException;
}
