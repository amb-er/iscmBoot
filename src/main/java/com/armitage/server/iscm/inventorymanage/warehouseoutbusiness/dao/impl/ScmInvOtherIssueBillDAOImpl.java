package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.ScmInvOtherIssueBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvOtherIssueBillDAO")
public class ScmInvOtherIssueBillDAOImpl extends BasicDAOImpl<ScmInvOtherIssueBill> implements ScmInvOtherIssueBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}
	
	@Override
	public ScmInvOtherIssueBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmInvOtherIssueBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.checkUnPostBill");
		}
	}

	@Override
	public List<ScmInvOtherIssueBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkPostedBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.checkPostedBill");
		}
	}

	@Override
	public List<ScmInvOtherIssueBill2> selectByPoId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPoId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.selectByPoId");
		}
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
    public List<ScmInvOtherIssueBill2> checkWareHouseFree(long otId) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", otId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.checkMaterialFree");
        }
    }
    
    @Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		  try {
	            sqlSession.clearCache();
	            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao.impl.ScmInvOtherIssueBillDAOImpl.error.countUnPostBill");
	        }
	}
	
}
