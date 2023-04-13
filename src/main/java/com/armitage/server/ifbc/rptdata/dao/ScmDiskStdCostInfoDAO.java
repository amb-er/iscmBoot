package com.armitage.server.ifbc.rptdata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.model.ScmDiskStdCostInfo;

public interface ScmDiskStdCostInfoDAO extends BasicDAO<ScmDiskStdCostInfo> {

	public void batchAdd(HashMap<String, Object> map) throws DAOException;

	public void batchDelete(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmDiskStdCostInfo> selectByResOrgUnitNos(HashMap<String, Object> map) throws DAOException;

}
