
package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurPriceEntryDAO")
public class ScmPurPriceEntryDAOImpl extends BasicDAOImpl<ScmPurPriceEntry> implements ScmPurPriceEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurPriceEntry2> selectByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPmId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAOImpl.error.selectByPmId", e);
		}
	}
	@Override
	public void updateRowStatusByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPmId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAOImpl.error.updateRowStatusByPmId", e);
		}
	}

	@Override
	public void updateVendorQuotation(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateVendorQuotation", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAOImpl.error.updateVendorQuotation", e);
		}
	}

	@Override
	public ScmPurPriceEntry2 selectTaxRateByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectTaxRateByPmId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.pricemanage.dao.ScmPurPriceEntryDAOImpl.error.selectTaxRateByPmId", e);
		}
	}

}
