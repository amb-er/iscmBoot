package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurOrderEntryDAO")
public class ScmPurOrderEntryDAOImpl extends BasicDAOImpl<ScmPurOrderEntry> implements ScmPurOrderEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPoId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectByPoId", e);
		}
	}

	@Override
	public void updateRowStatusByPoId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPoId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.updateRowStatusByPoId", e);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoIdAndSaleIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPoIdAndSaleIssueBill",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectByPoIdAndSaleIssueBill", e);
		}
	}

	@Override
	public void writeBackInvQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".writeBackInvQty", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.writeBackInvQty", e);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPoIdAndOtherIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPoIdAndOtherIssueBill",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectByPoIdAndOtherIssueBill", e);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectStatusByIds(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectStatusByIds",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectStatusByIds", e);
		}
	}

	@Override
	public void updateRowStatusByLineId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByLineId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.updateRowStatusByLineId", e);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPriceId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPriceId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectByPriceId", e);
		}
	}

	@Override
	public void updatePurOrderPriceBillIdByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updatePurOrderPriceBillIdByPmId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.updatePurOrderPriceBillIdByPmId", e);
		}
	}

	@Override
	public int generateAdd(ScmPurOrderEntry2 scmPurOrderEntry) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".generateAdd", scmPurOrderEntry);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.generateAdd", e);
		}
	}

	@Override
	public List<ScmPurOrderEntry2> selectByPvId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPvId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAOImpl.error.selectByPvId", e);
		}
	}
}
