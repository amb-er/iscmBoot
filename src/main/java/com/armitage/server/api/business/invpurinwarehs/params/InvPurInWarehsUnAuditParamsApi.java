package com.armitage.server.api.business.invpurinwarehs.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvPurInWarehsUnAuditParamsApi")
public class InvPurInWarehsUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvPurInWarehsUnAuditParams params;

	public InvPurInWarehsUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvPurInWarehsUnAuditParams params) {
		this.params = params;
	}
	
}
