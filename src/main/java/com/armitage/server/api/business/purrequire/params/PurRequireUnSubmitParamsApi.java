package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireUnSubmitParamsApi")
public class PurRequireUnSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireUnSubmitParams params;

	public PurRequireUnSubmitParams getParams() {
		return params;
	}

	public void setParams(PurRequireUnSubmitParams params) {
		this.params = params;
	}
	
	
}
