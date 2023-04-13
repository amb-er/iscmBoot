package com.armitage.server.iscm.report.inventory.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgeAnalysis;
import com.armitage.server.iscm.report.costcenter.model.ScmNewCostTransferOccurSum;
import com.armitage.server.iscm.report.inventory.model.RealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvDepositorySumSup;
import com.armitage.server.iscm.report.inventory.model.ScmInvGlobalStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWareMonthAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass2;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutDetail;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemInAndOutSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemSaleSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrDetails;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSumMaterials;
import com.armitage.server.iscm.report.inventory.model.ScmInvPurSalePrice;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStockSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvSaleBusiness;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgePrimnessAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmPurVendorInfo;
import com.armitage.server.iscm.report.inventory.model.ScmVendorItemContrast;

public interface ScmInvReportBiz extends BaseBiz<ScmInvReport> {
    /**
     * 即时库存查询报表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<RealtimeStock> selectRealtimeStock(HttpServletRequest httpServletRequest) throws AppException;
    /**
     * 全局库存查询
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvGlobalStock> selectGlobalInventory(HttpServletRequest httpServletRequest) throws AppException;
	/**
	 * 即时库存汇总表-按物资分类
	 * @param param 
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvRealtimeStockSum> selectImmediateInvSum(HttpServletRequest request) throws AppException;
    /**
     * 物资进出存汇总表
     * @param httpServletRequest
     * @return
     */
    public List<ScmInvItemInAndOutSum> selectScmInOutSummary(HttpServletRequest httpServletRequest) throws AppException;

	/**
	 * 入库事务汇总表-物资*供应商
	 * @param httpServletRequest
	 * @return
	 */
    public List<ScmInvItemWrSum> selectScmInvItemWrSum(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
	 * 入库事务汇总表-供应商*物资
	 * @param httpServletRequest
	 * @return
	 */
    public List<ScmInvItemWrSum> selectScmInvItemWrSupplierSum(HttpServletRequest httpServletRequest) throws AppException;
	
	/**
	 * 物资进出存明细表
	 * @param request
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvItemInAndOutDetail> selectScmInOutDetail(HttpServletRequest request) throws AppException;
	/**
	 * 销售汇总表
	 * @param httpServletRequest
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvItemSaleSum> selectScmInvItemSaleSum(HttpServletRequest httpServletRequest) throws AppException;

	/**
	 * 入库事务汇总表 - 物资类别（含税）
	 * @param httpServletRequest
	 * @return
	 * @throws AppException
	 */
	public List<HashMap> selectScmInvInWarehsItemClass(HttpServletRequest httpServletRequest) throws AppException;
	
	/**
	 * 入库事务汇总表 - 物资类别（含税）(新)
	 * @param httpServletRequest
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvInWarehsItemClass2> selectNewScmInvInWarehsItemClass(HttpServletRequest httpServletRequest) throws AppException;
	
	/**
	 * 入库事务明细表
	 * @param httpServletRequest
	 * @return
	 * @throws AppException
	 */
    public List<ScmInvItemWrDetails> selectScmInvItemWrDetails(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 寄存入库汇总-供应商
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvItemWrSum> selectScmInvConsignSumSup(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 库龄及呆滞分析表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvStorageAgePrimnessAnalysis> selectScmInvStorageAgePrimnessAnalysis(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 寄存出库汇总-供应商
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvDepositorySumSup> selectScmInvDepositorySumSup(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 库龄分析报表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvStorageAgeAnalysis> selectScmInvStorageAgeAnalysis(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 产品月累计采购分析表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
	public List<ScmInvInWareMonthAnalysis> selectScmInvInWareMonthAnalysis(HttpServletRequest httpServletRequest) throws AppException;
	
    /**
     * 产品月累计销售分析表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
	public List<ScmInvInWareMonthAnalysis> selectScmInvSaleMonthAnalysis(HttpServletRequest httpServletRequest) throws AppException;
	
	/**
     * 入库事务汇总表
     * @param httpServletRequest
     * @return
     * @throws AppException
     */
    public List<ScmInvInWarehsItemSum> selectScmInvInWarehsItemSum(HttpServletRequest httpServletRequest) throws AppException;
	
    /**
     * 销售出库事务汇总表
     * @param httpServletRequest
     * @return
     */
    public List<ScmInvInWarehsItemSum> selectScmInvSaleItemSum(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 日营业汇总表
     * @param httpServletRequest
     * @return
     */
    public List<ScmInvSaleBusiness> selectScmInvSaleBusiness(HttpServletRequest httpServletRequest) throws AppException;
    
    /**
     * 定价信息表
     * @param httpServletRequest
     * @return
     */
    public List<ScmInvPurSalePrice> selectScmInvPurSalePrice(HttpServletRequest httpServletRequest) throws AppException;
	
    /**
     * 供应商比价表
     * @param httpServletRequest
     * @return
     */
    public List<ScmVendorItemContrast> selectScmVendorItemContrast(HttpServletRequest httpServletRequest);
	
    /**
     * 供应商综合情况表
     * @param httpServletRequest
     * @return
     */
    public List<ScmPurVendorInfo> selectScmPurVendorInfo(HttpServletRequest httpServletRequest);
    
}

