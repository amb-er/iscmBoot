
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurchaseCanuseSetDAO")
public class ScmPurchaseCanuseSetDAOImpl extends BasicDAOImpl<ScmPurchaseCanuseSet> implements ScmPurchaseCanuseSetDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
