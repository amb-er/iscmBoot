package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveEditParamsApi")
public class PurReceiveEditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveEditParams params;

	public PurReceiveEditParams getParams() {
		return params;
	}

	public void setParams(PurReceiveEditParams params) {
		this.params = params;
	}
	
}
