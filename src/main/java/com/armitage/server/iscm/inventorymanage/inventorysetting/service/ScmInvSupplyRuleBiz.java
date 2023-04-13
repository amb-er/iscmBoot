
package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRuleWSBean;

public interface ScmInvSupplyRuleBiz extends BaseBiz<ScmInvSupplyRule> {

	ScmInvSupplyRuleWSBean generatePlan(ScmInvSupplyRule scmInvSupplyRule, Param param) throws AppException;

}
