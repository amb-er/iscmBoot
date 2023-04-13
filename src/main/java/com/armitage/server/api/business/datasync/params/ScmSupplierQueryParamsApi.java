package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmSupplierQueryParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmSupplierQueryParams params;

	public ScmSupplierQueryParams getParams() {
		return params;
	}

	public void setParams(ScmSupplierQueryParams params) {
		this.params = params;
	}
}
