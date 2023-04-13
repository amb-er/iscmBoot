
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMaterialAssignDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialAssignDAO")
public class ScmMaterialAssignDAOImpl extends BasicDAOImpl<ScmMaterialAssign> implements ScmMaterialAssignDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
