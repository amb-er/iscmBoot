package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvMoveInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveInWarehsBillDAO")
public class ScmInvMoveInWarehsBillDAOImpl extends BasicDAOImpl<ScmInvMoveInWarehsBill> implements ScmInvMoveInWarehsBillDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public ScmInvMoveInWarehsBill selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public List<ScmInvMoveInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.checkUnPostBill");
		}
	}

	@Override
	public List<ScmInvMoveInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkPostedBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.checkPostedBill");
		}
	}

    @Override
    public List<ScmInvMoveInWarehsBill2> checkWareHouseFree(long wrId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", wrId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public List<ScmInvMoveInWarehsBill2> selectByMoveIssue(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByMoveIssue", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.selectByMoveIssue");
        }
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.countUnPostBill");
        }
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countCostUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvMoveInWarehsBillDAOImpl.error.countCostUnPostBill");
        }
	}

}

