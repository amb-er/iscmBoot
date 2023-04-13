package com.armitage.server.iscm.report.purchase.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public interface ScmPurReportService {
	
	/**
	 * 供应商综合情况表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSupplierConsolidation")
	public Object selectSupplierConsolidation(@Context HttpServletRequest request);
	
	/**
	 * 物资采购排行榜
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectMaterialProcurement")
	public Object selectMaterialProcurement(@Context HttpServletRequest request);
	
	/**
	 * 供应商交易汇总表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSupTransSummary")
	public Object selectSupTransSummary(@Context HttpServletRequest request);
	
	/**
	 * 物资交易明细表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectMaterialTransDetails")
	public Object selectMaterialTransDetails(@Context HttpServletRequest request);
		
	/**
	 * 供应商交易物资汇总表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSupTransItemSummary")
	public Object selectSupTransItemSummary(@Context HttpServletRequest request);

	@GET
    @Path("/selectPODueOrNot")
    public Object selectPODueOrNot(@Context HttpServletRequest request);
	
    /**
     * 订货发货汇总表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectOrderDeliverySummary")
    public Object selectOrderDeliverySummary(@Context HttpServletRequest httpServletRequest);

    /**
     * 部门申购汇总表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectDeptApplySummary")
    public Object selectDeptApplySummary(@Context HttpServletRequest httpServletRequest);

    /**
     * 采购价格信息查询表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectPurPriceInfo")
    public Object selectPurPriceInfo(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 采购价格信息查询表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectPurPriceInfoCheck")
    public Object selectPurPriceInfoCheck(@Context HttpServletRequest httpServletRequest);

	/**
	 * 采购历史价格查询表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectPurHistoryPrice")
	public Object selectPurHistoryPrice(@Context HttpServletRequest httpServletRequest);
	
	/**
	 * 订货跟进明细表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSupplierDetails")
	public Object selectSupplierDetails(@Context HttpServletRequest request);
	
	/**
	 * 订货跟进汇总表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSupplierSummary")
	public Object selectSupplierSummary(@Context HttpServletRequest request);

	/**
	 * 供应商订货汇总表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectSupplierOrderSummary")
	public Object selectSupplierOrderSummary(@Context HttpServletRequest httpServletRequest);

	/**
	 * 采购退货情况表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectPurchaseReturn")
	public Object selectPurchaseReturn(@Context HttpServletRequest request);
	
	/**
	 * 供应商考核明细表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectScmPurSupplierAppraiseDetails")
	public Object selectScmPurSupplierAppraiseDetails(@Context HttpServletRequest request);
	/**
	 * 配送清单
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectScmPurDelivery")
	public Object selectScmPurDelivery(@Context HttpServletRequest request);
}

