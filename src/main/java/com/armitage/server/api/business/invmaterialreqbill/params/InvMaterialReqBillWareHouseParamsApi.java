package com.armitage.server.api.business.invmaterialreqbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillWareHouseParamsApi")
public class InvMaterialReqBillWareHouseParamsApi extends RequestParams{

	@ApiModelProperty(required = true)
	private InvMaterialReqBillWareHouseParams params;

	public InvMaterialReqBillWareHouseParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqBillWareHouseParams params) {
		this.params = params;
	}
}
