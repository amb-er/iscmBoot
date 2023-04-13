package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBillEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvOtherIssueBillEntryDAO")
public class ScmInvOtherIssueBillEntryDAOImpl  extends BasicDAOImpl<ScmInvOtherIssueBillEntry> implements ScmInvOtherIssueBillEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAOImpl.error.selectByOtId", e);
		}
	}
	
	@Override
	public List<ScmInvOtherIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAOImpl.error.selectInvQty", e);
        }
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectMaxLineId(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectMaxLineId", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAOImpl.error.selectMaxLineId", e);
        }
	}

	@Override
	public List<ScmInvOtherIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectOutEffectRow", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillEntryDAOImpl.error.selectOutEffectRow", e);
        }
	}
}

