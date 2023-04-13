
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;

public interface ScmPurSupplierSourceEntryDAO extends BasicDAO<ScmPurSupplierSourceEntry> {

	public List<ScmPurSupplierSourceEntry2> selectByBillId(HashMap<String, Object> map) throws DAOException;

}
