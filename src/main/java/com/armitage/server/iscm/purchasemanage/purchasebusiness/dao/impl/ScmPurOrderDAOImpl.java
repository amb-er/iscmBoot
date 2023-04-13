package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurOrderDAO")
public class ScmPurOrderDAOImpl extends BasicDAOImpl<ScmPurOrder> implements ScmPurOrderDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurOrder2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectMaxIdByDate");
		}
	}
	
	@Override
	public ScmPurOrderEntry2 getPreUseQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getPreUseQty", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.getPreUseQty");
		}
	}

	@Override
	public List<ScmPurOrder2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectBySaleIssueBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectBySaleIssueBill");
        }
	}

	@Override
	public List<ScmPurOrder2> selectByOtherIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByOtherIssueBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectByOtherIssueBill");
        }
	}

	@Override
	public int updateSendedStaus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateSendedStaus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateSendedStaus", e);
		}
	}

	@Override
	public void updateStaus(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateStaus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateStaus", e);
		}
	}

	@Override
	public BigDecimal getTotalOrderQty(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getTotalOrderQty", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.getTotalOrderQty");
		}
	}

	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDrillBills", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectDrillBills");
        }
	}

	@Override
	public void updatePushed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updatePushed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updatePushed", e);
		}
	}

	@Override
	public void updateVersion(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateVersion", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateVersion", e);
		}
	}

	@Override
	public ScmPurOrder2 selectByPoNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByPoNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectByPoNo");
		}
	}

	@Override
	public void updateLockStatusOrContractNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateLockStatusOrContractNo", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateLockStatusOrContractNo", e);
		}
	}

	@Override
	public int updateBillNoChangeTime(ScmPurOrder2 scmPurOrder) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateBillNoChangeTime", scmPurOrder);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateBillNoChangeTime", e);
		}
	}

	@Override
	public List<ScmPurOrder2> selectByEntryIds(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByEntryIds", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectByEntryIds", e);
        }
	}

	@Override
	public int updateUnSendedStaus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updateUnSendedStaus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.updateUnSendedStaus", e);
		}
	}

	@Override
	public ScmPurOrder2 selectByTypeCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByPoNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurOrderDAOImpl.error.selectByPoNo");
		}
	}
}

