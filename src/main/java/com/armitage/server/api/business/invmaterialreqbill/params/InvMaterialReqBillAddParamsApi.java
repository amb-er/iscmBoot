package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillAddParamsApi")
public class InvMaterialReqBillAddParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillAddParams params;

	public InvMaterialReqBillAddParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillAddParams params) {
		this.params = params;
	}
	
}
