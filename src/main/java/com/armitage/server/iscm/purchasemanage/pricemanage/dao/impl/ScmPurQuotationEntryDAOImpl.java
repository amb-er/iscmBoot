package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurQuotationEntryDAO")
public class ScmPurQuotationEntryDAOImpl extends BasicDAOImpl<ScmPurQuotationEntry> implements ScmPurQuotationEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurQuotationEntry2> selectByPqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPqId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurQuotationEntryDAOImpl.error.selectByPqId", e);
		}
	}
	
	@Override
	public void updateRowStatusByPqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurQuotationEntryDAOImpl.error.updateRowStatusByPqId", e);
		}
	}

	@Override
	public ScmPurQuotationEntry2 selectTaxRateByPqId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectTaxRateByPqId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurQuotationEntryDAOImpl.error.selectTaxRateByPqId", e);
		}
	}
}
