package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.ScmDataCollectionStepStateDAO;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState;
import com.armitage.server.iscm.common.model.ScmDataCollectionStepState2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmDataCollectionStepStateDAO")
public class ScmDataCollectionStepStateDAOImpl extends BasicDAOImpl<ScmDataCollectionStepState> implements ScmDataCollectionStepStateDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmDataCollectionStepState2> findAsynStep(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findAsynStep", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.rptdata.dao.impl.ScmDataCollectionStepStateDAOImpl.error.findAsynStep");
		}
	}

	@Override
	public ScmDataCollectionStepState2 selectByStepIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByStepIdAndOrgUnitNo", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.rptdata.dao.impl.ScmDataCollectionStepStateDAOImpl.error.selectByStepIdAndOrgUnitNo");
		}
	}

}
