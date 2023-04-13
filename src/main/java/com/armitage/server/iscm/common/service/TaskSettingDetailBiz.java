package com.armitage.server.iscm.common.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.common.model.TaskSetting2;
import com.armitage.server.iscm.common.model.TaskSettingDetail;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;

public interface TaskSettingDetailBiz extends BaseBiz<TaskSettingDetail> {

	public void updateByTaskSetting(TaskSetting2 taskSetting, Param param) throws AppException;

	public List<TaskSettingDetail> selectBySetId(long setId, Param param) throws AppException;
	
	public List<TaskSettingDetail2> selectByCtrl(String controlUnitNo, Param param) throws AppException;
	public TaskSettingDetail2 selectByTaskObject(String taskObject,String controlUnitNo, Param param) throws AppException;
}
