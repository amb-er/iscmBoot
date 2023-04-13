package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveUnSubmitParamsApi")
public class PurReceiveUnSubmitParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private PurReceiveUnSubmitParams params;

	public PurReceiveUnSubmitParams getParams() {
		return params;
	}

	public void setParams(PurReceiveUnSubmitParams params) {
		this.params = params;
	}
	
}
