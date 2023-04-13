package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqAuditParamsApi")
public class InvMaterialReqAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqAuditParams params;
	public InvMaterialReqAuditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqAuditParams params) {
		this.params = params;
	}
	
}
