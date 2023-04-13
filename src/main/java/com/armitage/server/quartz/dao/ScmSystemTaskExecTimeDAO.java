
package com.armitage.server.quartz.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;

public interface ScmSystemTaskExecTimeDAO extends BasicDAO<ScmSystemTaskExecTime> {

	public ScmSystemTaskExecTime selectByTaskType(HashMap<String, Object> map) throws DAOException;

}
