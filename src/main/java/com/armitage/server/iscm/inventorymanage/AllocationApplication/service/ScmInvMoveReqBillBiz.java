package com.armitage.server.iscm.inventorymanage.AllocationApplication.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;

public interface ScmInvMoveReqBillBiz extends BaseBiz<ScmInvMoveReqBill> {
	public ScmInvMoveReqBill submit(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	public ScmInvMoveReqBill undoSubmit(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	public ScmInvMoveReqBill release(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	public ScmInvMoveReqBill undoRelease(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	public ScmInvMoveReqBill finish(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	public ScmInvMoveReqBill undoFinish(ScmInvMoveReqBill scmInvMoveReqBill, Param param) throws AppException;
	
	/**
	 * 根据reqId查询
	 * @param reqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveReqBill> selectByReqId(long reqId, Param param) throws AppException;
	public ScmInvMoveReqBill updatePrtCount(ScmInvMoveReqBill ScmInvMoveReqBill, Param param) throws AppException;
}

