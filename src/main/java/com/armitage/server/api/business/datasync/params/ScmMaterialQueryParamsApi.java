package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmMaterialQueryParamsApi  extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmMaterialQueryParams params;

	public ScmMaterialQueryParams getParams() {
		return params;
	}

	public void setParams(ScmMaterialQueryParams params) {
		this.params = params;
	}
	
	
}
