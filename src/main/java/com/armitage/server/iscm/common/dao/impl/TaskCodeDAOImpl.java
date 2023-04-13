package com.armitage.server.iscm.common.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.common.dao.TaskCodeDAO;
import com.armitage.server.iscm.common.model.TaskCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("taskCodeDAO")
public class TaskCodeDAOImpl extends BasicDAOImpl<TaskCode> implements TaskCodeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
