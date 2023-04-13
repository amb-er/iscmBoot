package com.armitage.server.api.business.purreturns.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReturnsUnAuditParamsApi")
public class PurReturnsUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReturnsUnAuditParams params;

	public PurReturnsUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurReturnsUnAuditParams params) {
		this.params = params;
	}
	
}
