package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationUnSubmitParamsApi")
public class PurQuotationUnSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationUnSubmitParams params;

	public PurQuotationUnSubmitParams getParams() {
		return params;
	}

	public void setParams(PurQuotationUnSubmitParams params) {
		this.params = params;
	}
	
}
