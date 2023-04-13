package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqUnSubmitParamsApi")
public class InvMaterialReqUnSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqUnSubmitParams params;

	public InvMaterialReqUnSubmitParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqUnSubmitParams params) {
		this.params = params;
	}
	
	
}
