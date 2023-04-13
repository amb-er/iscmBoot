
package com.armitage.server.iscm.basedata.dao.impl;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.iscm.basedata.dao.ScmMaterialCostCardTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialCostCardTypeDAO")
public class ScmMaterialCostCardTypeDAOImpl extends BasicDAOImpl<ScmMaterialCostCardType> implements ScmMaterialCostCardTypeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }
}
