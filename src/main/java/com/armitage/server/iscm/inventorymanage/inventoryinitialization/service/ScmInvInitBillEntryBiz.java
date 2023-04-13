package com.armitage.server.iscm.inventorymanage.inventoryinitialization.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry2;

public interface ScmInvInitBillEntryBiz extends BaseBiz<ScmInvInitBillEntry2> {
	
	/**
	 * 根据initId查询
	 * @param initId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvInitBillEntry2> selectByInitId(long initId, Param param) throws AppException;
	
	/**
	 * 根据initId删除
	 * @param initId
	 * @param param
	 * @throws AppException
	 */
	public void deleteByInitId(long initId, Param param) throws AppException;

	public List<ScmInvInitBillEntry2> selectCancelEffectRow(long initId, Param param) throws AppException;
	
}
