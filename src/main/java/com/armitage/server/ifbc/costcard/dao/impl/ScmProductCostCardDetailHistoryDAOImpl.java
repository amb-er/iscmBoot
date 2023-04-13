
package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDetailHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmProductCostCardDetailHistoryDAO")
public class ScmProductCostCardDetailHistoryDAOImpl extends BasicDAOImpl<ScmProductCostCardDetailHistory> implements ScmProductCostCardDetailHistoryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmProductCostCardDetailHistory> selectByProductIdAndDate(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByProductIdAndDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.selectByProductIdAndDate");
        }
	}

	@Override
	public List<ScmProductCostCardDetailHistory> selectByEffectDay(HashMap<String, Object> map, Param param)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByEffectDay", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.selectByEffectDay");
        }
	}

	@Override
	public void deleteByEffectDay(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".deleteByEffectDay",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.deleteByEffectDay", e);
		}
	}

	@Override
	public void updateLastByCostCard(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateLastByCostCard",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.updateLastByCostCard", e);
		}
	}

	@Override
	public void addByCostCard(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByCostCard",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.addByCostCard", e);
		}
	}

	@Override
	public List<ScmProductCostCardDetailHistory> selectByEffectDayAndDetailItemId(HashMap<String, Object> map,
			Param param) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByEffectDayAndDetailItemId", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.selectByEffectDayAndDetailItemId");
        }
	}

	@Override
	public void deleteByEffectDayAndDetailItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".deleteByEffectDayAndDetailItemId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.deleteByEffectDayAndDetailItemId", e);
		}
	}

	@Override
	public void updateByAuditCostCardAndDetailItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateByAuditCostCardAndDetailItemId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailHistoryDAOImpl.error.updateByAuditCostCardAndDetailItemId", e);
		}
	}

}
