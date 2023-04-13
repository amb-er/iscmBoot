
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMeasureUnitAssignDAO;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnitAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMeasureUnitAssignDAO")
public class ScmMeasureUnitAssignDAOImpl extends BasicDAOImpl<ScmMeasureUnitAssign> implements ScmMeasureUnitAssignDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
