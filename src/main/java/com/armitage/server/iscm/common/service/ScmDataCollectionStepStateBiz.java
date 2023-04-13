package com.armitage.server.iscm.common.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;

public interface ScmDataCollectionStepStateBiz extends BaseBiz<ScmDataCollectionStepState2> {

	public List<ScmDataCollectionStepState2> findAsynStep(String orgUnitNo,String category,String reqNo, Param param) throws AppException;

	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(String orgUnitNo, String reqNo, long stepId, Param param) throws AppException;

	public ScmDataCollectionStepState2 updateByAsynProcessed(ScmDataCollectionStepState2 scmFbcRptData,String state, String msgInfo, Param param)throws AppException;

}
