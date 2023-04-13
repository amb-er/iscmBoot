package com.armitage.server.api.business.purmarketprice.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurMarketPriceResultApi",description="市调单详情内容")
public class PurMarketPriceResultApi extends ResultApi {
	private PurMarketPriceResult result;

	public PurMarketPriceResult getResult() {
		return result;
	}

	public void setResult(PurMarketPriceResult result) {
		this.result = result;
	}
	
}
