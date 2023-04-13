
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialCostCardTypeDetailDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail;
import com.armitage.server.iscm.basedata.model.ScmMaterialCostCardTypeDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialCostCardTypeDetailDAO")
public class ScmMaterialCostCardTypeDetailDAOImpl extends BasicDAOImpl<ScmMaterialCostCardTypeDetail> implements ScmMaterialCostCardTypeDetailDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmMaterialCostCardTypeDetail2> selectByTypeId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByTypeId",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialCostCardTypeDetailDAOImpl.error.selectByTypeId", e);
        }
	}

}
