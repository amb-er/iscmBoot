package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.ScmSyncDataDAO;
import com.armitage.server.iscm.common.model.ScmSyncData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSyncDataDAO")
public class ScmSyncDataDAOImpl extends BasicDAOImpl<ScmSyncData> implements ScmSyncDataDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSyncData selectByScmSyncData(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByScmSyncData",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.ScmSyncDataDAOImpl.error.selectByScmSyncData", e);
		}
	}

	@Override
	public int updateBillNoChangeTime(ScmSyncData scmSyncData) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateBillNoChangeTime", scmSyncData);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.ScmSyncDataDAOImpl.error.updateBillNoChangeTime", e);
		}
	}

}
