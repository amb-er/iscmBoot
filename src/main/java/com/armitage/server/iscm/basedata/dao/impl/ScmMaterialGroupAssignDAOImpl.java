
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupAssignDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroupAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialGroupAssignDAO")
public class ScmMaterialGroupAssignDAOImpl extends BasicDAOImpl<ScmMaterialGroupAssign> implements ScmMaterialGroupAssignDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
