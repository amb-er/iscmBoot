package com.armitage.server.quartz.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;

public interface ScmSystemTaskExecTimeBiz extends BaseBiz<ScmSystemTaskExecTime> {
	public ScmSystemTaskExecTime selectByTaskType(String orgUnitNo,String taskType,Param param) throws AppException;

}
