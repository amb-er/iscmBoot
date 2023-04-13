package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderUpdateContractNoParamsApi")
public class PurOrderUpdateContractNoParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderUpdateContractNoParams params;

	public PurOrderUpdateContractNoParams getParams() {
		return params;
	}

	public void setParams(PurOrderUpdateContractNoParams params) {
		this.params = params;
	}
	
}
