package com.armitage.server.iscm.purchasemanage.pricemanage.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;

public interface ScmPurMarketPriceDAO extends BasicDAO<ScmPurMarketPrice> {

	public ScmPurMarketPrice selectMaxIdByDate(HashMap<String,Object> map) throws DAOException;

	public List<ScmPurMarketPrice2> selectRecentPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurMarketPrice2> selectItemsRecentPrice(HashMap<String, Object> map)throws DAOException;

}
