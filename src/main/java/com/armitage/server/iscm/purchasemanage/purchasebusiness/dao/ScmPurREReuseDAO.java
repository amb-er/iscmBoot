
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurREReuse;

public interface ScmPurREReuseDAO extends BasicDAO<ScmPurREReuse> {

	void cancelRefuse(HashMap<String, Object> map) throws DAOException;

	ScmPurREReuse selectByEntryId(HashMap<String, Object> map) throws DAOException;

	void deleteByEntryId(HashMap<String, Object> map) throws DAOException;

}
