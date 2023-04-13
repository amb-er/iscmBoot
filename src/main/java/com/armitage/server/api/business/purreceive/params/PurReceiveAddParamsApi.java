package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveAddParamsApi")
public class PurReceiveAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveAddParams params;

	public PurReceiveAddParams getParams() {
		return params;
	}

	public void setParams(PurReceiveAddParams params) {
		this.params = params;
	}
	
}
