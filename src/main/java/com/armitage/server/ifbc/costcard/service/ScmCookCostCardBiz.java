package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;

public interface ScmCookCostCardBiz extends BaseBiz<ScmCookCostCard2> {
	
	public ScmCookCostCard2 lock(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException;
	
	public ScmCookCostCard2 unlock(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException;
	
	public ScmCookCostCard2 auditCostCard(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException;
	
	public List<ScmCookCostCard2> getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException;
	
	public void copyCostCard(ScmCookCostCard2 scmCookCostCard, List<ScmCookCostCardDetail2> scmCookCostCardDetailList, Param param) throws AppException;
	
	public ScmCookCostCard2 selectByCookId(long cookId, Param param) throws AppException;
}
