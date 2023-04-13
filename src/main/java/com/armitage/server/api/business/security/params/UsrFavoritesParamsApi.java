package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrFavoritesParamsApi")
public class UsrFavoritesParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private UsrFavoritesParams params;

	public UsrFavoritesParams getParams() {
		return params;
	}

	public void setParams(UsrFavoritesParams params) {
		this.params = params;
	}

}
