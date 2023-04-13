package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign2;

public interface ScmInvMaterialAssignDAO extends BasicDAO<ScmInvMaterialAssign> {
	public List<ScmInvMaterialAssign2> selectMaterialAssign(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialAssign2> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException;
}

