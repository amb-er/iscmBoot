package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;

public interface ScmPurRequireDAO extends BasicDAO<ScmPurRequire> {
	public ScmPurRequire2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	/**
	 * 计算金额
	 */
	public Map selectPurRequireTotalAmt(HashMap<String, Object> map);
	
	public int updateOutAudit(HashMap<String, Object> map) throws DAOException;
	

	public List<ScmPurBillDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurRequire2> selectByIds(HashMap<String, Object> map) throws DAOException;

	public ScmPurRequire2 selectByTypeCode(HashMap<String, Object> map) throws DAOException;

	public ScmPurRequire2 selectByPrNo(HashMap<String, Object> map) throws DAOException;
	
}
