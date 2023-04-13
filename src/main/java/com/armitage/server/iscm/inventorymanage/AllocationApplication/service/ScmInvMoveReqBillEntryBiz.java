package com.armitage.server.iscm.inventorymanage.AllocationApplication.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBillEntry2;

public interface ScmInvMoveReqBillEntryBiz extends BaseBiz<ScmInvMoveReqBillEntry2>{
	/**
	 * 根据reqId查询
	 * @param reqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveReqBillEntry2> selectByReqId(long reqId, Param param) throws AppException;
	
	/**
	 * 根据reqId删除
	 * @param reqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByReqId(long reqId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param reqId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByReqId(long reqId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	public List<ScmInvMoveReqBillEntry2> findGrantPage(Page page, Param param)throws AppException;
	
	public List<ScmInvMoveReqBillEntry2> updateStatus(List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList, Param param) throws AppException;
	
	/**
	 * 调拨申请推单
	 * @param scmInvMoveReqBillEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveReqBillEntry2> pushBill(List<ScmInvMoveReqBillEntry2> scmInvMoveReqBillEntryList, Param param) throws AppException;
	
}
