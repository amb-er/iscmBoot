
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;

public interface ScmPurGroupDAO extends BasicDAO<ScmPurGroup> {
	public List<ScmPurGroup> selectByPurGroupNo(HashMap<String,Object> map) throws DAOException;
	
}
