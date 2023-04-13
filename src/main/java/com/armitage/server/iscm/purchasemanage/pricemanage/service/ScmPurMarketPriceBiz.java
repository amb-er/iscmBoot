package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceEditParams;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceAdvQuery;


public interface ScmPurMarketPriceBiz extends BaseBiz<ScmPurMarketPrice2> {

	/**
	 * 提交
	 * @param scmPurMarketPrice
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurMarketPrice2 submit(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;
	
	/**
	 * 取消提交
	 * @param scmPurMarketPrice
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurMarketPrice2 undoSubmit(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;

	/**
	 * 下达
	 * @param scmPurMarketPrice
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurMarketPrice2 release(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;

	/**
	 * 取消下达
	 * @param scmPurMarketPrice
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmPurMarketPrice2 undoRelease(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;

	public List<ScmPurMarketPrice2> selectRecentPrice(long itemId, Date begDate,Date endDate, String enquiryGroupIds, Param param) throws AppException;

	public List<ScmPurMarketPrice2> queryPurMarketPriceList(ScmPurMarketPriceAdvQuery scmPurMarketPriceAdvQuery, int pageNum,Param param) throws AppException;

	public ScmPurMarketPrice2 queryPurMarketPrice(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;

	public ScmPurMarketPrice2 doEditPurMarketPrice(PurMarketPriceEditParams purMarketPriceEditParams, Param param) throws AppException;
	
	public ScmPurMarketPrice2 finish(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;
	
	public ScmPurMarketPrice2 undoFinish(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;

	public List<ScmPurMarketPrice2> selectItemsRecentPrice(String itemIds, Date begDate, Date endDate, String string) throws AppException;

	public ScmPurMarketPrice2 updatePrtCount(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException;
}
