
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierRegInvitationDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierRegInvitationDAO")
public class ScmSupplierRegInvitationDAOImpl extends BasicDAOImpl<ScmSupplierRegInvitation> implements ScmSupplierRegInvitationDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSupplierRegInvitation selectByVendorIdAndCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByVendorIdAndCtrl",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierRegInvitationDAOImpl.error.selectByVendorIdAndCtrl", e);
		}
	}

	@Override
	public ScmSupplierRegInvitation selectByCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCtrl",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierRegInvitationDAOImpl.error.selectByCtrl", e);
		}
	}

	@Override
	public ScmSupplierRegInvitation selectByPlatVendorIdAndCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByPlatVendorIdAndCtrl",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierRegInvitationDAOImpl.error.selectByPlatVendorIdAndCtrl", e);
		}
	}

	@Override
	public List<ScmSupplierRegInvitation> selectBindedByCtrl(HashMap<String, Object> map) throws DAOException {
		try{
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectBindedByCtrl",map);
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierRegInvitationDAOImpl.error.selectBindedByCtrl");
		}
	}

	@Override
	public List<ScmSupplierRegInvitation> selectBindedByCtrlAndVendor(HashMap<String, Object> map) throws DAOException {
		try{
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectBindedByCtrlAndVendor",map);
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierRegInvitationDAOImpl.error.selectBindedByCtrlAndVendor");
		}
	}
}
