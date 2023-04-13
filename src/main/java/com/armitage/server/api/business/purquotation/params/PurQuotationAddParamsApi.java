package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationAddParamsApi")
public class PurQuotationAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationAddParams params;

	public PurQuotationAddParams getParams() {
		return params;
	}

	public void setParams(PurQuotationAddParams params) {
		this.params = params;
	}
}
