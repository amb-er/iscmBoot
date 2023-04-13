package com.armitage.server.api.business.appaymentbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentLockBillParamsApi")
public class ApPaymentLockBillParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApPaymentLockBillParams params;

	public ApPaymentLockBillParams getParams() {
		return params;
	}

	public void setParams(ApPaymentLockBillParams params) {
		this.params = params;
	}
	
}
