package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SysParamParamsApi")
public class SysParamParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private SysParamParams params;

	public SysParamParams getParams() {
		return params;
	}

	public void setParams(SysParamParams params) {
		this.params = params;
	}

}
