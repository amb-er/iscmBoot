package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderUnAuditParamsApi")
public class PurOrderUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderUnAuditParams params;

	public PurOrderUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurOrderUnAuditParams params) {
		this.params = params;
	}
	
}
