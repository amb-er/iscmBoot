package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CheckUsrParamsApi")
public class CheckUsrParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CheckUsrParams params;

	public CheckUsrParams getParams() {
		return params;
	}

	public void setParams(CheckUsrParams params) {
		this.params = params;
	}

}
