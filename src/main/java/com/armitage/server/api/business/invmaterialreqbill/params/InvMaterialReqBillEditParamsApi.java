package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillEditParamsApi")
public class InvMaterialReqBillEditParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvMaterialReqBillEditParams params;

	public InvMaterialReqBillEditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillEditParams params) {
		this.params = params;
	}
	
}
