package com.armitage.server.ifbc.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;

public interface ScmProductionDeptBiz extends BaseBiz<ScmProductionDept> {

	public ScmProductionDept findRepeat(String orgUnitNo, ScmProductionDept scmProductionDept) throws AppException;
}
