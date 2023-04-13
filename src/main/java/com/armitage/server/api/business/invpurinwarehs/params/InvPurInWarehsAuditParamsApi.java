package com.armitage.server.api.business.invpurinwarehs.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvPurInWarehsAuditParamsApi")
public class InvPurInWarehsAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvPurInWarehsAuditParams params;

	public InvPurInWarehsAuditParams getParams() {
		return params;
	}

	public void setParams(InvPurInWarehsAuditParams params) {
		this.params = params;
	}

}
