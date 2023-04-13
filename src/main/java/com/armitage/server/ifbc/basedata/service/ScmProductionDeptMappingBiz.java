package com.armitage.server.ifbc.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;

public interface ScmProductionDeptMappingBiz extends BaseBiz<ScmProductionDeptMapping2> {

	public List<ScmProductionDeptMapping2> selectByProductDeptId(long productDeptId, Param param) throws AppException;

	public void deleteByProductDeptId(long productDeptId, Param param) throws AppException;

	public List<ScmProductionDeptMapping2> selectByOrgUnit(String orgUnitNo, Param param) throws AppException;
	
	public ScmProductionDeptMapping2 findRepeat(String orgUnitNo, ScmProductionDeptMapping2 scmProductionDeptMapping, Param param) throws AppException;
	
}
