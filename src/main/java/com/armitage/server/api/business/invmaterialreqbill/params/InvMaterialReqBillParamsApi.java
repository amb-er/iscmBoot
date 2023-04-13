package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillParamsApi")
public class InvMaterialReqBillParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillParams params;

	public InvMaterialReqBillParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillParams params) {
		this.params = params;
	}

}
