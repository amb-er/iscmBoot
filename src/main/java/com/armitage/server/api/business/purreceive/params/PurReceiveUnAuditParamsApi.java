package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveUnAuditParamsApi")
public class PurReceiveUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReceiveUnAuditParams params;

	public PurReceiveUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurReceiveUnAuditParams params) {
		this.params = params;
	}
	
}
