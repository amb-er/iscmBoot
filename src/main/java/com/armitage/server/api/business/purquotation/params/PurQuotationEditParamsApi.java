package com.armitage.server.api.business.purquotation.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationEditParamsApi")
public class PurQuotationEditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurQuotationEditParams params;

	public PurQuotationEditParams getParams() {
		return params;
	}

	public void setParams(PurQuotationEditParams params) {
		this.params = params;
	}
}
