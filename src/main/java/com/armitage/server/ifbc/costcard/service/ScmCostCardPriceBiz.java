package com.armitage.server.ifbc.costcard.service;


import java.util.Date;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCardPrice;

public interface ScmCostCardPriceBiz extends BaseBiz<ScmCostCardPrice> {
	public boolean modifySalePrice(String orgUnitNo,Param param) throws AppException;
	public ScmCostCardPrice selectByScmCostCardPrice(ScmCostCardPrice scmCostCardPrice, Param param) throws AppException;
	public int updateCostPrice(String orgUnitNo, Param param) throws AppException;
	public void getSalePrice(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException;
	public void calcCostPrice(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException;
	public void getSalePriceByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException;
	public void calcCostPriceByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException;
}

