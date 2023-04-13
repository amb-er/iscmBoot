package com.armitage.server.iscm.common.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.common.dao.ScmDataCollectionRegDAO;
import com.armitage.server.iscm.common.model.ScmDataCollectionReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmDataCollectionRegDAO")
public class ScmDataCollectionRegDAOImpl extends BasicDAOImpl<ScmDataCollectionReg> implements ScmDataCollectionRegDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
