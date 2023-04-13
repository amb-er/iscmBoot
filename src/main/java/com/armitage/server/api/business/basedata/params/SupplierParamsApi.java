package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierParamsApi")
public class SupplierParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private SupplierParams params;
	public SupplierParams getParams() {
		return params;
	}
	public void setParams(SupplierParams params) {
		this.params = params;
	}
}
