package com.armitage.server.ifbc.rptdata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.dao.ScmDiskStdCostInfoDAO;
import com.armitage.server.ifbc.rptdata.model.ScmDiskStdCostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmDiskStdCostInfoDAO")
public class ScmDiskStdCostInfoDAOImpl extends BasicDAOImpl<ScmDiskStdCostInfo> implements ScmDiskStdCostInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void batchAdd(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".batchAdd",map);
		} catch (Exception e) {
			throw new DAOException("armitage.server.ifbc.rptdata.dao.impl.ScmDiskStdCostInfoDAOImpl.error.batchAdd", e);
		}
	}

	@Override
	public void batchDelete(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".batchDelete", map);
		} catch (Exception e) {
			throw new DAOException("armitage.server.ifbc.rptdata.dao.impl.ScmDiskStdCostInfoDAOImpl.error.batchDelete", e);
		}
	}

	@Override
	public List<ScmDiskStdCostInfo> selectByResOrgUnitNos(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByResOrgUnitNos",map);
        } catch (Exception e) {
            throw new DAOException("armitage.server.ifbc.rptdata.dao.impl.ScmDiskStdCostInfoDAOImpl.error.selectByResOrgUnitNos", e);
        }
	}

}
