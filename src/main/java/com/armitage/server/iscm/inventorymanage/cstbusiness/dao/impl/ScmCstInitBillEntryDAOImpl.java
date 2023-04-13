package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstInitBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCstInitBillEntryDAO")
public class ScmCstInitBillEntryDAOImpl extends BasicDAOImpl<ScmCstInitBillEntry> implements ScmCstInitBillEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public List<ScmCstInitBillEntry2> selectByInitId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByInitId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillEntryDAOImpl.error.selectByInitId", e);
        }
    }

    @Override
    public void deleteByInitId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            sqlSession.delete(simpleName + ".deleteByInitId", map);
       } catch (Exception e) {
           throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillEntryDAOImpl.error.deleteByInit", e);
       }
        
    }

	@Override
	public List<ScmCstInitBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillEntryDAOImpl.error.selectInvQty", e);
        }
	}

	@Override
	public List<ScmCstInitBillEntry2> selectCancelPostEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectCancelPostEffectRow",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmCstInitBillEntryDAOImpl.error.selectCancelPostEffectRow", e);
        }
	}

}

