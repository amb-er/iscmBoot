package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveSubmitParamsApi")
public class PurReceiveSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveSubmitParams params;

	public PurReceiveSubmitParams getParams() {
		return params;
	}

	public void setParams(PurReceiveSubmitParams params) {
		this.params = params;
	}
	
}
