package com.armitage.server.ifbc.report.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public interface ScmFbcReportService {
    
	/**
	 * 通用报表查询方法
	 * @param request
	 * @return
	 */
	@GET
	@Path("/queryFbcReportData")
	public Object queryFbcReportData(@Context HttpServletRequest request);

	@GET
	@Path("/queryFbcStoreProfitData")
	public Object queryFbcStoreProfitData(@Context HttpServletRequest request);
	
	/**
	 * 毛利率分析表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectOrgAndOutltProfit")
	public Object selectOrgAndOutltProfit(@Context HttpServletRequest request);
	
	
	/**
	 * 菜品销售结构分析表
	 * @param request
	 * @return
	 */
	@GET
	@Path("/selectdishSaleStructureAnalysis")
	public Object selectDishSaleStructureAnalysis(@Context HttpServletRequest request);
	
}
