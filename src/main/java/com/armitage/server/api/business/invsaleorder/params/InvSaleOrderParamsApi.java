package com.armitage.server.api.business.invsaleorder.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleOrderParamsApi")
public class InvSaleOrderParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvSaleOrderParams params;

	public InvSaleOrderParams getParams() {
		return params;
	}

	public void setParams(InvSaleOrderParams params) {
		this.params = params;
	}

}
