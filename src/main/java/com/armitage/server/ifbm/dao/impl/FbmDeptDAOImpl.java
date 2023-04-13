package com.armitage.server.ifbm.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbm.dao.FbmDeptDAO;
import com.armitage.server.ifbm.model.FbmDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmDeptDAO")
public class FbmDeptDAOImpl extends BasicDAOImpl<FbmDept> implements FbmDeptDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIfbm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
