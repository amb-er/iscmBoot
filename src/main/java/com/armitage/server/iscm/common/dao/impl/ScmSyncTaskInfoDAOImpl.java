package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.ScmSyncTaskInfoDAO;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo;
import com.armitage.server.iscm.common.model.ScmSyncTaskInfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSyncTaskInfoDAO")
public class ScmSyncTaskInfoDAOImpl extends BasicDAOImpl<ScmSyncTaskInfo> implements ScmSyncTaskInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSyncTaskInfo2 selectByScmSyncTaskInfo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByScmSyncTaskInfo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.ScmSyncTaskInfoDAOImpl.error.selectByScmSyncTaskInfo", e);
		}
	}

}
