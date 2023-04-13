package com.armitage.server.ifbm.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbm.model.FbmCook2;

public interface FbmCookBiz extends BaseBiz<FbmCook2> {

	public List<FbmCook2> selectByDishId(ScmCostCard2 scmCostCard, Param param) throws AppException ;

}
