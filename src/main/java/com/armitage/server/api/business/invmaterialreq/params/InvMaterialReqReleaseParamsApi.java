package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqReleaseParamsApi")
public class InvMaterialReqReleaseParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqReleaseParams params;
	public InvMaterialReqReleaseParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqReleaseParams params) {
		this.params = params;
	}
	
}
