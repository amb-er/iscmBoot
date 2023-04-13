package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReceiveDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurReceiveDAO")
public class ScmPurReceiveDAOImpl extends BasicDAOImpl<ScmPurReceive> implements ScmPurReceiveDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurReceive selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmPurReceive2> selectByPurOrder(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurOrder", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectByPurOrder");
		}
	}

	@Override
	public ScmPurReceive2 selectByCkId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCkId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectByCkId");
		}
	}

	@Override
	public List<ScmPurReceive2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectBySaleIssueBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectBySaleIssueBill");
		}
	}

	@Override
	public List<ScmPurReceive2> selectByOtherIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOtherIssueBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectByOtherIssueBill");
		}
	}
	
	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDrillBills", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectDrillBills");
        }
	}

	@Override
	public List<ScmPurReceive2> findBypvNos(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findBypvNos", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.findBypvNos");
        }
	}

	@Override
	public ScmPurReceive2 selectByBillCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByBillCode", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurReceiveDAOImpl.error.selectByBillCode");
		}
	}

}



