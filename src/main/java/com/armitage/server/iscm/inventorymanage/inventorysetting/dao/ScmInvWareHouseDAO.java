package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;

public interface ScmInvWareHouseDAO extends BasicDAO<ScmInvWareHouse> {

	public ScmInvWareHouse selectMaxId() throws DAOException;
	public List<ScmInvWareHouse> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException;
	public ScmInvWareHouse selectByWhNo(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvWareHouse> selectByWhName(HashMap<String, Object> map) throws DAOException;
}
