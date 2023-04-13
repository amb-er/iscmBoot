package com.armitage.server.iscm.report.inventory.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgeAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvDepositorySumSup;
import com.armitage.server.iscm.report.inventory.model.ScmInvGlobalStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWareMonthAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemClass2;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemSaleSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrDetails;
import com.armitage.server.iscm.report.inventory.model.ScmInvItemWrSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvPurSalePrice;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStock;
import com.armitage.server.iscm.report.inventory.model.ScmInvRealtimeStockSum;
import com.armitage.server.iscm.report.inventory.model.ScmInvReport;
import com.armitage.server.iscm.report.inventory.model.ScmInvSaleBusiness;
import com.armitage.server.iscm.report.inventory.model.ScmInvStorageAgePrimnessAnalysis;
import com.armitage.server.iscm.report.inventory.model.ScmPurVendorInfo;
import com.armitage.server.iscm.report.inventory.model.ScmVendorItemContrast;
import com.armitage.server.iscm.report.inventory.model.ScmInvInWarehsItemSum;

public interface ScmInvReportDAO  extends BasicDAO<ScmInvReport>{
    // 即时结存查询报表
    public List<ScmInvRealtimeStock> selectRealtimeStock(HashMap<String, Object> map) throws DAOException;
    // 全局库存查询报表
    public List<ScmInvGlobalStock> selectGlobalInventory(HashMap<String, Object> map) throws DAOException;
    // 即时结存汇总报表
	public List<ScmInvRealtimeStockSum> selectImmediateInvSum(HashMap<String, Object> map) throws DAOException;
	//物资进出存汇总表
	public List<ScmMaterial2> selectScmInOutSummary(HashMap<String, Object> map) throws DAOException;
	//入库事务汇总表
    public List<ScmInvItemWrSum> selectScmInvItemWrSum(HashMap<String, Object> map) throws DAOException;

	//销售汇总表 
	public List<ScmInvItemSaleSum> selectScmInvItemSaleSum(HashMap<String, Object> map) throws DAOException;
	
	//入库事务汇总表 - 物资类别（含税）
	public List<ScmInvInWarehsItemClass> selectScmInvInWarehsItemClass(HashMap<String, Object> map) throws DAOException;
	
	//入库事务汇总表 - 物资类别（含税）(新)
	public List<ScmInvInWarehsItemClass2> selectNewScmInvInWarehsItemClass(HashMap<String, Object> map) throws DAOException;
	
	//入库事务明细表
    public List<ScmInvItemWrDetails> selectScmInvItemWrDetails(HashMap<String, Object> map) throws DAOException;
	
    // 寄存入库汇总-供应商
    public List<ScmInvItemWrSum> selectScmInvConsignSumSup(HashMap<String, Object> map) throws DAOException;
    
    //库龄及呆滞分析表
    public List<ScmInvStorageAgePrimnessAnalysis> selectScmInvStorageAgePrimnessAnalysis(HashMap<String, Object> map) throws DAOException;
    
    // 寄存出库汇总-供应商
    public List<ScmInvDepositorySumSup> selectScmInvDepositorySumSup(HashMap<String, Object> map) throws DAOException;
    
    // 库龄分析报表
    public List<ScmInvStorageAgeAnalysis> selectScmInvStorageAgeAnalysis(HashMap<String, Object> map) throws DAOException;
    
	// 菜品月累计采购分析表   
	public List<ScmInvInWareMonthAnalysis> selectScmInvInWareMonthAnalysis(HashMap<String, Object> map) throws DAOException;
	
	// 菜品月累计销售分析表 
	public List<ScmInvInWareMonthAnalysis> selectScmInvSaleMonthAnalysis(HashMap<String, Object> map) throws DAOException;
	
	 
    // 入库事务汇总表
    public List<ScmInvInWarehsItemSum> selectScmInvInWarehsItemSum(HashMap<String, Object> map) throws DAOException;
    
    
    // 销售出库事务汇总表
    public List<ScmInvInWarehsItemSum> selectScmInvSaleItemSum(HashMap<String, Object> map) throws DAOException;
    
    //日营业汇报表_进货数据
    public List<ScmInvSaleBusiness> selectScmInvSaleBusinessPurchase(HashMap<String, Object> map) throws DAOException;
    //日营业汇报表_销售数据
    public List<ScmInvSaleBusiness> selectScmInvSaleBusinessSale(HashMap<String, Object> map) throws DAOException;
    
    
    //定价信息表 
    //定价信息表 - 销售定价最新一条数据
    public List<ScmInvPurSalePrice> selectScmInvPurSalePrice_Sale(HashMap<String, Object> map) throws DAOException;
    //定价信息表 - 采购定价最新一条数据
    public List<ScmInvPurSalePrice> selectScmInvPurSalePrice_Pur(HashMap<String, Object> map) throws DAOException;
    
    
    //供应商物资比价表
    public List<ScmVendorItemContrast> selectScmVendorItemContras(HashMap<String, Object> map) throws DAOException;
	//供应商综合情况表
    public List<ScmPurVendorInfo> selectScmPurVendorInfo(HashMap<String, Object> hashMap);
    
    
}
