package com.armitage.server.api.business.purprice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceUnAuditParamsApi")
public class PurPriceUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurPriceUnAuditParams params;

	public PurPriceUnAuditParams getParams() {
		return params;
	}

	public void setParams(PurPriceUnAuditParams params) {
		this.params = params;
	}
	
}
