package com.armitage.server.iscm.common.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.TaskSetting;
import com.armitage.server.iscm.common.model.TaskSetting2;

public interface TaskSettingDAO extends BasicDAO<TaskSetting> {

	public TaskSetting2 selectByTaskId(HashMap<String, Object> map) throws DAOException;
}
