package com.armitage.server.ifbc.costcard.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmStandardRatioDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatioDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmStandardRatioDetailDAO")
public class ScmStandardRatioDetailDAOImpl extends BasicDAOImpl<ScmStandardRatioDetail> implements ScmStandardRatioDetailDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmStandardRatioDetail> selectByRateId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByRateId",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmStandardRatioDetailDAOImpl.error.selectByRateId", e);
        }
	}

}

