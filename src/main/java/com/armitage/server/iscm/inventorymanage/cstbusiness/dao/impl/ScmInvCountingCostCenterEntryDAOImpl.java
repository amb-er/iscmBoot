package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingCostCenterEntryDAO")
public class ScmInvCountingCostCenterEntryDAOImpl extends BasicDAOImpl<ScmInvCountingCostCenterEntry> implements ScmInvCountingCostCenterEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByTableId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTableId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.selectByTableId", e);
		}
	}

	@Override
	public void deleteByTableId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByTableId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.deleteByTableId", e);
		}
	}

	@Override
	public void addByItemIdList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addByItemIdList",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.addByItemIdList", e);
		}
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.selectByTaskId", e);
		}
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".queryCountCostTaskDiff",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.queryCountCostTaskDiff", e);
		}
	}
	@Override
	public int selectByTaskIdAndFinOrg(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskIdAndFinOrg", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.selectByTaskIdAndFinOrg", e);
		}
	}

	@Override
	public void refreshAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".refreshAccount", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.refreshAccount", e);
		}
	}

	@Override
	public int checkAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkAccount", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.checkAccount", e);
		}	}

	@Override
	public void addAccount(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addAccount",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.addAccount", e);
		}
	}

	@Override
	public List<ScmInvCountingCostCenterEntry2> selectByIdleCauseId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByIdleCauseId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterEntryDAOImpl.error.selectByIdleCauseId", e);
		}
	}
}
