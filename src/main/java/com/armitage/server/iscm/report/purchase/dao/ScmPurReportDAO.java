package com.armitage.server.iscm.report.purchase.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.report.purchase.model.ScmPurHistoryPrice;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurOrderTransTotal;
import com.armitage.server.iscm.report.purchase.model.ScmPurReport;
import com.armitage.server.iscm.report.purchase.model.ScmPurReturnInfo;
import com.armitage.server.iscm.report.purchase.model.ScmPurSupplierAppraiseDetails;

public interface ScmPurReportDAO  extends BasicDAO<ScmPurReport>{
	//供应商综合情况表 
	public List<ScmPurOrder2> selectSupplierConsolidation(HashMap<String, Object> map) throws DAOException;

	//物资采购排行榜
	public List<ScmPurOrder2> selectMaterialProcurement(HashMap<String, Object> map) throws DAOException;
	
	//物资交易明细表（按供应商*库粗组织）
	public List<ScmPurOrder2> selectMaterialTransDetails(HashMap<String, Object> map) throws Exception;
	
	//供应商交易汇总表（退货按业务日期）
	public List<ScmPurOrderTransTotal> selectSupTransSummary(HashMap<String, Object> map) throws Exception;
	
	//供应商交易物资汇总表
	public List<ScmPurOrderTransInfo> selectSupTransItemSummary(HashMap<String, Object> map) throws Exception;
	
	//采购订单应到未到明细表
    public List<ScmPurOrder2> selectPODueOrNot(HashMap<String, Object> map) throws DAOException;

	// 订货发货汇总表
	public List<ScmPurRequire2> selectOrderDeliverySummary(HashMap<String, Object> map);
	
	// 部门申购汇总表
	public List<ScmPurRequire2> selectDeptApplySummary(HashMap<String, Object> map);

	// 采购价格信息查询表
	public List<ScmPurPrice2> selectPurPriceInfo(HashMap<String, Object> map)  throws DAOException;

	//采购价格信息核查
	public List<ScmPurPrice2> selectPurPriceInfoCheck(HashMap<String, Object> map)  throws DAOException;
	
	/*采购历史价格查询表*/
	public List<ScmPurHistoryPrice> selectPurHistoryPrice(HashMap<String, Object> map) throws DAOException;
	
	/*供应商交易明细表*/
	public List<ScmPurOrderTransInfo> selectSupplierDetails(HashMap<String, Object> map) throws DAOException;
	
	/*供应商交易汇总表*/
	public List<ScmPurOrderTransTotal> selectSupplierSummary(HashMap<String, Object> map) throws DAOException;

	/*供应商订货汇总表*/
	public List<ScmPurRequireEntry2> selectSupplierOrderSummary(HashMap<String, Object> map);
	
	/*采购退货情况表*/
	public List<ScmPurReturnInfo> selectPurchaseReturn(HashMap<String, Object> map);
	
	/*供应商考核明细表*/
	public List<ScmPurSupplierAppraiseDetails> selectScmPurSupplierAppraiseDetails (HashMap<String, Object> map);
	
}
