package com.armitage.server.api.business.purmarketprice.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceUnSubmitParamsApi")
public class PurMarketPriceUnSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurMarketPriceUnSubmitParams params;

	public PurMarketPriceUnSubmitParams getParams() {
		return params;
	}

	public void setParams(PurMarketPriceUnSubmitParams params) {
		this.params = params;
	}	
}
