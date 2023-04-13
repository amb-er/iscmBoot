
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmBillPendingToUsrDAO;
import com.armitage.server.iscm.basedata.model.ScmBillPendingToUsr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmBillPendingToUsrDAO")
public class ScmBillPendingToUsrDAOImpl extends BasicDAOImpl<ScmBillPendingToUsr> implements ScmBillPendingToUsrDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void updateProcessed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateProcessed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingToUsrDAOImpl.error.updateProcessed", e);
		}
	}

	@Override
	public void updateUsrProcessed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateUsrProcessed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingToUsrDAOImpl.error.updateUsrProcessed", e);
		}
	}

	@Override
	public List<ScmBillPendingToUsr> selectByPendingId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPendingId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingToUsrDAOImpl.error.selectByPendingId", e);
		}
	}

	@Override
	public void cancelProcessed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".cancelProcessed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingToUsrDAOImpl.error.cancelProcessed", e);
		}
	}

	@Override
	public void cancelUsrProcessed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".cancelUsrProcessed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingToUsrDAOImpl.error.cancelUsrProcessed", e);
		}
	}

}
