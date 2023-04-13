package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderParamsApi")
public class PurOrderParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderParams params;

	public PurOrderParams getParams() {
		return params;
	}

	public void setParams(PurOrderParams params) {
		this.params = params;
	}

}
