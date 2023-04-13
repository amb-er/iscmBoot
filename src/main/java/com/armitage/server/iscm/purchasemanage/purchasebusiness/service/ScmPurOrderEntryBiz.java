package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntryAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;

public interface ScmPurOrderEntryBiz extends BaseBiz<ScmPurOrderEntry2> {
	
	/**
	 * 根据poId查询
	 * @param poId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrderEntry2> selectByPoId(long poId, Param param) throws AppException;
	
	/**
	 * 根据poId删除
	 * @param poId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPoId(long poId, Param param) throws AppException;
	
	/**
	 * 更新状态
	 * @param poId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateRowStatusByPoId(long poId, String status, String checker, Date checkDate, Param param) throws AppException;
	
	/**
	 * 直接更新状态
	 * @param scmPurOrderEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrderEntry2> updateStatus(List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param) throws AppException;

	/**
	 * 根据明细状态更新单状态
	 * @param scmPurOrderEntry
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void updateBillStatusByEntry(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException;
	
	/**
	 * 获取引入数据
	 * @param scmPurOrderEntryAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean getDataForLeadInto(ScmPurOrderEntryAdvQuery scmPurOrderEntryAdvQuery, Param param) throws AppException;
	
	/**
	 * 收货单回写订货单数量
	 * @param oldEntity
	 * @param newEntity
	 * @param param
	 * @throws AppException
	 */
	public void writeBackByPurReceive(ScmPurReceiveEntry2 oldEntity,ScmPurReceiveEntry2 newEntity, Param param) throws AppException;

	public List<ScmPurOrderEntry2> selectByPoIdAndSaleIssueBill(long poId,long otId, Param param) throws AppException;
	
	/**
	 * 销售订单回写订货单数量
	 * @param oldEntity
	 * @param newEntity
	 * @param param
	 * @throws AppException
	 */
	public void writeBackBySaleOrder(ScmInvSaleOrderEntry2 oldEntity,ScmInvSaleOrderEntry2 newEntity, Param param) throws AppException;
	
	public List<ScmPurOrderEntry2> selectByNotSend(long poId, Param param) throws AppException;

	public void writeBackInvQty(ScmPurReceive2 scmPurReceive,int sign, Param param) throws AppException;

	public void writeBackByOtherIssue(ScmInvOtherIssueBillEntry2 oldEntity, ScmInvOtherIssueBillEntry2 newEntity, Param param) throws AppException;

	public List<ScmPurOrderEntry2> selectByPoIdAndOtherIssueBill(long poId, long otId, Param param) throws AppException;

	public List<ScmPurOrderEntry2> selectStatusByIds(String ids, Param param) throws AppException;
	
	/**
	 * 根据请购Id，行号更新状态
	 * @param prId
	 * @param status
	 * @param checker
	 * @param checkDate
	 * @param param
	 * @throws AppException
	 */
	public void updateRowStatusByLineId(long poId, String status, String checker, Date checkDate, int lineId, Param param) throws AppException;

	/**
	 * 根据poId查询(OA)
	 * @param poId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrderEntry2> selectByPoIdOA(long poId, Param param) throws AppException;

	public void release(ScmPurOrder2 scmPurOrder, Param param) throws AppException;

	public void undoRelease(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	/**
	 * 生成临时定价单
	 * @param scmPurOrderEntryList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean generateTempPrice(List<ScmPurOrderEntry2> scmPurOrderEntryList, Param param) throws AppException;
	
	/**
	 * 根据priceId查询
	 * @param priceId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrderEntry2> selectByPriceId(long priceId, Param param) throws AppException;
	
	public void updatePurOrderEntryByPmId(ScmPurPrice2 scmPurPrice, Param param) throws AppException;
	
	/**
	 * 更新临时定价的价格来源Id
	 * @param scmPurPriceEntry
	 * @param param
	 * @throws AppException
	 */
	public void updatePurOrderPriceBillIdByPmId(long priceId, Param param) throws AppException;

	public void generateAdd(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException;

}

