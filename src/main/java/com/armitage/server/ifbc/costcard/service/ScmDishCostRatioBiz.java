package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio2;

public interface ScmDishCostRatioBiz extends BaseBiz<ScmDishCostRatio2> {
	public List<ScmDishCostRatio2> selectByCardId(long cardId, Param param) throws AppException;
	public List<ScmDishCostRatio2> selectCostRatio(ScmCostCard2 scmCostCard, Param param) throws AppException;
	public List<ScmDishCostRatio2> saveCostRatio(List<ScmDishCostRatio2> scmDishCostRatioList, Param param) throws AppException;
}
