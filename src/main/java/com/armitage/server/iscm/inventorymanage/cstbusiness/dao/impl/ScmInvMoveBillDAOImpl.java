package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvMoveBillDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMoveBillDAO")
public class ScmInvMoveBillDAOImpl extends BasicDAOImpl<ScmInvMoveBill> implements ScmInvMoveBillDAO {


    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public ScmInvMoveBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.selectMaxIdByDate");
        }
    }

    @Override
    public List<ScmInvMoveBill2> selectInvQty(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectInvQty", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.selectInvQty");
        }
    }

	@Override
	public List<ScmInvMoveBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.checkUnPostBill");
        }
	}

	@Override
	public List<ScmInvMoveBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkPostedBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.checkPostedBill");
        }
	}

    @Override
    public List<ScmInvMoveBill2> checkWareHouseFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.checkWareHouseFree");
        }
    }

    @Override
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.checkMaterialFree");
        }
    }

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.updatePostedStatus", e);
		}
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) {
		   try {
	            sqlSession.clearCache();
	            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvMoveBillDAOImpl.error.countUnPostBill");
	        }
	}

}
