
package com.armitage.server.ifbc.costcard.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;

public interface ScmProductCostCardDetailHistoryDAO extends BasicDAO<ScmProductCostCardDetailHistory> {

	public List<ScmProductCostCardDetailHistory> selectByProductIdAndDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmProductCostCardDetailHistory> selectByEffectDay(HashMap<String, Object> map, Param param) throws DAOException;

	public void deleteByEffectDay(HashMap<String, Object> map) throws DAOException;

	public void updateLastByCostCard(HashMap<String, Object> map) throws DAOException;

	public void addByCostCard(HashMap<String, Object> map) throws DAOException;

	public List<ScmProductCostCardDetailHistory> selectByEffectDayAndDetailItemId(HashMap<String, Object> map,Param param) throws DAOException;

	public void deleteByEffectDayAndDetailItemId(HashMap<String, Object> map) throws DAOException;

	public void updateByAuditCostCardAndDetailItemId(HashMap<String, Object> map) throws DAOException;

}
