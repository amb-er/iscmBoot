package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvAccreditWhDAO")
public class ScmInvAccreditWhDAOImpl extends BasicDAOImpl<ScmInvAccreditWh> implements ScmInvAccreditWhDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmInvAccreditWh2> selectByWareHouseIdList(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByWareHouseIdList",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAOImpl.error.selectByWareHouseIdList", e);
		}
	}

    @Override
    public void updateEndInit(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateEndInit", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAOImpl.error.updateEndInit", e);
        }
        
    }

    @Override
    public int selectCount(HashMap<String, Object> map) throws DAOException {
        try {
            return (int)sqlSession.selectOne(simpleName + ".selectCount", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAOImpl.error.selectCount", e);
        }
    }

    @Override
    public void updateReverseInit(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.update(simpleName + ".updateReverseInit", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAOImpl.error.updateReverseInit", e);
        }
    }

	@Override
	public ScmInvAccreditWh selectByWareHouseId(HashMap<String, Object> map)
			throws DAOException {
		try {
            return sqlSession.selectOne(simpleName + ".selectByWareHouseId", map);
        } catch (Exception e) {
            throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvAccreditWhDAOImpl.error.selectByWareHouseId", e);
        }
	}

}

