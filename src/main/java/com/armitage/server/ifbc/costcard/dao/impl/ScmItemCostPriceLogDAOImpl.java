package com.armitage.server.ifbc.costcard.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceLogDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmItemCostPriceLogDAO")
public class ScmItemCostPriceLogDAOImpl extends BasicDAOImpl<ScmItemCostPriceLog> implements ScmItemCostPriceLogDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}

