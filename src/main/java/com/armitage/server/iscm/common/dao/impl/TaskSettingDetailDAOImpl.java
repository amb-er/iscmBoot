package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.TaskSettingDetailDAO;
import com.armitage.server.iscm.common.model.TaskSettingDetail;
import com.armitage.server.iscm.common.model.TaskSettingDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("taskSettingDetailDAO")
public class TaskSettingDetailDAOImpl extends BasicDAOImpl<TaskSettingDetail> implements TaskSettingDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<TaskSettingDetail> selectBySetId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectBySetId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.TaskSettingDetailDAOImpl.error.selectBySetId");
		}
	}

	@Override
	public List<TaskSettingDetail2> selectByCtrl(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCtrl",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.TaskSettingDetailDAOImpl.error.selectByCtrl", e);
		}
	}

	@Override
	public TaskSettingDetail2 selectByTaskObject(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTaskObject",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.TaskSettingDetailDAOImpl.error.selectByTaskObject", e);
		}
	}

}
