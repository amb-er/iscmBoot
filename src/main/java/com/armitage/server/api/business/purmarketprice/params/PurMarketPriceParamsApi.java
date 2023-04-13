package com.armitage.server.api.business.purmarketprice.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceParamsApi")
public class PurMarketPriceParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurMarketPriceParams params;

	public PurMarketPriceParams getParams() {
		return params;
	}

	public void setParams(PurMarketPriceParams params) {
		this.params = params;
	}	
}
