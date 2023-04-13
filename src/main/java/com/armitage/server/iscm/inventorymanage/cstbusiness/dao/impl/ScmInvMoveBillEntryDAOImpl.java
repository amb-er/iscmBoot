package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvMoveBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveBillEntryDAO")
public class ScmInvMoveBillEntryDAOImpl extends BasicDAOImpl<ScmInvMoveBillEntry> implements ScmInvMoveBillEntryDAO {


    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public List<ScmInvMoveBillEntry2> selectByWtId(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByWtId",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillEntryDAOImpl.error.selectByWtId", e);
        }
    }

	@Override
	public List<ScmInvMoveBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOutEffectRow",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillEntryDAOImpl.error.selectOutEffectRow", e);
        }
	}

	@Override
	public List<ScmInvMoveBillEntry2> selectCancelEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectCancelEffectRow",map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillEntryDAOImpl.error.selectCancelEffectRow", e);
        }
	}

}
