package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;

public interface ScmPurReceiveEntryBiz extends BaseBiz<ScmPurReceiveEntry2> {

	/**
	 * 根据pvId查询
	 * @param pvId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceiveEntry2> selectByPvId(long pvId, Param param) throws AppException;
	
	/**
	 * 根据pvId删除
	 * @param pvId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPvId(long pvId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param pvId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPvId(long pvId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	/**
	 * 直接更新状态
	 * @param scmPurReceiveEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceiveEntry2> updateStatus(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException;

	/**
	 * 根据行状态更新单状态
	 * @param scmPurReceiveEntry
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateBillStatusByEntry(ScmPurReceiveEntry2 scmPurReceiveEntry, Param param) throws AppException;
	/**
	 * 获取引入数据
	 * @param scmPurRequireEntryAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean getDataForLeadInto(ScmPurReceiveEntryAdvQuery scmPurReceiveEntryAdvQuery, Param param) throws AppException;

	/**
	 * 根据退货单明细回写
	 * @param oldEntity
	 * @param newEntity
	 * @param param
	 * @throws AppException
	 */
	public void writeBackByPurReturn(ScmPurReturnsEntry2 oldEntity,ScmPurReturnsEntry2 newEntity, Param param) throws AppException;	
	
	/**
	 * 根据验收单Id查找对应的收货记录
	 * @param ckId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceiveEntry2> selectByCkId(long ckId, Param param) throws AppException;

	/**
	 * 采购入库回写收货单
	 * @param oldEntity
	 * @param newEntity
	 * @param param
	 * @throws AppException
	 */
	public void writeBackByInWarehs(ScmInvPurInWarehsBillEntry2 oldEntity,ScmInvPurInWarehsBillEntry2 newEntity, Param param) throws AppException;	
	/**
	 * 获取未入库明细行数据
	 * @param pvId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReceiveEntry2> selectByNotWr(long pvId, Param param) throws AppException;

	public void clearInvQty(String string, Param param) throws AppException;
}

