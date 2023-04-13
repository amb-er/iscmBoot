package com.armitage.server.api.business.invpurinwarehs.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvPurInWarehsParamsApi")
public class InvPurInWarehsParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvPurInWarehsParams params;

	public InvPurInWarehsParams getParams() {
		return params;
	}

	public void setParams(InvPurInWarehsParams params) {
		this.params = params;
	}

}
