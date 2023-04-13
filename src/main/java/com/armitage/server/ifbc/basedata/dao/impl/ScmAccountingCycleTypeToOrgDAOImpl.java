package com.armitage.server.ifbc.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BaseDataDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.dao.ScmAccountingCycleTypeToOrgDAO;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleTypeToOrg2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmAccountingCycleTypeToOrgDAO")
public class ScmAccountingCycleTypeToOrgDAOImpl extends BaseDataDAOImpl<ScmAccountingCycleTypeToOrg> implements ScmAccountingCycleTypeToOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmAccountingCycleTypeToOrg2 selectByOrgUnit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByOrgUnit",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmAccountingCycleTypeToOrgDAOImpl.error.selectByOrgUnit", e);
		}
	}

}