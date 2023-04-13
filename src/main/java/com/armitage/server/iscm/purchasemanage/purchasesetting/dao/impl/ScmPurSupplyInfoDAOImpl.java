
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplyInfoDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurSupplyInfoDAO")
public class ScmPurSupplyInfoDAOImpl extends BasicDAOImpl<ScmPurSupplyInfo> implements ScmPurSupplyInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurSupplyInfo2> findVendor(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.findVendor");
		}
	}

	@Override
	public ScmPurSupplyInfo2 getSupplyInfoByItem(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getSupplyInfoByItem", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.getSupplyInfoByItem");
		}
	}

	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItems(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getSupplyInfoByItems", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.getSupplyInfoByItems");
		}
	}

	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItemList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".getSupplyInfoByItemList",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.getSupplyInfoByItem");
		}
	}

	@Override
	public int addBySupplierSource(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addBySupplierSource", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.addBySupplierSource", e);
		}
	}

	@Override
	public ScmPurSupplyInfo2 getSupplyInfoByItemAndVendor(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getSupplyInfoByItemAndVendor", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.getSupplyInfoByItemAndVendor");
		}
	}

	@Override
	public List<ScmPurSupplyInfo2> getSupplyInfoByItemSAndVendorS(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getSupplyInfoByItemSAndVendorS", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyInfoDAOImpl.error.getSupplyInfoByItemSAndVendorS");
		}
	}

	
}
