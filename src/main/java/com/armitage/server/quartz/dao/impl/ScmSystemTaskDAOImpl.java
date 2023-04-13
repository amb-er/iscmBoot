
package com.armitage.server.quartz.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.quartz.dao.ScmSystemTaskDAO;
import com.armitage.server.quartz.model.ScmSystemTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSystemTaskDAO")
public class ScmSystemTaskDAOImpl extends BasicDAOImpl<ScmSystemTask> implements ScmSystemTaskDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
