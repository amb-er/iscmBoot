package com.armitage.server.iscm.common.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;

public interface ScmSyncTaskInfoDAO extends BasicDAO<ScmSyncTaskInfo> {
	public ScmSyncTaskInfo2 selectByScmSyncTaskInfo(HashMap<String, Object> map) throws DAOException;
}
