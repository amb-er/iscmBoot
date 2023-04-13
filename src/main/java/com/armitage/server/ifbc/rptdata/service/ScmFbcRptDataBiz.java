package com.armitage.server.ifbc.rptdata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.rptdata.model.ScmFbcRptData;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;

public interface ScmFbcRptDataBiz extends BaseBiz<ScmFbcRptData> {

	public CommonBean calcRptData(ScmFbcRptData scmFbcRptData,ScmDataCollectionStepState2 stepState, Param param) throws AppException;
}
