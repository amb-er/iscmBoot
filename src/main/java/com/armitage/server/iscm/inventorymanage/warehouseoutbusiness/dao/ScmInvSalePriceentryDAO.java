package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSalePriceentry2;

public interface ScmInvSalePriceentryDAO extends BasicDAO<ScmInvSalePriceentry>{
	
	public void deleteByPmId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvSalePriceentry2> selectByPmId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPmId(HashMap<String, Object> map) throws DAOException;
}
