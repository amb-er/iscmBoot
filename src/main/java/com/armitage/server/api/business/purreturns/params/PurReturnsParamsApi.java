package com.armitage.server.api.business.purreturns.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReturnsParamsApi")
public class PurReturnsParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReturnsParams params;

	public PurReturnsParams getParams() {
		return params;
	}

	public void setParams(PurReturnsParams params) {
		this.params = params;
	}

}
