package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.dao.ScmDishProfitRateDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishProfitRate;
import com.armitage.server.ifbc.costcard.service.ScmDishProfitRateBiz;
import org.springframework.stereotype.Service;

@Service("scmDishProfitRateBiz")
public class ScmDishProfitRateBizImpl extends BaseBizImpl<ScmDishProfitRate> implements ScmDishProfitRateBiz {

	@Override
	public ScmDishProfitRate selectProfitRateByCostCard(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(scmCostCard != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("cardId", scmCostCard.getId());
			map.put("orgUnitNo", scmCostCard.getOrgUnitNo());
			map.put("dishCode", scmCostCard.getDishCode());
			return ((ScmDishProfitRateDAO)dao).selectProfitRateByCostCard(map);
		}
		return null;
	}

}
