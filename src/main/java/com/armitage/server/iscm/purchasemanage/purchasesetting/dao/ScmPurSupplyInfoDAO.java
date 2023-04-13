
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;

public interface ScmPurSupplyInfoDAO extends BasicDAO<ScmPurSupplyInfo> {
	public List<ScmPurSupplyInfo2> findVendor(HashMap<String, Object> map) throws DAOException;

	public ScmPurSupplyInfo2 getSupplyInfoByItem(HashMap<String, Object> map) throws DAOException;
	
	public List<ScmPurSupplyInfo2> getSupplyInfoByItems(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurSupplyInfo2> getSupplyInfoByItemList(HashMap<String, Object> map) throws DAOException;

	public int addBySupplierSource(HashMap<String, Object> map) throws DAOException;

	public ScmPurSupplyInfo2 getSupplyInfoByItemAndVendor(HashMap<String, Object> map) throws DAOException;

	public List<ScmPurSupplyInfo2> getSupplyInfoByItemSAndVendorS(HashMap<String, Object> map) throws DAOException;

}
