package com.armitage.server.api.business.purprice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceParamsApi")
public class PurPriceParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurPriceParams params;

	public PurPriceParams getParams() {
		return params;
	}

	public void setParams(PurPriceParams params) {
		this.params = params;
	}

}
