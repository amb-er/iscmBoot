package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.business.purquotation.params.PurQuotationAddParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationEditParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationListParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

public interface ScmPurQuotationBiz extends BaseBiz<ScmPurQuotation2> {
	
	/*
	 * 删除报价单
	 */
	public void doDelPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException;
	
	/*
	 * 提交报价单
	 */
	public ScmPurQuotation2 doSubmitPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException;
	
	/*
	 * 取消提交报价单
	 */
	public ScmPurQuotation2 doUnSubmitPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException;
	
	/*
	 * 查询报价单列表
	 */
	public List<ScmPurQuotation2> queryPurQuotationList(PurQuotationListParams purQuotationListParams, Param param) throws AppException;
	
	
	/*
	 * 查询报价单详情
	 */
	public ScmPurQuotation2 queryPurQuotation(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException;
	
	/*
	 * 新增报价单
	 */
	public ScmPurQuotation2 doAddPurQuotation(PurQuotationAddParams purQuotationAddParams,Param param) throws AppException ;
	
	
	/*
	 * 修改报价单
	 */
	public ScmPurQuotation2 doEditPurQuotation(PurQuotationEditParams purQuotationEditParams,Param param) throws AppException ;

	/**
	 * 获取物资在某一天的有效报价
	 * @param purOrgUnitNo
	 * @param itemId
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo,long vendorId, String itemIds,Date bizDate, Param param) throws AppException ;

	/**
	 * 查询各供应商在查询时段内最新报价
	 * @param itemId
	 * @param begDate
	 * @param endDate
	 * @param vendorIds
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurQuotation2> selectRecentPrice(long itemId, Date begDate,Date endDate, String vendorIds, Param param) throws AppException;
	/**
	 * 查询某供应商某日期之前最近报价
	 * @param purOrgUnitNo
	 * @param itemIds
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getLastPrice(String itemIds,long vendorId,Date bizDate, Param param) throws AppException ;
	
	/**
	 * 审批报价单
	 * @param commonAuditParams
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 doAuditPurQuotation(CommonAuditParams commonAuditParams, Param param) throws AppException ;
	
	/**
	 * 取消审批报价单
	 * @param scmPurQuotation
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 doUnAuditPurQuotation(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException ;
	/**
	 * 下达
	 * @param scmPurQuotation
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 release(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException;
	/**
	 * 取消下达
	 * @param scmPurQuotation
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public CommonBean undoRelease(List<ScmPurQuotation2> scmPurQuotation,int type, Param param) throws AppException;
	/**
	 * 关闭
	 * @param scmPurQuotation
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 finish(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException ;

	/**
	 * 取消关闭
	 * @param scmPurQuotation
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 undoFinish(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException ;
	
	/**
	 * 查询各供应商各物资在查询时段内最新报价
	 * @param itemId
	 * @param begDate
	 * @param endDate
	 * @param vendorIds
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurQuotation2> selectItemsRecentPrice(String itemIds, Date begDate,Date endDate, String vendorIds, Param param) throws AppException;
	
	public CommonBean undoReleaseCheck(List<ScmPurQuotation2> scmPurQuotationList, int type, Param param) throws AppException;

	/**
	 * 获取物资在某一天的有效报价
	 * @param purOrgUnitNo
	 * @param vendorIds供应商IDs
	 * @param itemIds
	 * @param bizDate
	 * @param param
	 * @return
	 */
	public List<ScmMaterialPrice> getPriceByVendorIds(List<ScmPurPriceQuery> scmPurQuotationList,Param param) throws AppException;
	/**
	 * 根据定价单生成对应供应商的报价
	 * @param pmId
	 * @param vendorId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurQuotation2 generateBillFromPmBill(ScmPurPrice2 scmPurPrice,long vendorId,Param param) throws AppException;

	public void releaseByPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

	public void undoReleaseByPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

	public ScmPurQuotation2 updatePrtCount(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException;
}