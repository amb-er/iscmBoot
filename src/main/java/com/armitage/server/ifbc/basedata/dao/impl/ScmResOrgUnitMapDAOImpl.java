package com.armitage.server.ifbc.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.dao.ScmResOrgUnitMapDAO;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmResOrgUnitMapDAO")
public class ScmResOrgUnitMapDAOImpl extends BasicDAOImpl<ScmResOrgUnitMap> implements ScmResOrgUnitMapDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public ScmResOrgUnitMap selectByResOrgUnit(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectByResOrgUnit", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmResOrgUnitMapDAOImpl.error.selectByResOrgUnit");
        }
	}

}