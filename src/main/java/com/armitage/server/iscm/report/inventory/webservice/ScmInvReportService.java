package com.armitage.server.iscm.report.inventory.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public interface ScmInvReportService {
    
    /**
     * 即时结存
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectRealtimeStock")
    public Object selectRealtimeStock(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 全局结存
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectGlobalInventory")
    public Object selectGlobalInventory(@Context HttpServletRequest httpServletRequest);
    
	/**
	 * 即时结存汇总表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectImmediateInvSum")
	public Object selectImmediateInvSum(@Context HttpServletRequest httpServletRequest);
	
	/**
	 * 物资进出存汇总表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInOutSummary")
	public Object selectScmInOutSummary(@Context HttpServletRequest httpServletRequest); 
	
	/**
	 * 入库事务汇总表-物资*供应商
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInvItemWrSum")
	public Object selectScmInvItemWrSum(@Context HttpServletRequest httpServletRequest);
	
	/**
	 * 入库事务汇总表-供应商*物资
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInvItemWrSupplierSum")
	public Object selectScmInvItemWrSupplierSum(@Context HttpServletRequest httpServletRequest);

	/**
	 * 物资进出存明细表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInOutDetail")
	public Object selectScmInOutDetail(@Context HttpServletRequest httpServletRequest); 
	
	/**
	 * 销售汇总表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInvItemSaleSum")
	public Object selectScmInvItemSaleSum(@Context HttpServletRequest httpServletRequest); 
	/**
	 * 入库事务汇总表 - 物资类别（含税）
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectScmInvInWarehsItemClass")
	public String selectScmInvInWarehsItemClass(@Context HttpServletRequest request,
	        @Context HttpServletResponse response); 
	
	/**
	 * 入库事务汇总表 - 物资类别（含税）(新)
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectNewScmInvInWarehsItemClass")
	public Object selectNewScmInvInWarehsItemClass(@Context HttpServletRequest request,
	        @Context HttpServletResponse response); 
	
	/**
	 * 入库事务明细表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvItemWrDetails")
    public Object selectScmInvItemWrDetails(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 寄存入库汇总-供应商
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvConsignSumSup")
    public Object selectScmInvConsignSumSup(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 库龄及呆滞分析表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvStorageAgePrimnessAnalysis")
    public Object selectScmInvStorageAgePrimnessAnalysis(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 寄存出库汇总-供应商
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvDepositorySumSup")
    public Object selectScmInvDepositorySumSup(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 库龄分析报表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvStorageAgeAnalysis")
    public Object selectScmInvStorageAgeAnalysis(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 产品月累计采购分析表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvInWareMonthAnalysis")
    public Object selectScmInvInWareMonthAnalysis(@Context HttpServletRequest httpServletRequest);
    
    /**
     * 产品月累计销售分析表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvSaleMonthAnalysis")
    public Object selectScmInvSaleMonthAnalysis(@Context HttpServletRequest httpServletRequest);
    
    /**
     *  入库事务汇总表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvInWarehsItemSum")
    public Object selectScmInvInWarehsItemSum(@Context HttpServletRequest httpServletRequest);
    
    /**
     *  销售出库事务汇总表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvSaleItemSum")
    public Object selectScmInvSaleItemSum(@Context HttpServletRequest httpServletRequest);
    
    
    /**
     *  日营业汇总表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvSaleBusiness")
    public Object selectScmInvSaleBusiness(@Context HttpServletRequest httpServletRequest);
    
    
    /**
     *  定价信息表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmInvPurSalePrice")
    public Object selectScmInvPurSalePrice(@Context HttpServletRequest httpServletRequest);
    
    /**
     *  供应商比价表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmVendorItemContrast")
    public Object selectScmVendorItemContrast(@Context HttpServletRequest httpServletRequest);
    
    /**
     *  供应商综合情况表
     * @param httpServletRequest
     * @return
     */
    @GET
    @Path("/selectScmPurVendorInfo")
    public Object selectScmPurVendorInfo(@Context HttpServletRequest httpServletRequest);
    

}
