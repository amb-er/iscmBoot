package com.armitage.server.ifbc.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;

public interface ScmAccountingCycleBiz extends BaseBiz<ScmAccountingCycle> {
	public List<ScmAccountingCycle> selectByBegAndEndId(String orgUnitNo,long begPeriodId,long endPeriodId,Param param) throws AppException;
}
