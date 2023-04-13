package com.armitage.server.iscm.common.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;

public interface ScmSyncTaskInfoBiz extends BaseBiz<ScmSyncTaskInfo2> {
	public ScmSyncTaskInfo2 selectByScmSyncTaskInfo(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException;

	public void disableTask(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException;

	public List queryForBatchTask(ScmSyncTaskInfo2 scmSyncTaskInfo, Param param) throws AppException;

	public void generateSyncData(ScmSyncTaskInfo2 scmSyncTaskInfo, List list, Param param) throws AppException;
}
