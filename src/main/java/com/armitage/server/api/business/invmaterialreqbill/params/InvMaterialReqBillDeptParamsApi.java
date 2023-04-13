package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillDeptParamsApi")
public class InvMaterialReqBillDeptParamsApi extends RequestParams{
	
	@ApiModelProperty(required = true)
	private InvMaterialReqBillDeptParams params;

	public InvMaterialReqBillDeptParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillDeptParams params) {
		this.params = params;
	}
	
	
}
