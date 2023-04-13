package com.armitage.server.iscm.purchasemanage.purchasebusiness.service;


import java.math.BigDecimal;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;

public interface ScmPurOrderBiz extends BaseBiz<ScmPurOrder2> {
	
	/**
	 * 生成采购订单的收货单或者销售订单
	 * @param scmPurOrder
	 * @param param
	 * @throws AppException
	 */
	public void sendOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;

	public ScmPurOrderEntry2 getPreUseQty(String orgUnitNo,long itemId, Param param) throws AppException;

	public ScmPurOrder2 submit(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public ScmPurOrder2 undoSubmit(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public ScmPurOrder2 release(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public ScmPurOrder2 undoRelease(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	public List<ScmPurOrder2> selectBySaleIssueBill(long otId, Param param) throws AppException;

	public void unSendOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;

	public void writeBackInvQty(ScmPurReceive2 scmPurReceive,int sign, Param param) throws AppException;

	public List<ScmPurOrder2> selectByOtherIssueBill(long otId, Param param) throws AppException;
	
	public int updateSendedStaus(long poId, Param param) throws AppException;

	/**
	 * 收货单整单回写数量及状态
	 * @param pvId
	 * @param param
	 * @throws AppException
	 */
	public void writeBackQtyAndStatus(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException;
	
	/**
	 * 查询订货单列表接口
	 * @param scmPurOrderAdvQuery
	 * @param pageNum
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrder2> queryPurOrderList(ScmPurOrderAdvQuery scmPurOrderAdvQuery, int pageNum, Param param) throws AppException;
	
	/**
	 * 查询订货单详情
	 * @param scmPurOrder
	 * @param pageNum
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurOrder2 queryPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	/**
	 * 审批订货单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurOrder2 doAuditPurOrder(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
     * 取消审批订货单
     * @param scmPurOrder
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurOrder2 doUnAuditPurOrder(ScmPurOrder2 scmPurOrder,Param param) throws AppException ;
    
    /**
	 * 锁单
	 * @param scmPurOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurOrder2 doLockPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	/**
	 * 更新合同编号
	 * @param scmPurOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurOrder2 doUpdatePurOrderContractNo(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	/**
	 * 更新单据状态
	 * @param scmPurOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurOrder2 doUpdatePurOrderStatus(ScmPurOrder2 scmPurOrder, Param param) throws AppException;

	/**
	 * 查询订货单(OA)
	 * @param scmPurOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrder2> queryPurOrderOA(ScmPurOrder2 scmPurOrder, Param param) throws AppException;


	/**
	 * 获取一段时间内的订货总数量
	 * @param scmPurOrderAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public BigDecimal getTotalOrderQty(ScmPurOrderAdvQuery scmPurOrderAdvQuery, Param param) throws AppException ;
	
	public ScmPurOrder2 unlockBill(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	public void updatePushed(ScmPurOrder2 scmPurOrder,Param param) throws AppException ;
	
	public void updateVersion(ScmPurOrderEntry2 scmPurOrderEntry,Param param) throws AppException ;
	
	public ScmPurOrder2 selectByPoNo(String poNo, Param param) throws AppException;
	/**
	 * 收货单整单回写数量及状态
	 * @param pvId
	 * @param param
	 * @throws AppException
	 */
	public void writeBackMovedQtyAndStatus(List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList, Param param) throws AppException;
	
	public void updateLockStatusOrContractNoByPoId(long poId, String lockStatus, String contractNo, Param param) throws AppException;
	
	public void updateBillNoChangeTime(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	
	public void writeBackSended(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException;
	
	public List<ScmPurOrder2> selectByEntryIds(String entryIds, String sended, Param param) throws AppException;
	
	public int updateUnSendedStaus(long poId, Param param) throws AppException;

	public ScmPurOrder2 selectByTypeCode(String code, Param param) throws AppException;

	public ScmPurOrder2 updatePrtCount(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
}

