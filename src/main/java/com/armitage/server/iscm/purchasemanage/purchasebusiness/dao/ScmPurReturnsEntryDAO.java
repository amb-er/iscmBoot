
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;

public interface ScmPurReturnsEntryDAO extends BasicDAO<ScmPurReturnsEntry> {
	public List<ScmPurReturnsEntry2> selectByRtId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByRtId(HashMap<String, Object> map) throws DAOException;
}
