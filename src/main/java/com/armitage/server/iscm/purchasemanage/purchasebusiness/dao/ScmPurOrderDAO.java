package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;

public interface ScmPurOrderDAO extends BasicDAO<ScmPurOrder> {
	public ScmPurOrder2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	public ScmPurOrderEntry2 getPreUseQty(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurOrder2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurOrder2> selectByOtherIssueBill(HashMap<String, Object> map) throws DAOException;

	public int updateSendedStaus(HashMap<String, Object> map) throws DAOException;

	public void updateStaus(HashMap<String, Object> map) throws DAOException;

	public BigDecimal getTotalOrderQty(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;
	
	public void updatePushed(HashMap<String, Object> map) throws DAOException;
	
	public void updateVersion(HashMap<String, Object> map) throws DAOException;
	
	public ScmPurOrder2 selectByPoNo(HashMap<String, Object> map) throws DAOException;
	
	public void updateLockStatusOrContractNo(HashMap<String, Object> map) throws DAOException;
	
	public int updateBillNoChangeTime(ScmPurOrder2 scmPurOrder) throws DAOException;
	
	public List<ScmPurOrder2> selectByEntryIds(HashMap<String, Object> map) throws DAOException;
	
	public int updateUnSendedStaus(HashMap<String, Object> map) throws DAOException;

	public ScmPurOrder2 selectByTypeCode(HashMap<String, Object> map) throws DAOException;

}

