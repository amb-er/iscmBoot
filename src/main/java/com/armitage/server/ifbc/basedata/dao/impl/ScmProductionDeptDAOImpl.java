package com.armitage.server.ifbc.basedata.dao.impl;

import com.armitage.server.common.base.dao.BaseDataDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbc.basedata.dao.ScmProductionDeptDAO;
import com.armitage.server.ifbc.basedata.model.ScmProductionDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmProductionDeptDAO")
public class ScmProductionDeptDAOImpl extends BaseDataDAOImpl<ScmProductionDept> implements ScmProductionDeptDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}