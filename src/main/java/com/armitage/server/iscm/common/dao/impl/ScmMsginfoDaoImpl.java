package com.armitage.server.iscm.common.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.common.dao.ScmMsginfoDao;
import com.armitage.server.iscm.common.model.ScmMsginfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMsginfoDao")
public class ScmMsginfoDaoImpl extends BasicDAOImpl<ScmMsginfo> implements ScmMsginfoDao{

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
