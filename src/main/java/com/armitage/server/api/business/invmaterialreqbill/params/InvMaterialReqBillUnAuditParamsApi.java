package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillUnAuditParamsApi")
public class InvMaterialReqBillUnAuditParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillUnAuditParams params;

	public InvMaterialReqBillUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillUnAuditParams params) {
		this.params = params;
	}
	
}
