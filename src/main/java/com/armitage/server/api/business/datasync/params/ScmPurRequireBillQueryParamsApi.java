package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;


import io.swagger.annotations.ApiModelProperty;

public class ScmPurRequireBillQueryParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmPurRequireBillQueryParams params;

	public ScmPurRequireBillQueryParams getParams() {
		return params;
	}

	public void setParams(ScmPurRequireBillQueryParams params) {
		this.params = params;
	}
}
