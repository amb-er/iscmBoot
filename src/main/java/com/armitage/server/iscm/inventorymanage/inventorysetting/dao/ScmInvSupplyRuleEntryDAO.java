
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry2;

public interface ScmInvSupplyRuleEntryDAO extends BasicDAO<ScmInvSupplyRuleEntry> {

	List<ScmInvSupplyRuleEntry2> selectByRuleId(HashMap map) throws DAOException;

	void deleteByRuleIds(Long id, Param param) throws DAOException;

	List<Long> selectEntryIdsByRuleId(long ruleId, Param createParam)throws DAOException;

}
