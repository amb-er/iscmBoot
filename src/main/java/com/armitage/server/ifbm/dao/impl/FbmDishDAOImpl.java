package com.armitage.server.ifbm.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbm.dao.FbmDishDAO;
import com.armitage.server.ifbm.model.FbmDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmDishDAO")
public class FbmDishDAOImpl extends BasicDAOImpl<FbmDish> implements FbmDishDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIfbm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
