
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;

public interface ScmMaterialUnitRelationDAO extends BasicDAO<ScmMaterialUnitRelation> {

	public List<ScmMaterialUnitRelation2> selectByItemOrUnit(HashMap<String, Object> map) throws DAOException;
	public ScmMaterialUnitRelation2 selectByItemAndUnit(HashMap<String, Object> map) throws DAOException;
	public ScmMaterialUnitRelation2 selectBaseUnitByItem(HashMap<String, Object> map) throws DAOException;

}
