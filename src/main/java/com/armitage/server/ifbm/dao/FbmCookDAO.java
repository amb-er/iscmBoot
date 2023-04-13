package com.armitage.server.ifbm.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.model.FbmCook;
import com.armitage.server.ifbm.model.FbmCook2;

public interface FbmCookDAO extends BasicDAO<FbmCook> {

	List<FbmCook2> selectByDishId(HashMap<String, Object> map) throws DAOException;

}

