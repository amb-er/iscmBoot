package com.armitage.server.ifbc.costcard.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail2;

public interface ScmItemCostPriceLogDetailBiz extends BaseBiz<ScmItemCostPriceLogDetail2> {

	public void batchAddItemPrice(String orgUnitNo, long logId, Param param) throws AppException;

	public List<ScmItemCostPriceLogDetail2> getItemPrice(ScmItemCostPriceLog scmItemCostPriceLog, Param param) throws AppException;

	public void batchAdd(List<ScmItemCostPriceLogDetail2> scmItemCostPriceLogDetailList, Param param) throws AppException;
}

