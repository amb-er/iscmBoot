package com.armitage.server.api.business.apinvoice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceOAParamsApi")
public class ApinvoiceOAParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApinvoiceOAParams params;

	public ApinvoiceOAParams getParams() {
		return params;
	}

	public void setParams(ApinvoiceOAParams params) {
		this.params = params;
	}

}
