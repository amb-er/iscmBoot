package com.armitage.server.ifbm.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.model.FbmSellCookDetail;
import com.armitage.server.ifbm.model.FbmSellDetail;

public interface FbmSellDetailDAO extends BasicDAO<FbmSellDetail> {

	public List<FbmSellDetail> selectDishSellDetail(HashMap<String, Object> map) throws DAOException;

	public List<FbmSellCookDetail> selectCookSellDetail(HashMap<String, Object> map) throws DAOException;

}

