package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;

public interface ScmCostCardBiz extends BaseBiz<ScmCostCard2> {

	public ScmCostCard2 lock(ScmCostCard2 scmCostCard, Param param) throws AppException;
	
	public ScmCostCard2 unlock(ScmCostCard2 scmCostCard, Param param) throws AppException;
	
	public ScmCostCard2 auditCostCard(ScmCostCard2 scmCostCard, Param param) throws AppException;
	
	public List<ScmCostCard2> getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException;
	
	public void replaceItem(List<ScmCostCard2> scmCostCardList, ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException;
	
	public void copyCostCard(ScmCostCard2 scmCostCard, List<ScmCostCardDetail2> scmCostCardDetailList, Param param) throws AppException;
}
