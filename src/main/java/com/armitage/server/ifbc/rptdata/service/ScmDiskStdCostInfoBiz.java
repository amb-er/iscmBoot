package com.armitage.server.ifbc.rptdata.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.rptdata.model.ScmDiskStdCostInfo;

public interface ScmDiskStdCostInfoBiz extends BaseBiz<ScmDiskStdCostInfo> {
	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException;
	
	public List<ScmDiskStdCostInfo> selectByResOrgUnitNos(String resOrgUnitNos, Date begDate, Date endDate, Param param) throws AppException;
	
	public void generateCostConsume(Map<String,String> useOrgUnitNoMap, String resOrgUnitNos, Date begDate, Date endDate, Param param) throws AppException;
	
	public void calcRptDataByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException;
}
