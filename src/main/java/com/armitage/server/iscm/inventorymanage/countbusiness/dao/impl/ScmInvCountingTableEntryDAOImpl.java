package com.armitage.server.iscm.inventorymanage.countbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingTableEntryDAO")
public class ScmInvCountingTableEntryDAOImpl extends BasicDAOImpl<ScmInvCountingTableEntry> implements ScmInvCountingTableEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectByTableId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTableId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.selectByTableId", e);
		}
	}

	@Override
	public void deleteByTableId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByTableId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.deleteByTableId", e);
		}
	}

	@Override
	public void addByItemIdList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByItemIdList",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.addByItemIdList", e);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.selectByTaskId", e);
		}
	}

	@Override
	public int checkAccount(long taskId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkAccount", taskId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.checkAccount", e);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".queryCountInvTaskDiff",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.queryCountInvTaskDiff", e);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectForOtherInWh(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectForOtherInWh",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.selectForOtherInWh", e);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> selectForOtherOutWh(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectForOtherOutWh",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.selectForOtherOutWh", e);
		}
	}

	@Override
	public void updateLot(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateLot", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.updateLot", e);
		}
	}

	@Override
	public List<ScmInvCountingTableEntry2> findAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findAccount",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.findAccount", e);
		}
	}

	@Override
	public void refreshAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".refreshAccount", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.refreshAccount", e);
		}
	}

	@Override
	public void addAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addAccount",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.countbusiness.dao.ScmInvCountingTableEntryDAOImpl.error.addAccount", e);
		}
	}

}

