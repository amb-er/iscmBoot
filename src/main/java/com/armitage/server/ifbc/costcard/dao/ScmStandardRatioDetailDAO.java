package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioDetail;

public interface ScmStandardRatioDetailDAO extends BasicDAO<ScmStandardRatioDetail> {

	public List<ScmStandardRatioDetail> selectByRateId(HashMap<String, Object> map) throws DAOException;

}
