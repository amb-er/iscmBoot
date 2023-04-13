
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;

public interface ScmPurBuyerDAO extends BasicDAO<ScmPurBuyer> {

	public List<ScmPurBuyer> selectByParentId(long parentId) throws DAOException;

	public ScmPurBuyer2 selectByCode(HashMap<String, Object> map) throws DAOException;
}
