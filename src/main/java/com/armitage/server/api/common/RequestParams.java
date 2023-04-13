package com.armitage.server.api.common;

import io.swagger.annotations.ApiModelProperty;

public class RequestParams {
	@ApiModelProperty(required = true)
    private ApiIntegratedRequest integratedRequest;

	public ApiIntegratedRequest getIntegratedRequest() {
		return integratedRequest;
	}

	public void setIntegratedRequest(ApiIntegratedRequest integratedRequest) {
		this.integratedRequest = integratedRequest;
	}
    
}
