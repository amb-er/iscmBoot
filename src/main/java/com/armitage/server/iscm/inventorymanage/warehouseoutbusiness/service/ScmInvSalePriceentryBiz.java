package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;


import java.util.HashMap;

import java.util.Date;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePrice2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry;


import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry2;

public interface ScmInvSalePriceentryBiz extends BaseBiz<ScmInvSalePriceentry2>{
	/**
	 * 根据pqId查询
	 * @param pqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvSalePriceentry2> selectByPmId(long id, Param param) throws AppException;
	
	/**
	 * 根据pqId删除
	 * @param pqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPmId(long id, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param pmId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPmId(long pmId, String status, String checker, Date checkDate, Param param) throws AppException;
	
}
