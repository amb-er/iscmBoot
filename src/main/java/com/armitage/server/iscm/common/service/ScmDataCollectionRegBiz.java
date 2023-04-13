package com.armitage.server.iscm.common.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg2;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;

public interface ScmDataCollectionRegBiz extends BaseBiz<ScmDataCollectionReg> {

	public CommonBean findAsynStep(ScmDataCollectionReg2 scmDataCollectionReg, Param param) throws AppException;

	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(ScmDataCollectionStepState2 scmDataCollectionStepState, Param param) throws AppException;

}
