package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireParamsApi")
public class PurRequireParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireParams params;

	public PurRequireParams getParams() {
		return params;
	}

	public void setParams(PurRequireParams params) {
		this.params = params;
	}
	
	
}
