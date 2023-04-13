package com.armitage.server.ifbc.basedata.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.dao.ScmAccountingCycleDAO;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleBiz;
import com.armitage.server.ifbc.basedata.service.ScmAccountingCycleTypeToOrgBiz;
import org.springframework.stereotype.Service;

@Service("scmAccountingCycleBiz")
public class ScmAccountingCycleBizImpl extends BaseBizImpl<ScmAccountingCycle> implements ScmAccountingCycleBiz {
	private ScmAccountingCycleTypeToOrgBiz scmAccountingCycleTypeToOrgBiz;
	
	public void setScmAccountingCycleTypeToOrgBiz(ScmAccountingCycleTypeToOrgBiz scmAccountingCycleTypeToOrgBiz) {
		this.scmAccountingCycleTypeToOrgBiz = scmAccountingCycleTypeToOrgBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("orgUnitNo", param.getOrgUnitNo());
		map.put("controlUnitNo", param.getControlUnitNo());
		return map;
	}

	@Override
	public List<ScmAccountingCycle> selectByBegAndEndId(String orgUnitNo, long begPeriodId, long endPeriodId,
			Param param) throws AppException {
		ScmAccountingCycleTypeToOrg2 scmAccountingCycleTypeToOrg =scmAccountingCycleTypeToOrgBiz.selectByOrgUnit(orgUnitNo,param);
		if(scmAccountingCycleTypeToOrg==null) {
			throw new AppException("field.ScmAccountingCycleTypeToOrg.notsetorg");
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", scmAccountingCycleTypeToOrg.getTypeId());
		map.put("begPeriodId", begPeriodId);
		map.put("endPeriodId", endPeriodId);
		return ((ScmAccountingCycleDAO)dao).selectByBegAndEndId(map);
	}

}
