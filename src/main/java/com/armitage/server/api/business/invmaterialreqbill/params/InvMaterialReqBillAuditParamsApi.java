package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillAuditParamsApi")
public class InvMaterialReqBillAuditParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillAuditParams params;

	public InvMaterialReqBillAuditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillAuditParams params) {
		this.params = params;
	}

}
