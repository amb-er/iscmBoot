package com.armitage.server.api.business.invSaleIssueBill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleIssueBillParamsApi")
public class InvSaleIssueBillParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvSaleIssueBillParams params;

	public InvSaleIssueBillParams getParams() {
		return params;
	}

	public void setParams(InvSaleIssueBillParams params) {
		this.params = params;
	}

}
