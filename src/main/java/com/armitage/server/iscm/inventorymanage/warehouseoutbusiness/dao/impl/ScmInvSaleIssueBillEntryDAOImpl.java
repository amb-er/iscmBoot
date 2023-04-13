package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSaleIssueBillEntryDAO")
public class ScmInvSaleIssueBillEntryDAOImpl extends BasicDAOImpl<ScmInvSaleIssueBillEntry> implements ScmInvSaleIssueBillEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public List<ScmInvSaleIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOtId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAOImpl.error.selectByOtId", e);
        }
    }

    @Override
    public List<ScmInvSaleIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAOImpl.error.selectInvQty", e);
        }
    }

	@Override
	public List<ScmInvSaleIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOutEffectRow", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAOImpl.error.selectOutEffectRow", e);
        }
	}

	@Override
	public List<ScmInvSaleIssueBillEntry2> selectSaleIssueByPurOut(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectSaleIssueByPurOut", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAOImpl.error.selectSaleIssueByPurOut", e);
        }
	}

	@Override
	public List<ScmInvSaleIssueBillEntry2> selectCancelPostEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectCancelPostEffectRow", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillEntryDAOImpl.error.selectCancelPostEffectRow", e);
        }
	}

}
