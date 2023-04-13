package com.armitage.server.api.business.invmove.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveAuditParamsApi")
public class InvMoveAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMoveAuditParams params;

	public InvMoveAuditParams getParams() {
		return params;
	}

	public void setParams(InvMoveAuditParams params) {
		this.params = params;
	}

}
