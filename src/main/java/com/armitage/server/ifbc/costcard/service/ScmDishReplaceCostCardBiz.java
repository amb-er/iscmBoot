package com.armitage.server.ifbc.costcard.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCardDetail;

public interface ScmDishReplaceCostCardBiz extends BaseBiz<ScmDishReplaceCostCard> {

	public CommonBean getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException;
	
	public void replaceItem(ScmDishReplaceCostCard scmDishReplaceCostCard,List<ScmDishReplaceCostCardDetail> scmCostCardList,  Param param) throws AppException;
	
}
