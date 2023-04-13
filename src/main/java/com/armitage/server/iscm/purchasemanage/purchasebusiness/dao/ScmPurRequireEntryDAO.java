package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;

public interface ScmPurRequireEntryDAO extends BasicDAO<ScmPurRequireEntry> {
	public List<ScmPurRequireEntry2> selectByPrId(HashMap<String, Object> map) throws DAOException;
	public void deleteByPrId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByPrId(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurRequireEntry2> selectByBuyerId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByLineId(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> viewPurRequestStatus(HashMap<String, Object> map) throws DAOException;
	public int updateGrantedStatus(ScmPurRequireEntry2 scmPurRequireEntry) throws DAOException;
	public void updatePurRequestByPmId(HashMap<String, Object> map) throws DAOException;
	public void updatePurRequestPriceBillIdByPmId(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> selectByPrId2(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> undoReleaseCheck(HashMap<Object, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> clearPurRequirePrice(HashMap<Object, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> selectByPrIdCount(HashMap<String, Object> map) throws DAOException;
	public List selectByPurOrgUnitNo(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> selectByPrIds(HashMap<String, Object> map) throws DAOException;
	public List<ScmPurRequireEntry2> selectAutoOrderListByPrId(HashMap<String, Object> map) throws DAOException;
}
