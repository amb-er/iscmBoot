package com.armitage.server.api.business.invsaleorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleOrderUnAuditParamsApi")
public class InvSaleOrderUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvSaleOrderUnAuditParams params;

	public InvSaleOrderUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvSaleOrderUnAuditParams params) {
		this.params = params;
	}
	
}
