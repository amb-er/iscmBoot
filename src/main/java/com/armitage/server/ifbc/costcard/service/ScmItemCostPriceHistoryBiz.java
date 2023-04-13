package com.armitage.server.ifbc.costcard.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceHistory;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;

public interface ScmItemCostPriceHistoryBiz extends BaseBiz<ScmItemCostPriceHistory> {
	public void updateItemPriceHistory(ScmItemCostPriceLog scmItemCostPriceLog, Param param) throws AppException;
}
