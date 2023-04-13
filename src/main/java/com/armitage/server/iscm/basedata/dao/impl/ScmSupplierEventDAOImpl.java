package com.armitage.server.iscm.basedata.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmSupplierEventDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierEventDAO")
public class ScmSupplierEventDAOImpl extends BasicDAOImpl<ScmSupplierEvent> implements ScmSupplierEventDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}

