package com.armitage.server.api.business.purmarketprice.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurMarketPriceListResultApi",description="市调单列表查询返回结果集")
public class PurMarketPriceListResultApi extends ResultApi {
	private List<PurMarketPriceListResult> resultList;

	public List<PurMarketPriceListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurMarketPriceListResult> resultList) {
		this.resultList = resultList;
	}
	
}
