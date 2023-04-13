
package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlanEntry2;

public interface ScmInvSupplyPlanEntryBiz extends BaseBiz<ScmInvSupplyPlanEntry> {

	List<ScmInvSupplyPlanEntry2> selectByPlanId(long id, Param param) throws AppException;

}
