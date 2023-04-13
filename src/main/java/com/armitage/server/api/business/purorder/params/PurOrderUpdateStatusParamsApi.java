package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderUpdateStatusParamsApi")
public class PurOrderUpdateStatusParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderUpdateStatusParams params;

	public PurOrderUpdateStatusParams getParams() {
		return params;
	}

	public void setParams(PurOrderUpdateStatusParams params) {
		this.params = params;
	}
	
}
