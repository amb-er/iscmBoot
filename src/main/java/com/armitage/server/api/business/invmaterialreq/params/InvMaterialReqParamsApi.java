package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqParamsApi")
public class InvMaterialReqParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqParams params;

	public InvMaterialReqParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqParams params) {
		this.params = params;
	}	
}
