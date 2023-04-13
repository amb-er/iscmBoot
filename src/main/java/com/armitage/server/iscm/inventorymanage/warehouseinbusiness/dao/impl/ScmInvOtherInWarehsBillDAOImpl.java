package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvOtherInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvOtherInWarehsBillDAO")
public class ScmInvOtherInWarehsBillDAOImpl extends BasicDAOImpl<ScmInvOtherInWarehsBill> implements ScmInvOtherInWarehsBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvOtherInWarehsBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvOtherInWarehsBillDAOImpl.error.selectMaxIdByDate");
		}
	}
	
	@Override
	public int checkStock(long wrId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkStock", wrId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvOtherInWarehsBillDAOImpl.error.checkStock");
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkUnPostBill");
		}
	}

	@Override
	public List<ScmInvOtherInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkPostedBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkPostedBill");
		}
	}

    @Override
    public List<ScmInvOtherInWarehsBill2> checkWareHouseFree(long wrId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", wrId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvOtherInWarehsBillDAOImpl.error.checkMaterialFree");
        }
    }

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvOtherInWarehsBillDAOImpl.error.updatePostedStatus", e);
		}
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		   try {
	            sqlSession.clearCache();
	            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.countUnPostBill");
	        }
	}
}

