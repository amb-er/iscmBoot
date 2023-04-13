package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCostConsumeDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCostConsumeDAO")
public class ScmInvCostConsumeDAOImpl extends BasicDAOImpl<ScmInvCostConsume> implements ScmInvCostConsumeDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

    @Override
    public ScmInvCostConsume2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
        try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.selectMaxIdByDate");
        }
    }

	@Override
	public List<ScmInvCostConsume2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.checkUnPostBill");
        }
	}

	@Override
	public List<ScmInvCostConsume2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkPostedBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.checkPostedBill");
        }
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
	public List<ScmInvCostConsume2> checkCostCenterFree(long dcId)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkCostCenterFree", dcId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.checkCostCenterFree");
        }
	}

	@Override
	public int checkMaterialFree(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".checkMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.checkMaterialFree");
        }
	}

	@Override
	public List<ScmInvCostConsume2> selectGenerateBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectGenerateBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.selectGenerateBill");
        }
	}

	@Override
	public List<ScmInvCostConsume2> selectGenerateBillBySourceType(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectGenerateBillBySourceType", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.selectGenerateBillBySourceType");
        }
	}

	@Override
	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCostConsumeDAOImpl.error.countUnPostBill");
        }
	}

}
