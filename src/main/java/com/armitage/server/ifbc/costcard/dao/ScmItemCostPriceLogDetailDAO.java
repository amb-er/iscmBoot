package com.armitage.server.ifbc.costcard.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail2;

public interface ScmItemCostPriceLogDetailDAO extends BasicDAO<ScmItemCostPriceLogDetail> {

	public void batchAddItemPrice(HashMap<String, Object> map) throws DAOException;

	public List<ScmItemCostPriceLogDetail2> getItemPrice(HashMap<String, Object> map) throws DAOException;

	public void batchAdd(HashMap<String, Object> map) throws DAOException;

	public List<ScmItemCostPriceLogDetail2> getItemPriceByInvStock(HashMap<String, Object> map) throws DAOException;

}

