package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardPrice;

public interface ScmCookCostCardPriceDAO extends BasicDAO<ScmCookCostCardPrice> {

	public ScmCookCostCardPrice selectCurrPriceByCookId(HashMap<String, Object> map) throws DAOException;
	public int batchAddCostPrice(HashMap<String, Object> map) throws DAOException;
	public ScmCookCostCardPrice selectByScmCookCostCardPrice(HashMap<String, Object> map) throws DAOException;
	public int batchAddSalePrice(HashMap<String, Object> map) throws DAOException;
	public int addPeriodSalePrice(HashMap<String, Object> map) throws DAOException;
}
