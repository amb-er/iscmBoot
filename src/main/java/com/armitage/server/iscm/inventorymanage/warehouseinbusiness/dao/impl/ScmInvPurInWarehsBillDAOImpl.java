package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.ScmInvPurInWarehsBillDAO;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvPurInWarehsBillDAO")
public class ScmInvPurInWarehsBillDAOImpl extends BasicDAOImpl<ScmInvPurInWarehsBill> implements ScmInvPurInWarehsBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmInvPurInWarehsBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public int checkfollowupbill(long wrId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkfollowupbill", wrId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkfollowupbill");
		}
	}
	
	@Override
	public int checkStock(long wrId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkStock", wrId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkStock");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnPostBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkUnPostBill");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurReceive(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurReceive", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectByPurReceive");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurReturns(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurReturns", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectByPurReturns");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkPostedBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkPostedBill");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectBySaleIssueBill", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectBySaleIssueBill");
		}
	}

	@Override
	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".updatePostedStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.updatePostedStatus", e);
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> selectByPurOrder(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPurOrder", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectByPurOrder");
		}
	}

	@Override
	public BigDecimal getTotalPurInWarehsQty(HashMap<String, Object> map)
			throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getTotalPurInWarehsQty", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.getTotalPurInWarehsQty");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkWareHouseFree(
			long wrId) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkWareHouseFree", wrId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkWareHouseFree");
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
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkMaterialFree");
        }
	}

	@Override
	public List<ScmInvPurInWarehsBill2> checkCostCenterFree(
			long wrId) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".checkCostCenterFree", wrId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.checkCostCenterFree");
        }
	}

	@Override
	public int costCenterMaterialFree(HashMap<String, Object> map)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectOne(simpleName + ".costCenterMaterialFree", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.costCenterMaterialFree");
        }
	}

	@Override
	public ScmInvPurInWarehsBill2 selectPoNoAndPvNoById(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectPoNoAndPvNoById", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectPoNoAndPvNoById");
		}
	}

	@Override
	public void updatePushed(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updatePushed", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.updatePushed", e);
		}
	}

	@Override
	public void updateConfirmStatus(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateConfirmStatus", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.updateConfirmStatus", e);
		}
	}

	@Override
	public void updateVersion(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateVersion", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.updateVersion", e);
		}
	}
	
	@Override
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.countUnPostBill");
        }
	}
	
	@Override
	public List<Map<String, Object>> countCostUnPostBill(HashMap<String, Object> map) {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".countCostUnPostBill", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.countCostUnPostBill");
        }
	}

	@Override
	public ScmInvPurInWarehsBill2 selectByWrNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByWrNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectByWrNo");
		}
	}

	@Override
	public List<ScmInvPurInWarehsBill2> querySupSupplyOfMaterialDetails(HashMap<String, Object> map, Param param)
			throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".querySupSupplyOfMaterialDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.querySupSupplyOfMaterialDetails");
        }
	}
	
	@Override
	public List getTotalPurInWarehsQtyByItems(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".getTotalPurInWarehsQtyByItems", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.getTotalPurInWarehsQtyByItems");
		}
	}
	
	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectDrillBills", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectDrillBills");
        }
	}
	
	@Override
	public List<ScmPurBillDrillResult> selectDrillBillsISCM(List<String> list) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectDrillBillsISCM", list);
		} catch (Exception e) {
			e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao.impl.ScmInvPurInWarehsBillDAOImpl.error.selectDrillBillsISCM");
		}
	}
}

