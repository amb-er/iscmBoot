package com.armitage.server.iscm.inventorymanage.internaltrans.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;

public interface ScmInvSaleOrderBiz extends BaseBiz<ScmInvSaleOrder2> {

    public ScmInvSaleOrder2 updateStatus(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

    public void generateOutBoundOrders(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

	public Integer checkFollowUpBill(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

	public void delByPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException;
	/**
	 * 查询采购订单对应的销售订单
	 * @param poId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvSaleOrder2> selectByPoId(long poId, Param param) throws AppException;

	public ScmInvSaleOrder2 finish(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

	public ScmInvSaleOrder2 undoFinish(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

	public ScmInvSaleOrder2 undoGenerateOutBoundOrders(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	
	public List<ScmInvSaleOrder2> selectBySaleIssue(long otId, Param param) throws AppException;
	
	/**
	 * 获取销售订单详情
	 * @param scmInvSaleOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleOrder2 queryInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	
	/**
	 * 提交销售订单
	 * @param scmInvSaleOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleOrder2 doSubmitInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	
	/**
	 * 取消提交销售订单
	 * @param scmInvSaleOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleOrder2 doUnSubmitInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	
	/**
	 * 审批销售订单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleOrder2 doAuditInvSaleOrder(CommonAuditParams commonAuditParams, Param param) throws AppException;
	
	/**
	 * 取消审批销售订单
	 * @param scmInvSaleOrder
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvSaleOrder2 doUnAuditInvSaleOrder(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	public ScmInvSaleOrder2 doRelease(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
	public ScmInvSaleOrder2 doUndoRelease(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;

	public ScmInvSaleOrder2 updatePrtCount(ScmInvSaleOrder2 scmInvSaleOrder, Param param) throws AppException;
}
