package com.armitage.server.api.business.purquotation.params;


import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationSubmitParamsApi")
public class PurQuotationSubmitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationSubmitParams params;

	public PurQuotationSubmitParams getParams() {
		return params;
	}

	public void setParams(PurQuotationSubmitParams params) {
		this.params = params;
	}
	
	
}
