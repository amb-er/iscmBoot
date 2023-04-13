package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingTaskDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingTaskDAO")
public class ScmInvCountingTaskDAOImpl extends BasicDAOImpl<ScmInvCountingTask> implements ScmInvCountingTaskDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
    public ScmInvCountingTask2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.selectMaxIdByDate");
        }
    }

    @Override
    public List<ScmInvCountingTask2> selectByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.selectByDate");
        }
    }

	@Override
	public List<ScmInvCountingTask2> selectByOrgUnitNoAndWareHouseId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOrgUnitNoAndWareHouseId", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.selectByOrgUnitNoAndWareHouseId");
        }
	}

	@Override
	public List<ScmInvCountingTask2> selectByOrgUnitNoAndUseOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOrgUnitNoAndUseOrgUnitNo", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.selectByOrgUnitNoAndUseOrgUnitNo");
        }
	}

	@Override
	public int updateFinishStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateFinishStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.updateFinishStatus", e);
		}	}

	@Override
	public List<ScmInvCountingTask2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.checkUnPostBill");
        }
	}

	@Override
	public ScmInvCountingTask2 queryByTaskNo(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".queryByTaskNo", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingTaskDAOImpl.error.queryByTaskNo");
        }
	}

	
}
