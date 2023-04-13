
package com.armitage.server.iscm.inventorymanage.countbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;

public interface ScmInvCountingListMaterialGroupDAO extends BasicDAO<ScmInvCountingListMaterialGroup> {

	public void deleteByTaskId(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvCountingListMaterialGroup2> selectByTaskId(HashMap<String, Object> map) throws DAOException;

}
