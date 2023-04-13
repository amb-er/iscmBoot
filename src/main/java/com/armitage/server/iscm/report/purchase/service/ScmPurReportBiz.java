package com.armitage.server.iscm.report.purchase.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.report.purchase.model.OrderTransTotalAPI;
import com.armitage.server.iscm.report.purchase.model.ScmPurHistoryPrice;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransTotal;
import com.armitage.server.iscm.report.purchase.model.ScmPurReport;
import com.armitage.server.iscm.report.purchase.model.ScmPurReturnInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurSupplierAppraiseDetails;

public interface ScmPurReportBiz extends BaseBiz<ScmPurReport> {
	/**
	 * 供应商综合情况表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrder2> selectSupplierConsolidation(HttpServletRequest request) throws Exception;
	/**
	 * 物资采购排行榜
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrder2> selectMaterialProcurement(HttpServletRequest request) throws Exception;
	
	/**
	 * 物资交易明细表（按供应商*库存组织）
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrder2> selectMaterialTransDetails(HttpServletRequest request) throws Exception;
	
	/**
	 * 供应商交易汇总表（退货按业务日期）
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<OrderTransTotalAPI> selectSupTransSummary(HttpServletRequest request) throws Exception;
	
	/**
	 * 供应商交易明细表（按物资汇总）
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurOrderTransInfo> selectSupTransItemSummary(HttpServletRequest request) throws Exception;
	
	/**
     * 采购订单应到未到明细表
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    public List<ScmPurOrder2> selectPODueOrNot(HttpServletRequest httpServletRequest) throws Exception;
    
    /**
     * 订货发货汇总表
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    public List<ScmPurRequire2> selectOrderDeliverySummary(HttpServletRequest httpServletRequest) throws Exception;
    
    /**
     * 部门申购汇总表
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    public List<ScmPurRequire2> selectDeptApplySummary(HttpServletRequest httpServletRequest) throws Exception;
    
    /**
     * 采购价格信息查询表
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    public List<ScmPurPrice2> selectPurPriceInfo(HttpServletRequest httpServletRequest) throws Exception;
    
    /**
     * 采购价格信息查询表
     * @param request
     * @return
     * @throws Exception
     */
    public List<ScmPurPrice2> selectPurPriceInfoCheck(HttpServletRequest request) throws Exception;

	/**
	 * 采购历史价格查询表
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurHistoryPrice> selectPurHistoryPrice(HttpServletRequest httpServletRequest) throws Exception;
	
	/**
	 * 订货跟进明细表
	 * @param param
	 * @return
	 * @throws AppException
	 */
	
	public List<ScmPurOrderTransInfo> selectSupplierDetails(HttpServletRequest request) throws Exception;
	
	
	/**
	 * 订货跟进汇总表
	 * @param param
	 * @return
	 * @throws AppException
	 */
	
	public List<ScmPurOrderTransTotal> selectSupplierSummary(HttpServletRequest request) throws Exception;
	
	/**
	 * 供应商订货汇总表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<ScmPurRequireEntry2> selectSupplierOrderSummary(HttpServletRequest request) throws Exception;
	
	/**
	 * 采购退货情况表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<ScmPurReturnInfo> selectPurchaseReturn(HttpServletRequest request) throws Exception;
	
	/**
	 * 供应商考核明细表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<ScmPurSupplierAppraiseDetails> selectScmPurSupplierAppraiseDetails(HttpServletRequest request) throws Exception;

	
	/**
	 * 配送清单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<ScmPurDelivery> selectScmPurDelivery(HttpServletRequest request) throws Exception;
}

