package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderOAParamsApi")
public class PurOrderOAParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderOAParams params;

	public PurOrderOAParams getParams() {
		return params;
	}

	public void setParams(PurOrderOAParams params) {
		this.params = params;
	}

}
