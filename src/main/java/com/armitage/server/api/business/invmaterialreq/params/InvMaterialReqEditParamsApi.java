package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqEditParamsApi")
public class InvMaterialReqEditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqEditParams params;

	public InvMaterialReqEditParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqEditParams params) {
		this.params = params;
	}
	
}
