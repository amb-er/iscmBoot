package com.armitage.server.iscm.common.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.model.ScmConfirmRule;

public interface ScmConfirmRuleDAO extends BasicDAO<ScmConfirmRule> {

	public ScmConfirmRule selectByBillType(HashMap<String, Object> map) throws DAOException;

}
