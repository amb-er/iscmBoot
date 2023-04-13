package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationDelParamsApi")
public class PurQuotationDelParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationDelParams params;

	public PurQuotationDelParams getParams() {
		return params;
	}

	public void setParams(PurQuotationDelParams params) {
		this.params = params;
	}
	       
}
