package com.armitage.server.api.business.purorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderLockBillParamsApi")
public class PurOrderLockBillParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurOrderLockBillParams params;

	public PurOrderLockBillParams getParams() {
		return params;
	}

	public void setParams(PurOrderLockBillParams params) {
		this.params = params;
	}
	
}
