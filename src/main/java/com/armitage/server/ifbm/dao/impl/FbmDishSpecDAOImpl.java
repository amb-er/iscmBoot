package com.armitage.server.ifbm.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbm.dao.FbmDishSpecDAO;
import com.armitage.server.ifbm.model.FbmDishSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmDishSpecDAO")
public class FbmDishSpecDAOImpl extends BasicDAOImpl<FbmDishSpec> implements FbmDishSpecDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIfbm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}

