package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign2;

public interface ScmPurPriceAssignDAO extends BasicDAO<ScmPurPriceAssign> {
	public List<ScmPurPriceAssign2> selectByPmId(HashMap<String, Object> map) throws DAOException;
	
}
