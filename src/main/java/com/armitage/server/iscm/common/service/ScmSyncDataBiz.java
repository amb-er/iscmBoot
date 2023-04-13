package com.armitage.server.iscm.common.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.ScmSyncData;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;

public interface ScmSyncDataBiz extends BaseBiz<ScmSyncData> {
	public ScmSyncData selectByScmSyncData(ScmSyncData scmSyncData, Param param) throws AppException;

	public ScmSyncData addBySyncTaskInfo(ScmSyncTaskInfo2 ScmSyncTaskInfo, Param param) throws AppException;
	
	public void updateBillNoChangeTime(ScmSyncData scmSyncData, Param param) throws AppException;
}
