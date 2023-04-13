package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmInvPurInWarehsBillQueryParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmInvPurInWarehsBillQueryParams params;

	public ScmInvPurInWarehsBillQueryParams getParams() {
		return params;
	}

	public void setParams(ScmInvPurInWarehsBillQueryParams params) {
		this.params = params;
	}
	
	
}
