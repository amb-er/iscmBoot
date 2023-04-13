package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmCostCategory;

public interface ScmCostCategoryDAO extends BasicDAO<ScmCostCategory> {

	public ScmCostCategory selectByCode(HashMap<String, Object> map) throws DAOException;

}
