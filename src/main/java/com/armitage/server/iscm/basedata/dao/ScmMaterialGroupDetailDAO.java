
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupDetail2;

public interface ScmMaterialGroupDetailDAO extends BasicDAO<ScmMaterialGroupDetail> {
	public void deleteByItemIdOrGroupId(HashMap<String, Object> map) throws DAOException;

	public List<ScmMaterialGroupDetail2> selectByItemId(HashMap<String, Object> map) throws DAOException;
}
