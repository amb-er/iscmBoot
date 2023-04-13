package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;

public interface ScmPurReceiveDAO extends BasicDAO<ScmPurReceive> {
	public ScmPurReceive selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReceive2> selectByPurOrder(HashMap<String, Object> map) throws DAOException;

	public ScmPurReceive2 selectByCkId(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReceive2> selectBySaleIssueBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReceive2> selectByOtherIssueBill(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurReceive2> findBypvNos(HashMap<String, Object> map) throws DAOException;

	public ScmPurReceive2 selectByBillCode(HashMap<String, Object> map) throws DAOException;
	
}

