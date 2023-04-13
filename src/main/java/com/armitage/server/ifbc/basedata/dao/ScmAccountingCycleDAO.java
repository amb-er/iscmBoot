package com.armitage.server.ifbc.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;

public interface ScmAccountingCycleDAO extends BasicDAO<ScmAccountingCycle> {

	public List<ScmAccountingCycle> selectByBegAndEndId(HashMap<String, Object> map) throws DAOException;

}