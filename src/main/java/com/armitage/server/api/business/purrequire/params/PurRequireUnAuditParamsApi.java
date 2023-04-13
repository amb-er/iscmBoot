package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireUnAuditParamsApi")
public class PurRequireUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireUnAuditParams params;
	public PurRequireUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurRequireUnAuditParams params) {
		this.params = params;
	}
	
}
