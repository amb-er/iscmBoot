package com.armitage.server.api.business.invSaleIssueBill.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleIssueReturnBillAddParamsApi")
public class InvSaleIssueReturnBillAddParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private InvSaleIssueReturnBillAddParams params;

	public InvSaleIssueReturnBillAddParams getParams() {
		return params;
	}

	public void setParams(InvSaleIssueReturnBillAddParams params) {
		this.params = params;
	}
	
}
