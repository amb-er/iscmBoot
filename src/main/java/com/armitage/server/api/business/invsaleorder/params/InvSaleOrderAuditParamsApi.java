package com.armitage.server.api.business.invsaleorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleOrderAuditParamsApi")
public class InvSaleOrderAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvSaleOrderAuditParams params;

	public InvSaleOrderAuditParams getParams() {
		return params;
	}

	public void setParams(InvSaleOrderAuditParams params) {
		this.params = params;
	}

}
