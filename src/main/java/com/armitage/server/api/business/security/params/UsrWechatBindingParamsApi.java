package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatBindingParamsApi")
public class UsrWechatBindingParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private UsrWechatBindingParams params;

	public UsrWechatBindingParams getParams() {
		return params;
	}

	public void setParams(UsrWechatBindingParams params) {
		this.params = params;
	}

}
