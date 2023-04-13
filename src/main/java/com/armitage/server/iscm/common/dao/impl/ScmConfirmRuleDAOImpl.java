package com.armitage.server.iscm.common.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.common.dao.ScmConfirmRuleDAO;
import com.armitage.server.iscm.common.model.ScmConfirmRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmConfirmRuleDAO")
public class ScmConfirmRuleDAOImpl extends BasicDAOImpl<ScmConfirmRule> implements ScmConfirmRuleDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public ScmConfirmRule selectByBillType(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByBillType",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.common.dao.impl.ScmConfirmRuleDAOImpl.error.selectByBillType", e);
		}
	}

}
