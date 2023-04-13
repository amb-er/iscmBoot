package com.armitage.server.iscm.basedata.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;

public interface ScmIndustryGroupDAO extends BasicDAO<ScmIndustryGroup> {
	public List<ScmIndustryGroup> selectByTypeId(HashMap<String, Object> map) throws DAOException;
	public ScmIndustryGroup selectByClassCode(HashMap<String, Object> map) throws DAOException;
}

