package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;

public interface ScmPurMarketPriceEntryDAO extends BasicDAO<ScmPurMarketPriceEntry> {
	public List<ScmPurMarketPriceEntry2> selectByPiId(HashMap<String, Object> map) throws DAOException;
}
