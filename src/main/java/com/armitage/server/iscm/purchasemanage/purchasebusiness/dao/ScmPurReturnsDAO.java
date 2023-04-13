
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;

public interface ScmPurReturnsDAO extends BasicDAO<ScmPurReturns> {
	public ScmPurReturns selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReturns2> selectByPurInwareHouse(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReturns2> selectByPurInwareHouseReturn(HashMap<String, Object> map) throws DAOException;
	
}
