package com.armitage.server.iscm.basedata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmIndustryGroupDAO;
import com.armitage.server.iscm.basedata.model.ScmIndustryGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmIndustryGroupDAO")
public class ScmIndustryGroupDAOImpl extends BasicDAOImpl<ScmIndustryGroup> implements ScmIndustryGroupDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmIndustryGroup> selectByTypeId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTypeId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIndustryGroupDAOImpl.error.selectByTypeId", e);
		}
	}

	@Override
	public ScmIndustryGroup selectByClassCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByClassCode",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIndustryGroupDAOImpl.error.selectByClassCode", e);
		}
	}

}
