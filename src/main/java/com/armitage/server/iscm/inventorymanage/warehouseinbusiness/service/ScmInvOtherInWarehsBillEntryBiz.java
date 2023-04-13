package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBillEntry2;

public interface ScmInvOtherInWarehsBillEntryBiz extends BaseBiz<ScmInvOtherInWarehsBillEntry2> {
	/**
	 * 根据wrId查询
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvOtherInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException;
	
	/**
	 * 根据wrId删除
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByWrId(long wrId, Param param) throws AppException;

	public List<ScmInvOtherInWarehsBillEntry2> selectInvQty(long wrId, Param param) throws AppException;

	public List<ScmInvOtherInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException;

	public List<ScmInvOtherInWarehsBillEntry2> checkStock(long wrId, Param param) throws AppException;

	public List<ScmInvOtherInWarehsBillEntry2> selectReturnEffectRow(long wrId, Param param) throws AppException;
}

