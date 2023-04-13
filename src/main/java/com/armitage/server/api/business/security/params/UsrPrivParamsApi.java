package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrPrivParamsApi")
public class UsrPrivParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private UsrPrivParams params;

	public UsrPrivParams getParams() {
		return params;
	}

	public void setParams(UsrPrivParams params) {
		this.params = params;
	}

}
