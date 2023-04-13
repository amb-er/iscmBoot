package com.armitage.server.quartz.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.quartz.model.ScmSystemTask;

public interface ScmSystemTaskBiz extends BaseBiz<ScmSystemTask> {
	public List<ScmSystemTask> selectByTask(ScmSystemTask systemTask, Param param) throws AppException;

	public List<ScmSystemTask> selectByTask(ScmSystemTask systemTask, Boolean flag, Param param) throws AppException;
}
