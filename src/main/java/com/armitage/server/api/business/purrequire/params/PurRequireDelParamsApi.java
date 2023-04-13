package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireDelParamsApi")
public class PurRequireDelParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireDelParams params;

	public PurRequireDelParams getParams() {
		return params;
	}

	public void setParams(PurRequireDelParams params) {
		this.params = params;
	}

	
}
