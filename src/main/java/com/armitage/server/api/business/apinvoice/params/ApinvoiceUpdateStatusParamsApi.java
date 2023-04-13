package com.armitage.server.api.business.apinvoice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceUpdateStatusParamsApi")
public class ApinvoiceUpdateStatusParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApinvoiceUpdateStatusParams params;

	public ApinvoiceUpdateStatusParams getParams() {
		return params;
	}

	public void setParams(ApinvoiceUpdateStatusParams params) {
		this.params = params;
	}
	
}
