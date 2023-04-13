package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationPlanEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurQuotationPlanEntryDAO")
public class ScmPurQuotationPlanEntryDAOImpl extends BasicDAOImpl<ScmPurQuotationPlanEntry> implements ScmPurQuotationPlanEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurQuotationPlanEntry2> selectByPlanId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPlanId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurQuotationPlanEntryDAOImpl.error.selectByPlanId", e);
		}
	}

	@Override
	public List<ScmPurQuotationPlanEntry2> selectPurChasIngQuery(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectPurChasIngQuery",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurQuotationPlanEntryDAOImpl.error.selectPurChasIngQuery", e);
		}
	}

	
}
