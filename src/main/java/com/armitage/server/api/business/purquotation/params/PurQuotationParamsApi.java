package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationParamsApi")
public class PurQuotationParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationParams params;

	public PurQuotationParams getParams() {
		return params;
	}

	public void setParams(PurQuotationParams params) {
		this.params = params;
	}
}
	
