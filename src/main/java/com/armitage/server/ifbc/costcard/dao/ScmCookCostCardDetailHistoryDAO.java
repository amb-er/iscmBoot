package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetailHistory;

public interface ScmCookCostCardDetailHistoryDAO extends BasicDAO<ScmCookCostCardDetailHistory> {

	public void deleteByEffectDay(HashMap<String, Object> map) throws DAOException;

	public void updateLastByCostCard(HashMap<String, Object> map) throws DAOException;

	public void addByCostCard(HashMap<String, Object> map) throws DAOException;

	public List<ScmCookCostCardDetailHistory> selectByEffectDay(HashMap<String, Object> map) throws DAOException;

	public void updateLastByCostCard2(HashMap<String, Object> map) throws DAOException;

}