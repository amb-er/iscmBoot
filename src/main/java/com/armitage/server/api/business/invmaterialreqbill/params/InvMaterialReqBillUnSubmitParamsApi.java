package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillUnSubmitParamsApi")
public class InvMaterialReqBillUnSubmitParamsApi extends RequestParams{

	@ApiModelProperty(required = true)
	private InvMaterialReqBillUnSubmitParams params;

	public InvMaterialReqBillUnSubmitParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillUnSubmitParams params) {
		this.params = params;
	}
}
