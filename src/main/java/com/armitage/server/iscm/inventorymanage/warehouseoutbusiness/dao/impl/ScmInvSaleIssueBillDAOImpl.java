package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvSaleIssueBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvSaleIssueBillDAO")
public class ScmInvSaleIssueBillDAOImpl extends BasicDAOImpl<ScmInvSaleIssueBill> implements ScmInvSaleIssueBillDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public ScmInvSaleIssueBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public List<ScmInvSaleIssueBill2> SelectBySaleOrder(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".SelectBySaleOrder", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.SelectBySaleOrder");
        }
	}

	@Override
	public List<ScmInvSaleIssueBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.checkUnPostBill");
        }
	}

	@Override
	public List<ScmInvSaleIssueBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkPostedBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.checkPostedBill");
        }
	}

	@Override
	public List<ScmInvSaleIssueBill2> selectByPurInwareHouse(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByPurInwareHouse", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.selectByPurInwareHouse");
        }
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
    public List<ScmInvSaleIssueBill2> checkWareHouseFree(long otId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", otId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.checkMaterialFree");
        }
    }
    
	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.countUnPostBill");
        }
	}

	@Override
	public List<ScmInvSaleIssueBill2> checkOrgFree(long otId) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkOrgFree", otId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvSaleIssueBillDAOImpl.error.checkOrgFree");
        }
	}

}
