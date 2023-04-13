package com.armitage.server.ifbc.report.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.report.model.ScmDeptAndOutltProfit;
import com.armitage.server.ifbc.report.model.ScmDishSaleStructureAnalysis;

public interface ScmFbcReportBiz extends BaseBiz<BaseModel> {
	/**
	 * 通用报表数据查询
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List queryFbcReportData(HttpServletRequest request) throws AppException;
	/**
	 * 查询门店损溢汇总表
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public List queryFbcStoreProfitData(HttpServletRequest request) throws AppException;
	
	/**
	 * 毛利率分析表
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public List<ScmDeptAndOutltProfit> selectOrgAndOutltProfit(HttpServletRequest request) throws AppException;
	
	/**
	 * 菜品销售结构分析表
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public List<ScmDishSaleStructureAnalysis> selectDishSaleStructureAnalysis(HttpServletRequest request) throws AppException;
}

