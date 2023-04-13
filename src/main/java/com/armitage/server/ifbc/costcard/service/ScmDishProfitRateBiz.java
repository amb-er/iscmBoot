package com.armitage.server.ifbc.costcard.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishProfitRate;

public interface ScmDishProfitRateBiz extends BaseBiz<ScmDishProfitRate> {
	public ScmDishProfitRate selectProfitRateByCostCard(ScmCostCard2 scmCostCard, Param param) throws AppException;
}
