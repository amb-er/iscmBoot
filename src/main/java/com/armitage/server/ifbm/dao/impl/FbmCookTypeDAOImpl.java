package com.armitage.server.ifbm.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbm.dao.FbmCookTypeDAO;
import com.armitage.server.ifbm.model.FbmCookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmCookTypeDAO")
public class FbmCookTypeDAOImpl extends BasicDAOImpl<FbmCookType> implements FbmCookTypeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIfbm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
