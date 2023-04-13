package com.armitage.server.ifbc.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;

public interface ScmAccountingCycleTypeToOrgBiz extends BaseBiz<ScmAccountingCycleTypeToOrg2> {

	public ScmAccountingCycleTypeToOrg2 selectByOrgUnit(String orgUnitNo, Param param) throws AppException;

	public List<ScmAccountingCycleTypeToOrg2> selectByTypeId(long typeId, Param param) throws AppException;
}
