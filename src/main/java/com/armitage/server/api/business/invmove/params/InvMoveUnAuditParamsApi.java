package com.armitage.server.api.business.invmove.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveUnAuditParamsApi")
public class InvMoveUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMoveUnAuditParams params;

	public InvMoveUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvMoveUnAuditParams params) {
		this.params = params;
	}
	
}
