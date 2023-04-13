package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireAuditParamsApi")
public class PurRequireAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireAuditParams params;
	public PurRequireAuditParams getParams() {
		return params;
	}

	public void setParams(PurRequireAuditParams params) {
		this.params = params;
	}
	
}
