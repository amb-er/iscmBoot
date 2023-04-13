
package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan2;

public interface ScmInvSupplyPlanDAO extends BasicDAO<ScmInvSupplyPlan> {

	List<ScmInvSupplyPlan2> selectByRuleId(long id) throws DAOException ;

	List<ScmMaterial2> SelectScmMaterialByItemIds(HashMap<String, Object> map) throws DAOException ;

}
