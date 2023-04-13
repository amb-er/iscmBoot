
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroupQualifyType2;

public interface ScmIndustryGroupQualifyTypeDAO extends BasicDAO<ScmIndustryGroupQualifyType> {
	public List<ScmIndustryGroupQualifyType2> selectByClassId(HashMap<String, Object> map) throws DAOException;
}
