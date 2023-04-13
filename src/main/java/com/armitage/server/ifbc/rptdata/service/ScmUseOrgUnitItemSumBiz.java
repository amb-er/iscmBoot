package com.armitage.server.ifbc.rptdata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.rptdata.model.ScmUseOrgUnitItemSum;

public interface ScmUseOrgUnitItemSumBiz extends BaseBiz<ScmUseOrgUnitItemSum> {

	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException;
	public void batchAdd(List<ScmUseOrgUnitItemSum> scmUseOrgUnitItemSumList, Param param) throws AppException;
	public void delRptData(String orgUnitNo,long periodId, Param param) throws AppException;
}
