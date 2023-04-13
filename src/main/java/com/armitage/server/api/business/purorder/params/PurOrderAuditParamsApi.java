package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderAuditParamsApi")
public class PurOrderAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderAuditParams params;
	public PurOrderAuditParams getParams() {
		return params;
	}

	public void setParams(PurOrderAuditParams params) {
		this.params = params;
	}
	
}
