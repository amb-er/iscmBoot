package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurMarketPriceEntryDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceEntryBiz;
import org.springframework.stereotype.Service;

@Service("scmPurMarketPriceEntryBiz")
public class ScmPurMarketPriceEntryBizImpl extends BaseBizImpl<ScmPurMarketPriceEntry2> implements ScmPurMarketPriceEntryBiz {

	@Override
	public List<ScmPurMarketPriceEntry2> selectByPiId(long piId, Param param) throws AppException {
		if(piId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("piId", piId);
			return ((ScmPurMarketPriceEntryDAO) dao).selectByPiId(map);
		}
		return null;
	}

}
