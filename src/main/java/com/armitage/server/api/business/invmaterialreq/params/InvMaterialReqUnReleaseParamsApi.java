package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqUnReleaseParamsApi")
public class InvMaterialReqUnReleaseParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqUnReleaseParams params;

	public InvMaterialReqUnReleaseParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqUnReleaseParams params) {
		this.params = params;
	}
	
	
}
