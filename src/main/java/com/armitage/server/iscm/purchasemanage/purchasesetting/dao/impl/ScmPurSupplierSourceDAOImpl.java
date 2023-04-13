
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplierSourceDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurSupplierSourceDAO")
public class ScmPurSupplierSourceDAOImpl extends BasicDAOImpl<ScmPurSupplierSource> implements ScmPurSupplierSourceDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
