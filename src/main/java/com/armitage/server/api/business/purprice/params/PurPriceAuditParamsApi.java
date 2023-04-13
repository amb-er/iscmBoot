package com.armitage.server.api.business.purprice.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceAuditParamsApi")
public class PurPriceAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurPriceAuditParams params;

	public PurPriceAuditParams getParams() {
		return params;
	}

	public void setParams(PurPriceAuditParams params) {
		this.params = params;
	}

}
