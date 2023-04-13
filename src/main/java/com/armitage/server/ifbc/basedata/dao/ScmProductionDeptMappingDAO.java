package com.armitage.server.ifbc.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;


public interface ScmProductionDeptMappingDAO extends BasicDAO<ScmProductionDeptMapping> {

	public List<ScmProductionDeptMapping2> selectByProductDeptId(HashMap<String, Object> map) throws DAOException;

	public List<ScmProductionDeptMapping2> selectByOrgUnit(HashMap<String, Object> map) throws DAOException;

	public List<ScmProductionDeptMapping2> findRepeat(HashMap<String, Object> map) throws DAOException;
	
}