
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;

public interface ScmMeasureUnitDAO extends BasicDAO<ScmMeasureUnit> {

	public ScmMeasureUnit selectByUnitNo(HashMap<String, Object> map) throws DAOException;

}
