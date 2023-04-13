package com.armitage.server.ifbc.rptdata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.model.ScmCookStdCostInfo;

public interface ScmCookStdCostInfoDAO extends BasicDAO<ScmCookStdCostInfo> {

	public void batchAdd(HashMap<String, Object> map) throws DAOException;

	public void batchDetele(HashMap<String, Object> map) throws DAOException;

}
