
package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;

public interface ScmPurReturnsEntryBiz extends BaseBiz<ScmPurReturnsEntry2> {
	/**
	 * 根据rtId查询
	 * @param rtId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturnsEntry2> selectByRtId(long rtId, Param param) throws AppException;
	
	/**
	 * 根据rtId删除
	 * @param rtId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByRtId(long rtId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param rtId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByRtId(long rtId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	/**
	 * 直接更新状态
	 * @param scmPurReturnsEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturnsEntry2> updateStatus(List<ScmPurReturnsEntry2> scmPurReturnsEntryList, Param param) throws AppException;
	public void writeBackByInWarehs(ScmInvPurInWarehsBillEntry2 oldEntity,ScmInvPurInWarehsBillEntry2 newEntity, Param param) throws AppException;

	/**
	 * 获取未退货明细
	 * @param rtId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurReturnsEntry2> selectByNotRt(long rtId, Param param) throws AppException;	
}
