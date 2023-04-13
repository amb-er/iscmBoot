package com.armitage.server.quartz.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.quartz.dao.ScmSystemTaskExecTimeDAO;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;
import com.armitage.server.quartz.service.ScmSystemTaskExecTimeBiz;
import org.springframework.stereotype.Service;

@Service("scmSystemTaskExecTimeBiz")
public class ScmSystemTaskExecTimeBizImpl extends BaseBizImpl<ScmSystemTaskExecTime> implements ScmSystemTaskExecTimeBiz {

	@Override
	public ScmSystemTaskExecTime selectByTaskType(String orgUnitNo, String taskType, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("taskType", taskType);
		return ((ScmSystemTaskExecTimeDAO)dao).selectByTaskType(map);
	}
}
