package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCostConsumeEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCostConsumeEntryDAO")
public class ScmInvCostConsumeEntryDAOImpl extends BasicDAOImpl<ScmInvCostConsumeEntry> implements ScmInvCostConsumeEntryDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public List<ScmInvCostConsumeEntry2> selectByDcId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByDcId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeEntryDAOImpl.error.selectByDcId", e);
        }
    }

    @Override
    public void deleteByDcId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            sqlSession.delete(simpleName + ".deleteByDcId", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeEntryDAOImpl.error.deleteByDcId", e);
        }
    }

	@Override
	public List<ScmInvCostConsumeEntry2> checkStockByReturnWareHouse(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkStockByReturnWareHouse",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeEntryDAOImpl.error.checkStockByReturnWareHouse", e);
		}
	}

	@Override
	public List<ScmInvCostConsumeEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeEntryDAOImpl.error.selectOutEffectRow", e);
		}
	}

}
