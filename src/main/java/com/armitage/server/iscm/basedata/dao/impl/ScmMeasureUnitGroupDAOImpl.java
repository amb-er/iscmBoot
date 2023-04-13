
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMeasureUnitGroupDAO;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnitGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMeasureUnitGroupDAO")
public class ScmMeasureUnitGroupDAOImpl extends BasicDAOImpl<ScmMeasureUnitGroup> implements ScmMeasureUnitGroupDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

}
