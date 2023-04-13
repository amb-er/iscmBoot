package com.armitage.server.ifbc.costcard.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbc.costcard.dao.ScmStandardRatioDAO;
import com.armitage.server.ifbc.costcard.model.ScmStandardRatio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmStandardRatioDAO")
public class ScmStandardRatioDAOImpl extends BasicDAOImpl<ScmStandardRatio> implements ScmStandardRatioDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}

