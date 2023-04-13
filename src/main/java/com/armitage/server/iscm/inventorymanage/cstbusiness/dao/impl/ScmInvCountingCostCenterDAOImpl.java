package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingCostCenterDAO")
public class ScmInvCountingCostCenterDAOImpl extends BasicDAOImpl<ScmInvCountingCostCenter> implements ScmInvCountingCostCenterDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteNotExist(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteNotExist", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAOImpl.error.deleteNotExist", e);
		}
	}

	@Override
	public List<ScmInvCountingCostCenter2> findDifference(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findDifference",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAOImpl.error.findDifference", e);
		}
	}

	@Override
	public List<ScmInvCountingCostCenter2> selectByTaskNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAOImpl.error.selectByTaskNo", e);
		}
	}

	@Override
	public ScmInvCountingCostCenter2 selectByTaskNoAndUseOrgUnitNo(
			HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskNoAndUseOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.ScmInvCountingCostCenterDAOImpl.error.selectByTaskNoAndUseOrgUnitNo", e);
		}
	}

}
