package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierDemanderDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierDemanderDAO")
public class ScmSupplierDemanderDAOImpl extends BasicDAOImpl<ScmSupplierDemander> implements ScmSupplierDemanderDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public ScmSupplierDemander selectByCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCtrl",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierDemanderDAOImpl.error.selectByCtrl", e);
		}
	}

	@Override
	public ScmSupplierDemander selectByDemanderId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByDemanderId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierDemanderDAOImpl.error.selectByDemanderId", e);
		}
	}

}
