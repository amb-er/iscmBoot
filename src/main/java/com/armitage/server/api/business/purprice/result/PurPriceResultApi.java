package com.armitage.server.api.business.purprice.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurPriceResultApi",description="返回结果集")
public class PurPriceResultApi extends ResultApi {
	private PurPriceResult result;

	public PurPriceResult getResult() {
		return result;
	}

	public void setResult(PurPriceResult result) {
		this.result = result;
	}
	
}
