package com.armitage.server.iscm.inventorymanage.AllocationApplication.service;

import java.util.List;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;

public interface ScmInvStockTransferBillEntryBiz extends BaseBiz<ScmInvStockTransferBillEntry2> {
	/**
	 * 根据reqId查询
	 * @param reqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvStockTransferBillEntry2> selectByWtId(long wtId, Param param) throws AppException;
	
	/**
	 * 根据reqId删除
	 * @param reqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByWtId(long reqId, Param param) throws AppException;
	
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
}

