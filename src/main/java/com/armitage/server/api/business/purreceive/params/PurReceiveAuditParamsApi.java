package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveAuditParamsApi")
public class PurReceiveAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveAuditParams params;

	public PurReceiveAuditParams getParams() {
		return params;
	}

	public void setParams(PurReceiveAuditParams params) {
		this.params = params;
	}
	
}
