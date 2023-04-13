package com.armitage.server.iscm.inventorymanage.countbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterial2;

public interface ScmInvCountingListMaterialDAO extends BasicDAO<ScmInvCountingListMaterial> {
	public List<ScmInvCountingListMaterial2> selectByTaskId(HashMap<String, Object> map) throws DAOException;
	public void deleteByTaskId(HashMap<String, Object> map) throws DAOException;
}

