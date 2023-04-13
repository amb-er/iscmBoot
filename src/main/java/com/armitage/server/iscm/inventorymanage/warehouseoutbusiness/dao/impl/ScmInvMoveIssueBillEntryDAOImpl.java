package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveIssueBillEntryDAO")
public class ScmInvMoveIssueBillEntryDAOImpl  extends BasicDAOImpl<ScmInvMoveIssueBillEntry> implements ScmInvMoveIssueBillEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmInvMoveIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAOImpl.error.selectByOtId", e);
		}
	}
	
	@Override
	public void deleteByOtId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByOtId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAOImpl.error.deleteByOtId", e);
		}
	}

    @Override
    public void deleteById(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            sqlSession.delete(simpleName + ".deleteById", map);
       } catch (Exception e) {
           throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAOImpl.error.deleteById", e);
       }
    }

    @Override
    public List<ScmInvMoveIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAOImpl.error.selectInvQty", e);
        }
    }

	@Override
	public List<ScmInvMoveIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOutEffectRow", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMoveIssueBillEntryDAOImpl.error.selectOutEffectRow", e);
        }
	}
}

