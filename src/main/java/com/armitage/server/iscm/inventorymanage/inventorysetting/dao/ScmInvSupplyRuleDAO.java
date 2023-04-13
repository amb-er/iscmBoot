
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;

public interface ScmInvSupplyRuleDAO extends BasicDAO<ScmInvSupplyRule> {

	public List<ScmMaterial2> findAllBillQtyByItemIds(HashMap<String, Object> map) throws DAOException;

	public List<Long> selectByOrgInv(HashMap<String, Object> map) throws DAOException;
	public String selectByIds(String string) throws DAOException;
}
