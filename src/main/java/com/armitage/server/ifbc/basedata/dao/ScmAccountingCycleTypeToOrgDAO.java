package com.armitage.server.ifbc.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BaseDataDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;

public interface ScmAccountingCycleTypeToOrgDAO extends BaseDataDAO<ScmAccountingCycleTypeToOrg> {

	public ScmAccountingCycleTypeToOrg2 selectByOrgUnit(HashMap<String, Object> map) throws DAOException;

}