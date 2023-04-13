package com.armitage.server.api.business.apinvoice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceQueryAccountDetailParamsApi")
public class ApinvoiceQueryAccountDetailParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApinvoiceQueryAccountDetailParams params;

	public ApinvoiceQueryAccountDetailParams getParams() {
		return params;
	}

	public void setParams(ApinvoiceQueryAccountDetailParams params) {
		this.params = params;
	}

}
