
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSupplyPlanEntryDAO")
public class ScmInvSupplyPlanEntryDAOImpl extends BasicDAOImpl<ScmInvSupplyPlanEntry> implements ScmInvSupplyPlanEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvSupplyPlanEntry2> selectByPlanId(HashMap map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPlanId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmInvSupplyRuleEntryDAOImpl.error.selectByRuleId", e);
		}
	}

}
