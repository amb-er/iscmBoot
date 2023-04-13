package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveDelParamsApi")
public class PurReceiveDelParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveDelParams params;
	public PurReceiveDelParams getParams() {
		return params;
	}

	public void setParams(PurReceiveDelParams params) {
		this.params = params;
	}
}
