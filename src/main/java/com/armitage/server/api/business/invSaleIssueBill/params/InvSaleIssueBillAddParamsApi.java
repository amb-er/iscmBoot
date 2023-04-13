package com.armitage.server.api.business.invSaleIssueBill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleIssueBillAddParamsApi")
public class InvSaleIssueBillAddParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvSaleIssueBillAddParams params;

	public InvSaleIssueBillAddParams getParams() {
		return params;
	}

	public void setParams(InvSaleIssueBillAddParams params) {
		this.params = params;
	}
	
}
