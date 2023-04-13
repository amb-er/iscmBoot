
package com.armitage.server.quartz.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.quartz.dao.ScmSystemTaskExecTimeDAO;
import com.armitage.server.quartz.model.ScmSystemTaskExecTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSystemTaskExecTimeDAO")
public class ScmSystemTaskExecTimeDAOImpl extends BasicDAOImpl<ScmSystemTaskExecTime> implements ScmSystemTaskExecTimeDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSystemTaskExecTime selectByTaskType(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskType",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.quartz.dao.impl.ScmSystemTaskExecTimeDAOImpl.error.selectByTaskType", e);
		}
	}

}
