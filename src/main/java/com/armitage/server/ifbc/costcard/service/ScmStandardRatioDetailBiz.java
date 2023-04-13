package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioDetail;

public interface ScmStandardRatioDetailBiz extends BaseBiz<ScmStandardRatioDetail> {

	public void deleteByRateId(long rateId, Param param) throws AppException;

	public List<ScmStandardRatioDetail> selectByRateId(long rateId, Param param) throws AppException;

}
