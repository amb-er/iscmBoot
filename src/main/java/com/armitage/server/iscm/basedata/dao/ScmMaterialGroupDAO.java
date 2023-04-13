
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;

public interface ScmMaterialGroupDAO extends BasicDAO<ScmMaterialGroup> {
	public List<ScmMaterialGroup> findChild(HashMap<String, Object> map) throws DAOException;
	public ScmMaterialGroup selectByClassCode(HashMap<String, Object> map) throws DAOException;
	public ScmMaterialGroup selectByItemId(HashMap<String, Object> map) throws DAOException;
	public List<ScmMaterialGroup2> queryDetailClassList(HashMap<String, Object> map) throws DAOException;
}
