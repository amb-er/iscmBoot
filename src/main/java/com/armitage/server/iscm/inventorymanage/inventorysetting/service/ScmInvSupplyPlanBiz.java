
package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyPlan2;

public interface ScmInvSupplyPlanBiz extends BaseBiz<ScmInvSupplyPlan2> {

	List<ScmInvSupplyPlan2> selectByRuleId(long id) throws AppException ;

	public void setConvertMapBiz(ScmInvSupplyPlan2 scmInvSupplyPlan, Param param) throws AppException;

	void generateRequire(CommonBean bean, Param param) throws AppException;
}
