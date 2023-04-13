package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationUnAuditParamsApi")
public class PurQuotationUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationUnAuditParams params;

	public PurQuotationUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurQuotationUnAuditParams params) {
		this.params = params;
	}
	
}
