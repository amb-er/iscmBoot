
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialGroupDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup;
import com.armitage.server.iscm.basedata.model.ScmMaterialGroup2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialGroupDAO")
public class ScmMaterialGroupDAOImpl extends BasicDAOImpl<ScmMaterialGroup> implements ScmMaterialGroupDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public List<ScmMaterialGroup> findChild(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findChild",map);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("iscm.basedata.dao.impl.ScmMaterialGroupDAOImpl.error.findChild");
        }
    }

	@Override
	public ScmMaterialGroup selectByClassCode(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectByClassCode",map);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("iscm.basedata.dao.impl.ScmMaterialGroupDAOImpl.error.selectByClassCode");
        }
	}

	@Override
	public ScmMaterialGroup selectByItemId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectByItemId",map);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("iscm.basedata.dao.impl.ScmMaterialGroupDAOImpl.error.selectByItemId");
        }
	}

	@Override
	public List<ScmMaterialGroup2> queryDetailClassList(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".queryDetailClassList",map);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("iscm.basedata.dao.impl.ScmMaterialGroupDAOImpl.error.queryDetailClassList");
        }
	}

}
