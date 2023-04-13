package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveParamsApi")
public class PurReceiveParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveParams params;

	public PurReceiveParams getParams() {
		return params;
	}

	public void setParams(PurReceiveParams params) {
		this.params = params;
	}
	
	
}
