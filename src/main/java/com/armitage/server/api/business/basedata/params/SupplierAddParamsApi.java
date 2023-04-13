package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierAddParamsApi")
public class SupplierAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private SupplierAddParams params;

	public SupplierAddParams getParams() {
		return params;
	}

	public void setParams(SupplierAddParams params) {
		this.params = params;
	}
	
}
