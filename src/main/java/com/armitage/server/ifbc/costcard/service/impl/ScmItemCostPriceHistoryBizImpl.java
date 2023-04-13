package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceHistoryDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceHistory;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLog;
import com.armitage.server.ifbc.costcard.service.ScmItemCostPriceHistoryBiz;
import org.springframework.stereotype.Service;

@Service("scmItemCostPriceHistoryBiz")
public class ScmItemCostPriceHistoryBizImpl extends BaseBizImpl<ScmItemCostPriceHistory> implements ScmItemCostPriceHistoryBiz {

	@Override
	public void updateItemPriceHistory(ScmItemCostPriceLog scmItemCostPriceLog, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("logId", scmItemCostPriceLog.getId());
		((ScmItemCostPriceHistoryDAO)dao).batchAddItemPriceHistory(map);
	}
}
