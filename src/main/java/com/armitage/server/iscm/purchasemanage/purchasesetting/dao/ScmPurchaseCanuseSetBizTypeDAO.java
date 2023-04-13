
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType2;

public interface ScmPurchaseCanuseSetBizTypeDAO extends BasicDAO<ScmPurchaseCanuseSetBizType> {

	public List<ScmPurchaseCanuseSetBizType2> selectByPcsId(HashMap<String,Object> map) throws DAOException;
	
}
