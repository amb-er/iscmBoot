package com.armitage.server.ifbc.rptdata.service.impl;

import java.util.Date;
import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.rptdata.dao.ScmCookStdCostInfoDAO;
import com.armitage.server.ifbc.rptdata.model.ScmCookStdCostInfo;
import com.armitage.server.ifbc.rptdata.service.ScmCookStdCostInfoBiz;
import org.springframework.stereotype.Service;

@Service("scmCookStdCostInfoBiz")
public class ScmCookStdCostInfoBizImpl extends BaseBizImpl<ScmCookStdCostInfo> implements ScmCookStdCostInfoBiz {
	private ScmAccountingCycleBiz scmAccountingCycleBiz;

	public void setScmAccountingCycleBiz(ScmAccountingCycleBiz scmAccountingCycleBiz) {
		this.scmAccountingCycleBiz = scmAccountingCycleBiz;
	}

	@Override
	public void calcRptData(String orgUnitNo, long begPeriodId, long endPeriodId, Param param) throws AppException {
		ScmAccountingCycle begScmAccountingCycle = scmAccountingCycleBiz.selectDirect(begPeriodId, param);
		ScmAccountingCycle endScmAccountingCycle = scmAccountingCycleBiz.selectDirect(endPeriodId, param);
		if(begScmAccountingCycle==null || endScmAccountingCycle==null) {
			throw new AppException("field.ScmAccountingCycle.error.wrongperiod");
		}
		Date begDate = begScmAccountingCycle.getStartDate();
		Date endDate = endScmAccountingCycle.getEndDate();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmCookStdCostInfoDAO)dao).batchDetele(map);
		((ScmCookStdCostInfoDAO)dao).batchAdd(map);
	}

	@Override
	public void calcRptDataByTask(String orgUnitNo, Date begDate, Date endDate, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		((ScmCookStdCostInfoDAO)dao).batchDetele(map);
		((ScmCookStdCostInfoDAO)dao).batchAdd(map);
	}

}
