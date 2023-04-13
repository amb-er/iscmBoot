package com.armitage.server.ifbc.rptdata.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.rptdata.model.ScmFbmSellDetail;

public interface ScmFbmSellDetailBiz extends BaseBiz<ScmFbmSellDetail> {

	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException;
	public void batchAdd(List<ScmFbmSellDetail> scmFbmSellDetailList, Param param) throws AppException;
	public void delRptData(Date begDate, Date endDate, Param param) throws AppException;
	public void calcRptDataByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException;
}
