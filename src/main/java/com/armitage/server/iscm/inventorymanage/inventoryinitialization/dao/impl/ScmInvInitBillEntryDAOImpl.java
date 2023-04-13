package com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao.ScmInvInitBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvInitBillEntryDAO")
public class ScmInvInitBillEntryDAOImpl extends BasicDAOImpl<ScmInvInitBillEntry> implements ScmInvInitBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvInitBillEntry2> selectByInitId(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByInitId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventoryinitialization.dao.impl.error.selectByInitId", e);
		}
	}

	@Override
	public void deleteByInitId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByInitId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventoryinitialization.dao.impl.error.deleteByInitId", e);
		}
		
	}

	@Override
	public List<ScmInvInitBillEntry2> selectCancelEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectCancelEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventoryinitialization.dao.impl.error.selectCancelEffectRow", e);
		}
	}

}
