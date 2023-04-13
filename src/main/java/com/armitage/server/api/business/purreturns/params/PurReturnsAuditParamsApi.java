package com.armitage.server.api.business.purreturns.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReturnsAuditParamsApi")
public class PurReturnsAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReturnsAuditParams params;

	public PurReturnsAuditParams getParams() {
		return params;
	}

	public void setParams(PurReturnsAuditParams params) {
		this.params = params;
	}

}
