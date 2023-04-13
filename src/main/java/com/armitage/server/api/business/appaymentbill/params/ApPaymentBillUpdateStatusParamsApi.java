package com.armitage.server.api.business.appaymentbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentBillUpdateStatusParamsApi")
public class ApPaymentBillUpdateStatusParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApPaymentBillUpdateStatusParams params;

	public ApPaymentBillUpdateStatusParams getParams() {
		return params;
	}

	public void setParams(ApPaymentBillUpdateStatusParams params) {
		this.params = params;
	}
	
}
