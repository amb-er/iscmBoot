
package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationPlanDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurQuotationPlanDAO")
public class ScmPurQuotationPlanDAOImpl extends BasicDAOImpl<ScmPurQuotationPlan> implements ScmPurQuotationPlanDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurQuotationPlan selectMaxIdByDate(HashMap<String,Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurQuotationPlanDAOImpl.error.selectMaxIdByDate");
		}
	}

}
