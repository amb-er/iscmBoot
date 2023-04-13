package com.armitage.server.iscm.common.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.ScmSyncData;

public interface ScmSyncDataDAO extends BasicDAO<ScmSyncData> {
	public ScmSyncData selectByScmSyncData(HashMap<String, Object> map) throws DAOException;
	public int updateBillNoChangeTime(ScmSyncData scmSyncData) throws DAOException;
}
