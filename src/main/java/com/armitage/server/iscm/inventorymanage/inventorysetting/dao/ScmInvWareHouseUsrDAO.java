package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr2;

public interface ScmInvWareHouseUsrDAO extends BasicDAO<ScmInvWareHouseUsr> {

	public List<ScmInvWareHouseUsr2> selectByWareHouseId(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvWareHouseUsr2> selectByUsrCode(HashMap<String, Object> map) throws DAOException;
}

