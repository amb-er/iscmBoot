
package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleEntry2;

public interface ScmInvSupplyRuleEntryBiz extends BaseBiz<ScmInvSupplyRuleEntry> {

	List<ScmInvSupplyRuleEntry2> selectByRuleId(long id, Param param);

	void deleteByRuleIds(Long id, Param param);

	List<Long> selectEntryIdsByRuleId(long id, Param createParam);

}
