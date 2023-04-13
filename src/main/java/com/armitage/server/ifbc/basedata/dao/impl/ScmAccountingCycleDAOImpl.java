package com.armitage.server.ifbc.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.dao.ScmAccountingCycleDAO;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmAccountingCycleDAO")
public class ScmAccountingCycleDAOImpl extends BasicDAOImpl<ScmAccountingCycle> implements ScmAccountingCycleDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public List<ScmAccountingCycle> selectByBegAndEndId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBegAndEndId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmAccountingCycleDAOImpl.error.selectByBegAndEndId", e);
		}
	}

}