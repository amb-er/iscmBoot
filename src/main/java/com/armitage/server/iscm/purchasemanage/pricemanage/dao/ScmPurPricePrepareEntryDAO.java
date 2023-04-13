package com.armitage.server.iscm.purchasemanage.pricemanage.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntry2;

public interface ScmPurPricePrepareEntryDAO extends BasicDAO<ScmPurPricePrepareEntry> {
	public List<ScmPurPricePrepareEntry2> selectByPmId(HashMap<String, Object> map) throws DAOException;
}

