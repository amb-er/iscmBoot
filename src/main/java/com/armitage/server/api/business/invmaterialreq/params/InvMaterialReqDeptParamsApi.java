package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqDeptParamsApi")
public class InvMaterialReqDeptParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqDeptParams params;

	public InvMaterialReqDeptParams getParams() {
		return params;
	}

	public void setParams(InvMaterialReqDeptParams params) {
		this.params = params;
	}	
}
