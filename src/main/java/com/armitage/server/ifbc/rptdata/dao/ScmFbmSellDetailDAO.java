package com.armitage.server.ifbc.rptdata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.model.ScmFbmSellDetail;

public interface ScmFbmSellDetailDAO extends BasicDAO<ScmFbmSellDetail> {

	public int batchAdd(HashMap<String, Object> map) throws DAOException;

	public void delRptData(HashMap<String, Object> map) throws DAOException;

}