package com.armitage.server.ifbm.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.model.FbmDishPrc;
import com.armitage.server.ifbm.model.FbmDishPrc2;

public interface FbmDishPrcDAO extends BasicDAO<FbmDishPrc> {

	public FbmDishPrc2 selectByDishAndSpecId(HashMap<String, Object> map) throws DAOException;
	public FbmDishPrc2 selectByDishSpecCode(HashMap<String, Object> map) throws DAOException;
	public List<FbmDishPrc2> selectDishSalePrice(HashMap<String, Object> map) throws DAOException;

}
