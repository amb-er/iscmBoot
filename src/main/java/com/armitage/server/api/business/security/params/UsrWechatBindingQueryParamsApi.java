package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatBindingQueryParamsApi")
public class UsrWechatBindingQueryParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private UsrWechatBindingQueryParams params;

	public UsrWechatBindingQueryParams getParams() {
		return params;
	}

	public void setParams(UsrWechatBindingQueryParams params) {
		this.params = params;
	}

}
