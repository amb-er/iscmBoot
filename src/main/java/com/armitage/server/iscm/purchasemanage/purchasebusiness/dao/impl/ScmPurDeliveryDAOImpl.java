package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurDeliveryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurDeliveryDAO")
public class ScmPurDeliveryDAOImpl extends BasicDAOImpl<ScmPurDelivery> implements ScmPurDeliveryDAO {


    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}

