
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmBillPendingDAO;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmBillPendingDAO")
public class ScmBillPendingDAOImpl extends BasicDAOImpl<ScmBillPending> implements ScmBillPendingDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public int checkExistPendingBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkExistPendingBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.checkExistPendingBill");
		}
	}

	@Override
	public ScmBillPending selectByUsrCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByUsrCode", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.selectByUsrCode");
		}
	}

	@Override
	public ScmBillPending selectLastUsrPended(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectLastUsrPended", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.selectLastUsrPended");
		}
	}

	@Override
	public ScmBillPending selectLastUsrPending(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectLastUsrPending", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.selectLastUsrPending");
		}
	}

	@Override
	public ScmBillPending2 selectPendingUsr(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectPendingUsr", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.selectPendingUsr");
		}
	}

	@Override
	public void deletePendingBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".deletePendingBill", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmBillPendingDAOImpl.error.deletePendingBill");
		}
	}

}
