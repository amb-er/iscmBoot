package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmCstFrmLossDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmCstFrmLossDAO")
public class ScmCstFrmLossDAOImpl extends BasicDAOImpl<ScmCstFrmLoss> implements ScmCstFrmLossDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map)
			throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.updatePostedStatus", e);
		}
	}
	
	@Override
	public List<ScmCstFrmLoss2> checkUnPostBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.checkUnPostBill");
        }
	}

	@Override
	public List<ScmCstFrmLoss2> checkPostedBill(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkPostedBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.checkPostedBill");
        }
	}

	@Override
	public List<ScmCstFrmLoss2> checkCostCenterFree(long id)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkCostCenterFree", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.checkCostCenterFree");
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
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.checkMaterialFree");
        }
	}

	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countCostUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmCstFrmLossDAOImpl.error.countCostUnPostBill");
        }
	}

}
