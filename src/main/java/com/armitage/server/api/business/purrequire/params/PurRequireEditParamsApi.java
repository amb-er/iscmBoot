package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireEditParamsApi")
public class PurRequireEditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireEditParams params;

	public PurRequireEditParams getParams() {
		return params;
	}

	public void setParams(PurRequireEditParams params) {
		this.params = params;
	}
	
}
