package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CommonAppServiceInfoParamsApi")
public class CommonAppServiceInfoParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CommonAppServiceInfoParams params;

	public CommonAppServiceInfoParams getParams() {
		return params;
	}

	public void setParams(CommonAppServiceInfoParams params) {
		this.params = params;
	}

}
