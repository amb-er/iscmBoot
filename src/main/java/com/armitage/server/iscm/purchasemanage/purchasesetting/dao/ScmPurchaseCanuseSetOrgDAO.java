
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetOrg2;

public interface ScmPurchaseCanuseSetOrgDAO extends BasicDAO<ScmPurchaseCanuseSetOrg> {
	public List<ScmPurchaseCanuseSetOrg2> selectByPcsId(HashMap<String,Object> map) throws DAOException;
}
