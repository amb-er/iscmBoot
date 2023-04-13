package com.armitage.server.api.business.audit.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AuditStatusParams")
public class AuditStatusParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private AuditStatusParams params;

	public AuditStatusParams getParams() {
		return params;
	}

	public void setParams(AuditStatusParams params) {
		this.params = params;
	}
	
}
