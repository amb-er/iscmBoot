package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;

public interface ScmPurOrderEntryDAO extends BasicDAO<ScmPurOrderEntry> {
	public List<ScmPurOrderEntry2> selectByPoId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPoId(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurOrderEntry2> selectByPoIdAndSaleIssueBill(HashMap<String, Object> map) throws DAOException;
	public void writeBackInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurOrderEntry2> selectByPoIdAndOtherIssueBill(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurOrderEntry2> selectStatusByIds(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByLineId(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurOrderEntry2> selectByPriceId(HashMap<String, Object> map) throws DAOException;
	public void updatePurOrderPriceBillIdByPmId(HashMap<String, Object> map) throws DAOException;
	public int generateAdd(ScmPurOrderEntry2 scmPurOrderEntry) throws DAOException;
	public List<ScmPurOrderEntry2> selectByPvId(HashMap<String, Object> map) throws DAOException;
}

