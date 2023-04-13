package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;

public interface ScmInvPurInWarehsBillEntryBiz extends BaseBiz<ScmInvPurInWarehsBillEntry2> {
	
	/**
	 * 根据wrId查询
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvPurInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException;
	
	/**
	 * 根据wrId删除
	 * @param wrId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByWrId(long wrId, Param param) throws AppException;
	
	/**
	 * 获取引入数据
	 * @param scmInvPurInWarehsBillEntryAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	//public CommonBean getDataForLeadInto(ScmInvPurInWarehsBillEntryAdvQuery scmInvPurInWarehsBillEntryAdvQuery, Param param) throws AppException;
	
	public List<ScmInvPurInWarehsBillEntry2> checkStock(long wrId, Param param) throws AppException;

	public List<ScmInvPurInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException;

	public List<ScmInvPurInWarehsBillEntry2> selectCancelOutEffectRow(long wrId, Param param) throws AppException;
	
	public ScmInvPurInWarehsBillEntry2 updateBuildAPStatus(ScmInvPurInWarehsBillEntry2 entity, Param param) throws AppException;
	
	//查找對應物資最近入庫價格
	public BigDecimal getInvPrice(String orgUnitNo, String itemNo,Param param) throws AppException;

	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(String invOrgUnitNo, String itemIds, Param param) throws AppException;
}

