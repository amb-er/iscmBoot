package com.armitage.server.ifbc.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BaseDataDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet2;

public interface ScmPriceUpdSetDAO extends BaseDataDAO<ScmPriceUpdSet> {
	public List<ScmPriceUpdSet2> selectByCtrl(HashMap<String, Object> map) throws DAOException;
}