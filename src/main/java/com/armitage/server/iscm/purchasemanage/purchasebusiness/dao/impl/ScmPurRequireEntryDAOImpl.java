package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurRequireEntryDAO")
public class ScmPurRequireEntryDAOImpl extends BasicDAOImpl<ScmPurRequireEntry> implements ScmPurRequireEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPrId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByPrId", e);
		}
	}

	@Override
	public void deleteByPrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByPrId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.deleteByPrId", e);
		}
	}

	@Override
	public void updateRowStatusByPrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByPrId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.updateRowStatusByPrId",
					e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectByBuyerId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBuyerId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByBuyerId", e);
		}
	}

	@Override
	public void updateRowStatusByLineId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByLineId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.updateRowStatusByLineId",
					e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> viewPurRequestStatus(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".viewPurRequestStatus", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.viewPurRequestStatus", e);
		}
	}

	@Override
	public int updateGrantedStatus(ScmPurRequireEntry2 scmPurRequireEntry) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateGrantedStatus", scmPurRequireEntry);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.updateGrantedStatus", e);
		}
	}

	@Override
	public void updatePurRequestByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updatePurRequestByPmId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.updatePurRequestByPmId",
					e);
		}
	}

	@Override
	public void updatePurRequestPriceBillIdByPmId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updatePurRequestPriceBillIdByPmId", map);
		} catch (Exception e) {
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.updatePurRequestPriceBillIdByPmId",
					e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrId2(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPrId2", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByPrId2", e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> undoReleaseCheck(HashMap<Object, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".undoReleaseCheck", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(
					"iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.undoReleaseCheck", e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> clearPurRequirePrice(HashMap<Object, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return	sqlSession.selectList(simpleName + ".clearPurRequirePrice", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.clearPurRequirePrice", e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrIdCount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return	sqlSession.selectList(simpleName + ".selectByPrIdCount", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByPrIdCount", e);
		}
	}

	@Override
	public List selectByPurOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return	sqlSession.selectList(simpleName + ".selectByPurOrgUnitNo", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByPurOrgUnitNo", e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectByPrIds(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return	sqlSession.selectList(simpleName + ".selectByPrIds", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectByPrIds", e);
		}
	}

	@Override
	public List<ScmPurRequireEntry2> selectAutoOrderListByPrId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return	sqlSession.selectList(simpleName + ".selectAutoOrderListByPrId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireEntryDAOImpl.error.selectAutoOrderListByPrId", e);
		}
	}
	
}
