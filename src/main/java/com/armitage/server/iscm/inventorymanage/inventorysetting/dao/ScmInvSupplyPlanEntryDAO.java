
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;

public interface ScmInvSupplyPlanEntryDAO extends BasicDAO<ScmInvSupplyPlanEntry> {

	List<ScmInvSupplyPlanEntry2> selectByPlanId(HashMap map) throws DAOException;

}
