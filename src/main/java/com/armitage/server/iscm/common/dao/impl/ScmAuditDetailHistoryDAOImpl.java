package com.armitage.server.iscm.common.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.common.dao.ScmAuditDetailHistoryDAO;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmAuditDetailHistoryDAO")
public class ScmAuditDetailHistoryDAOImpl extends BasicDAOImpl<ScmAuditDetailHistory> implements ScmAuditDetailHistoryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}

