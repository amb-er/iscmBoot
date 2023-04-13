package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqDelParamsApi")
public class InvMaterialReqDelParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqDelParams params;

	public InvMaterialReqDelParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqDelParams params) {
		this.params = params;
	}

	
}
