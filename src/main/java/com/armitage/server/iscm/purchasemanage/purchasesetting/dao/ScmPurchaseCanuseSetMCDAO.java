
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC2;

public interface ScmPurchaseCanuseSetMCDAO extends BasicDAO<ScmPurchaseCanuseSetMC> {

	public List<ScmPurchaseCanuseSetMC2> selectByPcsId(HashMap<String,Object> map) throws DAOException;

}
