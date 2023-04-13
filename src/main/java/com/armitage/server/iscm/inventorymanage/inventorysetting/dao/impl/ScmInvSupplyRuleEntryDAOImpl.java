
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSupplyRuleEntryDAO")
public class ScmInvSupplyRuleEntryDAOImpl extends BasicDAOImpl<ScmInvSupplyRuleEntry> implements ScmInvSupplyRuleEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvSupplyRuleEntry2> selectByRuleId(HashMap map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByRuleId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleEntryDAOImpl.error.selectByRuleId", e);
		}
	}

	@Override
	public void deleteByRuleIds(Long ruleId, Param param) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.selectList(simpleName + ".deleteByRuleIds", ruleId);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleEntryDAOImpl.error.deleteByRuleIds", e);
		}
	}

	@Override
	public List<Long> selectEntryIdsByRuleId(long ruleId, Param createParam) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectEntryIdsByRuleId", ruleId);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyRuleEntryDAOImpl.error.selectEntryIdsByRuleId", e);
		}
	}

}
