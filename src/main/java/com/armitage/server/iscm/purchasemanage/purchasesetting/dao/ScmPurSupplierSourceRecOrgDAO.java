
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;

public interface ScmPurSupplierSourceRecOrgDAO extends BasicDAO<ScmPurSupplierSourceRecOrg> {

	public List<ScmPurSupplierSourceRecOrg2> selectByBillId(HashMap<String, Object> map) throws DAOException;

}
