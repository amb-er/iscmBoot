package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqAddParamsApi")
public class InvMaterialReqAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqAddParams params;

	public InvMaterialReqAddParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqAddParams params) {
		this.params = params;
	}
	
}
