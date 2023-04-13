package com.armitage.server.iscm.report.costcenter.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public interface ScmCostReportService {
    
	/**
	 * 成本中心物资直拨明细表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectDirectTransDetails")
	public Object selectDirectTransDetails(@Context HttpServletRequest request);

	/**
	 * 成本中心盘存报表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectCostCenterInventory")
	public Object selectCostCenterInventory(@Context HttpServletRequest request);

	/**
	 * 成本中心物资进出存汇总
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSummaryOfMaterials")
	public Object selectSummaryOfMaterials(@Context HttpServletRequest request);
	
	/**
	 * 物资进出存汇总
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectFinSummaryOfMaterials")
	public Object selectFinSummaryOfMaterials(@Context HttpServletRequest request);
	
	/**
	 * 成本中心物资进出存汇总(正中)
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectSummaryOfMaterialsForInOfOut")
	public Object selectSummaryOfMaterialsForInOfOut(@Context HttpServletRequest request);
	
	/**
	 * 成本中心物资进出存明细表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectListOfMaterials")
	public Object selectListOfMaterials(@Context HttpServletRequest request);
	
	/**
         * 成本中心事务发生汇总表（函数）
    * @param request
    * @return
    */
    @GET
    @Path("/selectScmCostTransferOccurSum")
    public String selectScmCostTransferOccurSum(@Context HttpServletRequest request,
        @Context HttpServletResponse response);
    
    @GET
    @Path("/selectNewScmCostTransferOccurSum")
    public Object selectNewScmCostTransferOccurSum(@Context HttpServletRequest request,
        @Context HttpServletResponse response);
    
    @GET
    @Path("/selectScmCostTransferOccurDetail")
    public Object selectScmCostTransferOccurDetail(@Context HttpServletRequest request,
        @Context HttpServletResponse response);
    
    /**
	 * 部门耗用表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectDeptConsume")
	public Object selectDeptConsume(@Context HttpServletRequest request);
	
	/**
	 * 成本中心耗用分析表-部门汇总
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectDeptSummaryConsume")
	public Object selectDeptSummaryConsume(@Context HttpServletRequest request);
	
	/**
	 * 即时成本中心汇总表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectImmediateCostSum")
	public Object selectImmediateCostSum(@Context HttpServletRequest httpServletRequest);
	
	/**
	 * 成本中心转移明细表
	 * @param httpServletRequest
	 * @return
	 */
	@GET
	@Path("/selectmovebillDetails")
	public Object selectmovebillDetails(@Context HttpServletRequest httpServletRequest);

	@GET
	@Path("/selectDeptConsumeOfCostUse")
	public Object selectDeptConsumeOfCostUse(@Context HttpServletRequest httpServletRequest);
}
