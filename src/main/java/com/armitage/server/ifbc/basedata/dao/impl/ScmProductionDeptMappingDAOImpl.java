package com.armitage.server.ifbc.basedata.dao.impl;
		
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.dao.ScmProductionDeptMappingDAO;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping;
import com.armitage.server.ifbc.basedata.model.ScmProductionDeptMapping2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmProductionDeptMappingDAO")
public class ScmProductionDeptMappingDAOImpl extends BasicDAOImpl<ScmProductionDeptMapping> implements ScmProductionDeptMappingDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmProductionDeptMapping2> selectByProductDeptId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByProductDeptId",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmProductionDeptMappingDAOImpl.error.selectByProductDeptId", e);
        }
	}

	@Override
	public List<ScmProductionDeptMapping2> selectByOrgUnit(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOrgUnit",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmProductionDeptMappingDAOImpl.error.selectByOrgUnit", e);
        }
	}

	@Override
	public List<ScmProductionDeptMapping2> findRepeat(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findRepeat",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmProductionDeptMappingDAOImpl.error.findRepeat", e);
        }
	}

}