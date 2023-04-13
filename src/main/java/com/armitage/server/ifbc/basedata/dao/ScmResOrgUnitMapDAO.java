package com.armitage.server.ifbc.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;

public interface ScmResOrgUnitMapDAO extends BasicDAO<ScmResOrgUnitMap> {

	public ScmResOrgUnitMap selectByResOrgUnit(HashMap<String, Object> map) throws DAOException;

}