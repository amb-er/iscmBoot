package com.armitage.server.quartz.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.quartz.dao.ScmSyncTaskLogDAO;
import com.armitage.server.quartz.model.ScmSyncTaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSyncTaskLogDAO")
public class ScmSyncTaskLogDAOImpl extends BasicDAOImpl<ScmSyncTaskLog> implements ScmSyncTaskLogDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
    
}

