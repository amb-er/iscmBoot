package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqSubmitParamsApi")
public class InvMaterialReqSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqSubmitParams params;
	public InvMaterialReqSubmitParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqSubmitParams params) {
		this.params = params;
	}
	
}
