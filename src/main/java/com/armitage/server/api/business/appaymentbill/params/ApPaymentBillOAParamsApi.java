package com.armitage.server.api.business.appaymentbill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApPaymentBillOAParamsApi")
public class ApPaymentBillOAParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private ApPaymentBillOAParams params;

	public ApPaymentBillOAParams getParams() {
		return params;
	}

	public void setParams(ApPaymentBillOAParams params) {
		this.params = params;
	}

}
