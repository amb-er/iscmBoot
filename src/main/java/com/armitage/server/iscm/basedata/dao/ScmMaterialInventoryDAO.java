
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;

public interface ScmMaterialInventoryDAO extends BasicDAO<ScmMaterialInventory> {
	public ScmMaterialInventory2 selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException;

	public ScmMaterialInventory2 selectByItemId(HashMap<String, Object> map) throws DAOException;
}
