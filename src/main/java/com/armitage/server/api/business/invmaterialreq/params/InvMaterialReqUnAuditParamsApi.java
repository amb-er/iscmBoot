package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqUnAuditParamsApi")
public class InvMaterialReqUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqUnAuditParams params;

	public InvMaterialReqUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqUnAuditParams params) {
		this.params = params;
	}
	
}
