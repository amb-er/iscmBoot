
package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;

public interface ScmPurPriceEntryBiz extends BaseBiz<ScmPurPriceEntry2> {

	/**
	 * 根据pmId查询
	 * @param pmId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurPriceEntry2> selectByPmId(long pmId, Param param) throws AppException;
	
	/**
	 * 根据pmId删除
	 * @param pmId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPmId(long pmId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param pmId
	 * @param status
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPmId(long pmId, String status, Param param) throws AppException;

	
	public void updateVendorQuotation(ScmPurPriceEntry2 scmPurPriceEntry,Param param) throws AppException ;


	/**
	 * 根据pmId，itemId查税率，同物资取ID最大的
	 * @param priceBillId
	 * @param itemId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurPriceEntry2 selectTaxRateByPmId(long pmId, long itemId, Param param) throws AppException;
}
