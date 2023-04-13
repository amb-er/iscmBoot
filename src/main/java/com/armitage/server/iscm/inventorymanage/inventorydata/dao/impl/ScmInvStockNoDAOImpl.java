package com.armitage.server.iscm.inventorymanage.inventorydata.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.inventorymanage.inventorydata.dao.ScmInvStockNoDAO;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvStockNoDAO")
public class ScmInvStockNoDAOImpl extends BasicDAOImpl<ScmInvStockNo> implements ScmInvStockNoDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }



}

