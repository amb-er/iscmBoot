package com.armitage.server.iscm.basedata.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmsuppliergroupstandardDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupstandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsuppliergroupstandardDAO")
public class ScmsuppliergroupstandardDAOImpl extends BasicDAOImpl<Scmsuppliergroupstandard> implements ScmsuppliergroupstandardDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
