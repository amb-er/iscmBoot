package com.armitage.server.api.business.invmove.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveParamsApi")
public class InvMoveParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMoveParams params;

	public InvMoveParams getParams() {
		return params;
	}

	public void setParams(InvMoveParams params) {
		this.params = params;
	}

}
