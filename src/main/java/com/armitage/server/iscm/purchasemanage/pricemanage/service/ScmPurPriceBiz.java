package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceImPort;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;


public interface ScmPurPriceBiz extends BaseBiz<ScmPurPrice2> {
	
    /**
     * 删除定价单
	 * 
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */

	public void doDelPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;
	

	/**
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */
	public ScmPurPrice2 doSubmitPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

	/**
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */
	public ScmPurPrice2 doUnSubmitPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

	/**
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */
	public ScmPurPrice2 doReleasePurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

	/**
	 * @param scmPurPrice
	 * @param param
	 * @throws AppException
	 */
	public CommonBean doUndoReleasePurPrice(List<ScmPurPrice2> scmPurPrice,int type, Param param) throws AppException;
	/**
	 * 查询定价单列表接口
	 * @param scmPurPriceAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurPrice2> queryPurPriceList(ScmPurPriceAdvQuery scmPurPriceAdvQuery, int pageNum, Param param) throws AppException;

	/**
	 * 查询定价单详情接口
	 * @param scmPurPriceAdvQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurPrice2 queryPurPrice(ScmPurPrice scmPurPrice, Param param) throws AppException;
	
	/**
	 * 获取物资在某一天的有效定价
	 * @param purOrgUnitNo
	 * @param itemId
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo, String itemIds,Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException ;

	public ScmPurPrice2 getPrePrice(String purOrgUnitNo, long itemId,Date bizDate, Param param) throws AppException ;
	public ScmPurPrice2 getLastYearPrice(String purOrgUnitNo, long itemId,Date begDate,Date endDate, Param param) throws AppException ;

	public ScmPurPrice2 finish(ScmPurPrice2 scmPurPrice, Param param) throws AppException ;

	public ScmPurPrice2 undoFinish(ScmPurPrice2 scmPurPrice, Param param) throws AppException ;


    public ScmPurPrice2 importExcel(ScmPurPriceImPort scmPurPriceImPort, Param param) throws AppException;
    
    /**
     * 审批定价单
     * @param commonAuditParams
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurPrice2 doAuditPurPrice(CommonAuditParams commonAuditParams, Param param) throws AppException;
    
    /**
     * 取消审批定价单
     * @param scmPurPrice
     * @param param
     * @return
     * @throws AppException
     */
    public ScmPurPrice2 doUnAuditPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException;
    
    public ScmPurPrice2 selectByPmNo(String pmNo, Param param) throws AppException;
    
    public void updateVendorPqData(ScmPurPrice2 scmPurPrice,Param param) throws AppException ;
    
    public List<ScmSupplierRegInvitation> getEnterSupplierPlat(String vendorIds, Param param) throws AppException ;
    
    /**
	 * 获取物资在某一天的有效备选定价
	 * @param purOrgUnitNo
	 * @param itemId
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrePrice> getPreMaterialPrice(String purOrgUnitNo, String itemIds,Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException ;
	
	/**
	 * 获取物资在某一天的有效定价及备选定价
	 * @param purOrgUnitNo
	 * @param itemId
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getPreParePrice(String purOrgUnitNo, String itemIds,Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException ;
	
	/**
	 * 获取物资在某一天的有效备选定价
	 * @param purOrgUnitNo
	 * @param itemId
	 * @param bizDate
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getPreParePriceByVendorId(String purOrgUnitNo, long vendorId, String itemIds,Date bizDate, String finOrgUnitNo, String pmId, Param param) throws AppException ;

	/**
	 * 获取供应商定价
	 * @param purOrgUnitNo
	 * @param itemIds
	 * @param bizDate
	 * @param finOrgUnitNo
	 * @param pmId
	 * @param vendorId
	 * @param param
	 * @return
	 */
	public List<ScmMaterialPrice> getPreParePrice(String purOrgUnitNo, String itemIds, Date bizDate,
			String finOrgUnitNo, String pmId, long vendorId, Param param) throws AppException;


	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo, String itemIds, Date bizDate, String finOrgUnitNo,
			String pmId, long vendorId, Param param) throws AppException;


	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(List<ScmPurPriceQuery> object, Param param) throws AppException;


	/**
	 * 获取上期定价
	 * @param scmPurPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrePrice> getPrePrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException;


	public List selectLastYearPriceByVendor(List<ScmPurPriceQuery> list, Param param) throws AppException;


	public List selectPrePriceByVendor(List<ScmPurPriceQuery> list, Param param) throws AppException;


	public ScmPurPrice2 updatePrtCount(ScmPurPrice2 scmPurPrice, Param param) throws AppException;

}
