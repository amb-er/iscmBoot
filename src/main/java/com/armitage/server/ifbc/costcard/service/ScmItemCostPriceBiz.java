package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPrice2;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;

public interface ScmItemCostPriceBiz extends BaseBiz<ScmItemCostPrice2> {
	public int updateByPriceUpdSet(String orgUnitNo,Param param) throws AppException;
	public int batchAddItemPrice(ScmItemCostPriceLog scmItemCostPriceLog, Param param) throws AppException;
	public void updateItemPriceHistory(ScmItemCostPriceLog scmItemCostPriceLog,Param param) throws AppException;
	public List<ScmItemCostPrice2> selectItemCost(String resOrgUnitNos, String fmtDate, Param param) throws AppException;
}
