
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSupplyPlanDAO")
public class ScmInvSupplyPlanDAOImpl extends BasicDAOImpl<ScmInvSupplyPlan> implements ScmInvSupplyPlanDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvSupplyPlan2> selectByRuleId(long id) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByRuleId", id);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanDAOImpl.error.selectByRuleId", e);
		}
	}

	@Override
	public List<ScmMaterial2> SelectScmMaterialByItemIds(HashMap<String, Object> map) throws DAOException  {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectScmMaterialByItemIds", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.inventorymanage.inventorysetting.dao.ScmInvSupplyPlanDAOImpl.error.selectByRuleId", e);
		}
	}
}
