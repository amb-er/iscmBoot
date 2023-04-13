package com.armitage.server.ifbc.rptdata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.dao.ScmCookStdCostInfoDAO;
import com.armitage.server.ifbc.rptdata.model.ScmCookStdCostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCookStdCostInfoDAO")
public class ScmCookStdCostInfoDAOImpl extends BasicDAOImpl<ScmCookStdCostInfo> implements ScmCookStdCostInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void batchAdd(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".batchAdd",map);
		} catch (Exception e) {
			throw new DAOException("armitage.server.ifbc.rptdata.dao.impl.ScmCookStdCostInfoDAOImpl.error.batchAdd", e);
		}
	}

	@Override
	public void batchDetele(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".batchDelete", map);
		} catch (Exception e) {
			throw new DAOException("armitage.server.ifbc.rptdata.dao.impl.ScmCookStdCostInfoDAOImpl.error.batchDelete", e);
		}
	}

}
