package com.armitage.server.api.business.purmarketprice.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceSubmitParamsApi")
public class PurMarketPriceSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurMarketPriceSubmitParams params;

	public PurMarketPriceSubmitParams getParams() {
		return params;
	}

	public void setParams(PurMarketPriceSubmitParams params) {
		this.params = params;
	}
	
}
