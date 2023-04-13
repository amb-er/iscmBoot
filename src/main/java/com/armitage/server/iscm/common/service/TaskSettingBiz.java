package com.armitage.server.iscm.common.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.TaskSetting2;

public interface TaskSettingBiz extends BaseBiz<TaskSetting2> {
	public TaskSetting2 selectByTaskId(long taskId,Param param) throws AppException;
}
