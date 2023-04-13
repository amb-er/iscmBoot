package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurchaseTypeParamsApi")
public class PurchaseTypeParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurchaseTypeParams params;

	public PurchaseTypeParams getParams() {
		return params;
	}

	public void setParams(PurchaseTypeParams params) {
		this.params = params;
	}

	
}
