package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireSubmitParamsApi")
public class PurRequireSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireSubmitParams params;
	public PurRequireSubmitParams getParams() {
		return params;
	}

	public void setParams(PurRequireSubmitParams params) {
		this.params = params;
	}
	
}
