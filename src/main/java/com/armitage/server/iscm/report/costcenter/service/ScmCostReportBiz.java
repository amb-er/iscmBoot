package com.armitage.server.iscm.report.costcenter.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmDeptConsumeQuery;
import com.armitage.server.iscm.report.costcenter.model.ScmCostDirectTransfer;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmCostItemInAndOutSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostRealtimeStockSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCostReport;
import com.armitage.server.iscm.report.costcenter.model.ScmCostTransferOccurSum;
import com.armitage.server.iscm.report.costcenter.model.ScmCountingTaskSum;
import com.armitage.server.iscm.report.costcenter.model.ScmDeptConsume;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurDetail;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurSum;

public interface ScmCostReportBiz extends BaseBiz<ScmCostReport> {
	/**
	 * 成本中心物资直拨明细表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostDirectTransfer> selectDirectTransDetails(HttpServletRequest request) throws AppException;
	
	/**
	 * 盘存盘点报表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCountingTaskSum> selectCostCenterInventory(HttpServletRequest request) throws AppException;
	
	/**
	 * 成本中心物资进出存汇总
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostItemInAndOutSum> selectSummaryOfMaterials(HttpServletRequest request) throws AppException;
	
	/**
	 * 物资进出存汇总
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostItemInAndOutSum> selectFinSummaryOfMaterials(HttpServletRequest request) throws AppException;
	
	/**
	 * 成本中心物资进出存汇总(正中)
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostItemInAndOutSum> selectSummaryOfMaterialsForInOfOut(HttpServletRequest request) throws AppException;
    
	/**
	 * 成本中心物资进出存明细表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostItemInAndOutDetail> selectListOfMaterials(HttpServletRequest request) throws AppException;

	
	/**
	 * 成本中心事务发生汇总表（含税）
	 * @param request
	 * @return
	 * @throws AppException
	 */
    public List<ScmCostTransferOccurSum> selectScmCostTransferOccurSum(HttpServletRequest request) throws AppException;
    
	/**
	 * 成本中心事务发生明细表
	 * @param request
	 * @return
	 * @throws AppException
	 */
    public List<ScmNewCostTransferOccurDetail> selectScmCostTransferOccurDetail(HttpServletRequest request) throws AppException;
    
    /**
	 * 部门耗用表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmDeptConsume> selectDeptConsume(HttpServletRequest request) throws AppException;
	
	/**
	 * 成本中心耗用分析表-部门汇总
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmDeptConsume> selectDeptSummaryConsume(HttpServletRequest request) throws AppException;
	
	public List<ScmNewCostTransferOccurSum> selectNewScmCostTransferOccurSum(HttpServletRequest request) throws AppException;
	
	/**
	 * 即时成本中心汇总表
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostRealtimeStockSum> selectImmediateCostSum(HttpServletRequest request) throws AppException;

	public List selectmovebillDetails(HttpServletRequest httpServletRequest) throws AppException;

	public List<ScmCostFinDeptConsume> selectDeptConsumeOfCostUse(HttpServletRequest httpServletRequest) throws AppException;
	public List<ScmCostFinDeptConsume> selectCostFinConsume(ScmCostFinDeptConsumeQuery scmDeptConsumeQuery, Param param) throws AppException;
}

