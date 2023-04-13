package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationAuditParamsApi")
public class PurQuotationAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationAuditParams params;

	public PurQuotationAuditParams getParams() {
		return params;
	}

	public void setParams(PurQuotationAuditParams params) {
		this.params = params;
	}

}
