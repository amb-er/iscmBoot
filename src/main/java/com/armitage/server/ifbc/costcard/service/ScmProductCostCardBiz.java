
package com.armitage.server.ifbc.costcard.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;

public interface ScmProductCostCardBiz extends BaseBiz<ScmProductCostCard2> {

	ScmProductCostCard2 auditCostCard(ScmProductCostCard2 scmCostCard, Param createParam) throws AppException;

}
