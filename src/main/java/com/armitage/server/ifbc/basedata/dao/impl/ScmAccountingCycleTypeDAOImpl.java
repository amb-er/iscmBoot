package com.armitage.server.ifbc.basedata.dao.impl;

import com.armitage.server.common.base.dao.BaseDataDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbc.basedata.dao.ScmAccountingCycleTypeDAO;
import com.armitage.server.ifbc.basedata.model.ScmAccountingCycleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmAccountingCycleTypeDAO")
public class ScmAccountingCycleTypeDAOImpl extends BaseDataDAOImpl<ScmAccountingCycleType> implements ScmAccountingCycleTypeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}