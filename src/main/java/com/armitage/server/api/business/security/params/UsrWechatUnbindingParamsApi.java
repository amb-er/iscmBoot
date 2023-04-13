package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatUnbindingParamsApi")
public class UsrWechatUnbindingParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private UsrWechatUnbindingParams params;

	public UsrWechatUnbindingParams getParams() {
		return params;
	}

	public void setParams(UsrWechatUnbindingParams params) {
		this.params = params;
	}

}
