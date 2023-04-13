
package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmProductCostCardDAO")
public class ScmProductCostCardDAOImpl extends BasicDAOImpl<ScmProductCostCard> implements ScmProductCostCardDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmProductCostCard selectUnique(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectUnique", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDAOImpl.error.selectUnique",e);
		}
	}

}
