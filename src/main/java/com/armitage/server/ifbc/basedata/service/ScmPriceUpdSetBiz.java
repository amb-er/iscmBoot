package com.armitage.server.ifbc.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet2;

public interface ScmPriceUpdSetBiz extends BaseBiz<ScmPriceUpdSet> {

	public ScmPriceUpdSet selectByOrgUnit(String orgUnitNo) throws AppException;
	
	public List<ScmPriceUpdSet2> selectByCtrl(String controlUnitNo, Param param) throws AppException;
}
