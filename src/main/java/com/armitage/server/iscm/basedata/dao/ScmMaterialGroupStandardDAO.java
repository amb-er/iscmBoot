
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupStandard2;

public interface ScmMaterialGroupStandardDAO extends BasicDAO<ScmMaterialGroupStandard> {

	public List<ScmMaterialGroupStandard2> selectSubsidiaryTypeByItem(HashMap<String, Object> map) throws DAOException;

}
