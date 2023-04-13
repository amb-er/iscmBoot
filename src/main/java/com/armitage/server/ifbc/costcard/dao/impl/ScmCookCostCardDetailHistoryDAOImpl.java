package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetailHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCookCostCardDetailHistoryDAO")
public class ScmCookCostCardDetailHistoryDAOImpl extends BasicDAOImpl<ScmCookCostCardDetailHistory> implements ScmCookCostCardDetailHistoryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteByEffectDay(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".deleteByEffectDay",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDetailHistoryDAOImpl.error.deleteTodayData", e);
		}
	}

	@Override
	public void updateLastByCostCard(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateLastByCostCard",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDetailHistoryDAOImpl.error.updateLastByCostCard", e);
		}
	}

	@Override
	public void addByCostCard(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByCostCard",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDetailHistoryDAOImpl.error.addByCostCard", e);
		}
	}

	@Override
	public List<ScmCookCostCardDetailHistory> selectByEffectDay(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByEffectDay", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDetailHistoryDAOImpl.error.selectByEffectDay");
        }
	}

	@Override
	public void updateLastByCostCard2(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateLastByCostCard2",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDetailHistoryDAOImpl.error.updateLastByCostCard2", e);
		}
	}

}