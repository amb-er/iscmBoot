package com.armitage.server.api.business.purmarketprice.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="PurMarketPriceListResultApi",description="市调员查询返回结果集")
public class PurMarketPersonResultApi extends ResultApi {
	private List<PurMarketPersonResult> resultList;

	public List<PurMarketPersonResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurMarketPersonResult> resultList) {
		this.resultList = resultList;
	}
	
}
