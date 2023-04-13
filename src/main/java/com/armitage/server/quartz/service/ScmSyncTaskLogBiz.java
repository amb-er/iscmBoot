package com.armitage.server.quartz.service;


import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.quartz.model.ScmSyncTaskLog;

public interface ScmSyncTaskLogBiz extends BaseBiz<ScmSyncTaskLog> {

	public ScmSyncTaskLog manualUpdate(ScmSyncTaskLog syncTaskLog, Param param) throws AppException;
}
