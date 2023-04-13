package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;

public interface ScmPurQuotationEntryBiz extends BaseBiz<ScmPurQuotationEntry2> {

	/**
	 * 根据pqId查询
	 * @param pqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurQuotationEntry2> selectByPqId(long pqId, Param param) throws AppException;
	
	/**
	 * 根据pqId删除
	 * @param pqId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPqId(long pqId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param pqId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPqId(long pqId, String status, String checker, Date checkDate, Param param) throws AppException;

	/**
	 * 根据pqId，itemId查税率，同物资取ID最大的
	 * @param priceBillId
	 * @param itemId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotationEntry2 selectTaxRateByPqId(long pqId, long itemId, Param param) throws AppException;
}
