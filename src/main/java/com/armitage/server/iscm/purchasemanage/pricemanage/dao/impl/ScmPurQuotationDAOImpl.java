package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurQuotationDAO")
public class ScmPurQuotationDAOImpl extends BasicDAOImpl<ScmPurQuotation> implements ScmPurQuotationDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurQuotation2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurQuotationDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPrice");
		}
	}

	@Override
	public List<ScmPurQuotation2> selectRecentPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectRecentPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectRecentPrice");
		}
	}

	@Override
	public List<ScmMaterialPrice> getLastPrice(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getLastPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getLastPrice");
		}
	}

	@Override
	public List<ScmPurQuotation2> selectItemsRecentPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectItemsRecentPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.selectItemsRecentPrice");
		}
	}

	@Override
	public List<ScmMaterialPrice> getPriceByVendorIds(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getPriceByVendorIds", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurPriceDAOImpl.error.getPriceByVendorIds");
		}
	}
}
