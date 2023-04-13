package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvMoveInWarehsBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveInWarehsBillEntryDAO")
public class ScmInvMoveInWarehsBillEntryDAOImpl extends BasicDAOImpl<ScmInvMoveInWarehsBillEntry> implements ScmInvMoveInWarehsBillEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public void deleteByWrId(HashMap<String, Object> map) throws DAOException {
       try {
            sqlSession.clearCache();
            sqlSession.delete(simpleName + ".deleteByWrId", map);
       } catch (Exception e) {
           throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillEntryDAOImpl.error.deleteByWrId", e);
       }
    }

    @Override
    public List<ScmInvMoveInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByWrId", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillEntryDAOImpl.error.selectByWrId", e);
        }
    }

	@Override
	public List<ScmInvMoveInWarehsBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillEntryDAOImpl.error.selectInvQty", e);
        }
	}

	@Override
	public List<ScmInvMoveInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOutEffectRow", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillEntryDAOImpl.error.selectOutEffectRow", e);
        }
	}

}

