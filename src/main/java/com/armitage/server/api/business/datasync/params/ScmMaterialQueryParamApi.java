package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmMaterialQueryParamApi  extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmMaterialQueryParam params;

	public ScmMaterialQueryParam getParams() {
		return params;
	}

	public void setParams(ScmMaterialQueryParam params) {
		this.params = params;
	}
	
	
}
