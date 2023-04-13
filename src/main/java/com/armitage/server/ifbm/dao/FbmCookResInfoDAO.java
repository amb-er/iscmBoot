package com.armitage.server.ifbm.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbm.model.FbmCookResInfo;
import com.armitage.server.ifbm.model.FbmCookResInfo2;

public interface FbmCookResInfoDAO extends BasicDAO<FbmCookResInfo> {
	public List<FbmCookResInfo2> selectCookSalePrice(HashMap<String, Object> map) throws DAOException;

}
