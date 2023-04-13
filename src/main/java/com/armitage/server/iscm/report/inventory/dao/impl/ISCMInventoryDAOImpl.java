package com.armitage.server.iscm.report.inventory.dao.impl;

import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.report.inventory.dao.ISCMInventoryDAO;
import com.armitage.server.iscm.report.inventory.model.ISCMInventory;
import com.armitage.server.system.dao.impl.BaseReportDataDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("iSCMInventoryDAO")
public class ISCMInventoryDAOImpl extends BaseReportDataDAOImpl<ISCMInventory> implements ISCMInventoryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
	
}
