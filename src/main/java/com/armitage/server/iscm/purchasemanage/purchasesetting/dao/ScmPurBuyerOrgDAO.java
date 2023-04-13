
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;

public interface ScmPurBuyerOrgDAO extends BasicDAO<ScmPurBuyerOrg> {

	public List<ScmPurBuyerOrg2> selectByBuyerId(HashMap<String, Object> map) throws DAOException;
}
