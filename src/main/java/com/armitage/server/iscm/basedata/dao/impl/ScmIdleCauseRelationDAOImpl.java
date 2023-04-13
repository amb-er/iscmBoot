package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmIdleCauseRelationDAO;
import com.armitage.server.iscm.basedata.model.ScmIdleCauseRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmIdleCauseRelationDAO")
public class ScmIdleCauseRelationDAOImpl extends BasicDAOImpl<ScmIdleCauseRelation> implements ScmIdleCauseRelationDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
