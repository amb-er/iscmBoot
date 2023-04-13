package com.armitage.server.ifbc.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;

public interface ScmResOrgUnitMapBiz extends BaseBiz<ScmResOrgUnitMap> {

	public ScmResOrgUnitMap selectByResOrgUnit(String resOrgUnitNo,Param param) throws AppException; 
}
