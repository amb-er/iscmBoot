package com.armitage.server.iscm.report.purchase.dao.impl;

import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.report.purchase.dao.ISCMPurchaseDAO;
import com.armitage.server.iscm.report.purchase.model.ISCMPurchase;
import com.armitage.server.system.dao.impl.BaseReportDataDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("iSCMPurchaseDAO")
public class ISCMPurchaseDAOImpl extends BaseReportDataDAOImpl<ISCMPurchase> implements ISCMPurchaseDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
	
}
