package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.TaskSettingDAO;
import com.armitage.server.iscm.common.model.TaskSetting;
import com.armitage.server.iscm.common.model.TaskSetting2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("taskSettingDAO")
public class TaskSettingDAOImpl extends BasicDAOImpl<TaskSetting> implements TaskSettingDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public TaskSetting2 selectByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.TaskSettingDAOImpl.error.selectByTaskId", e);
		}
	}

}
