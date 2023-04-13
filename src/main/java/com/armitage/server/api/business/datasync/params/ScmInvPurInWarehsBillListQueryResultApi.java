package com.armitage.server.api.business.datasync.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class ScmInvPurInWarehsBillListQueryResultApi extends RequestParams {

	@ApiModelProperty(required = true)
	private ScmInvPurInWarehsBillListQueryParams params;

	public ScmInvPurInWarehsBillListQueryParams getParams() {
		return params;
	}

	public void setParams(ScmInvPurInWarehsBillListQueryParams params) {
		this.params = params;
	}
	
	
}
