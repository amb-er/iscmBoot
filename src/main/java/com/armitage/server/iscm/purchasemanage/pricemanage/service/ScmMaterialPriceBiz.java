package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrePrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAll;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAllQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurSupplyInfoQuery;


public interface ScmMaterialPriceBiz extends BaseBiz<ScmMaterialPrice> {

	public ScmPurPriceAll getMaterialPriceAll(ScmPurPriceAllQuery scmPurPriceAllQuery, Param param) throws AppException;

	/**
	 * 获取物资定价或报价
	 * @param scmPurPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getMaterialPrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException;

	/**
	 * 获取上一次报价
	 * @param scmPurPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getLastQuotationPrice(ScmPurPriceQuery scmPurPriceQuery,Param param) throws AppException;

	/**
	 * 获取销售定价
	 * @param scmInvPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getMaterialSalePrice(ScmInvPriceQuery scmInvPriceQuery, Param param) throws AppException;

	/**
	 * 根据货源获取供应商
	 * @param ScmPurSupplyInfoQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmMaterialPrice getMaterialSupplyInfo(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery,Param param) throws AppException;
	
	/**
	 * 根据货源获取供应商
	 * @param ScmPurSupplyInfoQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getMaterialSupplyInfos(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery,Param param) throws AppException;
	
	public List<ScmPurPriceAll> getMaterialPriceAllList(ScmPurPriceAllQuery scmPurPriceAllQuery, Param param) throws AppException;
	
	/**
	 * 获取物资备选定价
	 * @param scmPurPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrePrice> getPreMaterialPrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException;
	
	/**
	 * 获取物资备选供应商及对应定价
	 * @param scmPurPriceQuery
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmMaterialPrice> getPreParePrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException;

	public List<ScmMaterialPrice> getMaterialSupplyInfoList(ScmPurSupplyInfoQuery scmPurSupplyInfoQuery, Param param) throws AppException;

	public List<ScmMaterialPrice> getMaterialPriceByItemidsAndVendorIdsList(List<ScmPurPriceQuery> object, Param param) throws AppException;

	public ScmMaterialPrice getRecentPriceAndStock(ScmInvPriceQuery scmInvPriceQuery, Param param) throws AppException;

	public ScmMaterialPrice selectCostPrice(long itemId, Param param) throws AppException;

	public List<ScmMaterialPrice> getRecentPriceAndStockList(ScmInvPriceQuery scmInvPriceQuery, Param createParam) throws AppException;
}
