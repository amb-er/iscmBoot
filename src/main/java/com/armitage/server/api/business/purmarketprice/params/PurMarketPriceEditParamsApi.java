package com.armitage.server.api.business.purmarketprice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceEditParamsApi")
public class PurMarketPriceEditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurMarketPriceEditParams params;

	public PurMarketPriceEditParams getParams() {
		return params;
	}

	public void setParams(PurMarketPriceEditParams params) {
		this.params = params;
	}
}
