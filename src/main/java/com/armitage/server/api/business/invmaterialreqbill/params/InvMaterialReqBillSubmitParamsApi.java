package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillSubmitParamsApi")
public class InvMaterialReqBillSubmitParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillSubmitParams params;

	public InvMaterialReqBillSubmitParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillSubmitParams params) {
		this.params = params;
	}
}
