package com.armitage.server.api.business.apinvoice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceQueryAccountParamsApi")
public class ApinvoiceQueryAccountParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApinvoiceQueryAccountParams params;

	public ApinvoiceQueryAccountParams getParams() {
		return params;
	}

	public void setParams(ApinvoiceQueryAccountParams params) {
		this.params = params;
	}

}
