
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMeasureUnitGrouplAssignDAO;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnitGrouplAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMeasureUnitGrouplAssignDAO")
public class ScmMeasureUnitGrouplAssignDAOImpl extends BasicDAOImpl<ScmMeasureUnitGrouplAssign> implements ScmMeasureUnitGrouplAssignDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
