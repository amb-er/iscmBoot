package com.armitage.server.ifbm.dao.impl;


import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.ifbm.dao.FbmItemDAO;
import com.armitage.server.ifbm.model.FbmItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("fbmItemDAO")
public class FbmItemDAOImpl extends BasicDAOImpl<FbmItem> implements FbmItemDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIfbm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
