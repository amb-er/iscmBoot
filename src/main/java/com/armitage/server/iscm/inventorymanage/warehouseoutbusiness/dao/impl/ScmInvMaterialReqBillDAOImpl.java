package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvMaterialReqBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMaterialReqBillDAO")
public class ScmInvMaterialReqBillDAOImpl extends BasicDAOImpl<ScmInvMaterialReqBill> implements ScmInvMaterialReqBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvMaterialReqBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmInvMaterialReqBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.checkUnPostBill");
		}
	}

	@Override
	public List<ScmInvMaterialReqBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkPostedBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.checkPostedBill");
		}
	}

    @Override
    public List<ScmInvMaterialReqBill2> checkWareHouseFree(long otId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", otId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.checkMaterialFree");
        }
    }

    @Override
    public List<ScmInvMaterialReqBill2> inventoryWareHouseFree(long otId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".inventoryWareHouseFree", otId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.inventoryWareHouseFree");
        }
    }

    @Override
    public int inventoryMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".inventoryMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.inventoryMaterialFree");
        }
    }

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		 try {
	            sqlSession.clearCache();
	            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.countUnPostBill");
	        }
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".countCostUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialReqBillDAOImpl.error.countCostUnPostBill");
		}
	}
	
	@Override
	public List<ScmInvMaterialDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDrillBills", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvMaterialRequestBillDAOImpl.error.selectDrillBills");
        }
	}
}

