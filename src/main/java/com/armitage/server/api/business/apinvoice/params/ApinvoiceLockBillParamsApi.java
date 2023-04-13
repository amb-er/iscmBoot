package com.armitage.server.api.business.apinvoice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceLockBillParamsApi")
public class ApinvoiceLockBillParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApinvoiceLockBillParams params;

	public ApinvoiceLockBillParams getParams() {
		return params;
	}

	public void setParams(ApinvoiceLockBillParams params) {
		this.params = params;
	}
	
}
