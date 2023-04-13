package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;

public interface ScmCostCardDetailBiz extends BaseBiz<ScmCostCardDetail2> {
	/**
	 * 根据cardId查询
	 * @param cardId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmCostCardDetail2> selectByCardId(long cardId, Param param) throws AppException;
	
	/**
	 * 根据cardId删除
	 * @param cardId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByCardId(long cardId, Param param) throws AppException;
	
	public void replaceItemByCardIds(String cardIds,ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException;
	
	public List<ScmCostCardDetail2> checkItemCostPrice(ScmPriceUpdSet scmPriceUpdSet, Param param) throws AppException;
}
