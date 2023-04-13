package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;

public interface ScmPurMarketPriceEntryBiz extends BaseBiz<ScmPurMarketPriceEntry2> {

	/**
	 * 根据piId查询
	 * @param piId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurMarketPriceEntry2> selectByPiId(long piId, Param param) throws AppException;
	
	
}
