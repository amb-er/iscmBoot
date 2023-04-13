package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PlatformInfoParamsApi")
public class PlatformInfoParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PlatformInfoParams params;

	public PlatformInfoParams getParams() {
		return params;
	}

	public void setParams(PlatformInfoParams params) {
		this.params = params;
	}

}
