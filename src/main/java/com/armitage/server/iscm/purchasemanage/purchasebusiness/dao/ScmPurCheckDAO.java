package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;


import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurCheck2;


public interface ScmPurCheckDAO extends BasicDAO<ScmPurCheck> {

	public ScmPurCheck2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurCheck2> selectByPoId(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurCheck2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurCheck2> selectByOtherIssueBill(HashMap<String, Object> map) throws DAOException;
}

