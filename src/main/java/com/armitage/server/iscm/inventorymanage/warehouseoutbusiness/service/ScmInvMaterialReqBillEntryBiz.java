package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;

public interface ScmInvMaterialReqBillEntryBiz extends BaseBiz<ScmInvMaterialReqBillEntry2> {
	/**
	 * 根据otId查询
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialReqBillEntry2> selectByOtId(long otId, Param param) throws AppException;
	
	public List<ScmInvMaterialReqBillEntry2> checkStockByOutWareHouse(long otId, Param param) throws AppException;
	public List<ScmInvMaterialReqBillEntry2> checkStockByReturnWareHouse(long otId, Param param) throws AppException;
	
	/**获取出库过账将会影响的行
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMaterialReqBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException;

	public List<ScmInvMaterialReqBillEntry2> selectReturnEffectRow(long otId, Param param) throws AppException;

	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectDeptRow(long otId, Param param) throws AppException;

	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectWareHsRow(long otId, Param param) throws AppException;

	public void deleteByOtId(long otId, Param param) throws AppException;
}


