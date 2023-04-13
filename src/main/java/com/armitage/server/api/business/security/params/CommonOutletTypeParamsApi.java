package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CommonOutletTypeParamsApi")
public class CommonOutletTypeParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CommonOutletTypeParams params;

	public CommonOutletTypeParams getParams() {
		return params;
	}

	public void setParams(CommonOutletTypeParams params) {
		this.params = params;
	}

}
