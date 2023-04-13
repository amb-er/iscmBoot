
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg2;

public interface ScmPurSupplyRecOrgDAO extends BasicDAO<ScmPurSupplyRecOrg> {

	public List<ScmPurSupplyRecOrg2> selectBySupplyInfoId(HashMap<String, Object> map) throws DAOException;

	public int addBySupplierSource(HashMap<String, Object> map) throws DAOException;
}
