package com.armitage.server.iscm.common.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.TaskSettingDetail;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;

public interface TaskSettingDetailDAO extends BasicDAO<TaskSettingDetail> {

	public List<TaskSettingDetail> selectBySetId(HashMap<String, Object> map) throws DAOException;

	public List<TaskSettingDetail2> selectByCtrl(HashMap<String, Object> map) throws DAOException;

	public TaskSettingDetail2 selectByTaskObject(HashMap<String, Object> map) throws DAOException;
}
