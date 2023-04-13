package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmPurRequireBillParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	public List<ScmPurRequireBillParam> params;

	public List<ScmPurRequireBillParam> getParams() {
		return params;
	}

	public void setParams(List<ScmPurRequireBillParam> params) {
		this.params = params;
	}


}
