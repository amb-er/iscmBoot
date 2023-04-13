
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurREReuseDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurREReuseDAO")
public class ScmPurREReuseDAOImpl extends BasicDAOImpl<ScmPurREReuse> implements ScmPurREReuseDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void cancelRefuse(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.update(simpleName + ".cancelRefuse",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurREReuseDAOImpl.error.cancelRefuse", e);
		}
	}

	@Override
	public ScmPurREReuse selectByEntryId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByEntryId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurREReuseDAOImpl.error.selectByEntryId", e);
		}
	}

	@Override
	public void deleteByEntryId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByEntryId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurREReuseDAOImpl.error.deleteByEntryId", e);
		}
	}

}
