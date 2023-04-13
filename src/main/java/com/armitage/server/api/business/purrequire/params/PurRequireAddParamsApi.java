package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireAddParamsApi")
public class PurRequireAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireAddParams params;

	public PurRequireAddParams getParams() {
		return params;
	}

	public void setParams(PurRequireAddParams params) {
		this.params = params;
	}
	
}
