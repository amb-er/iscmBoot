package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierOAParamsApi")
public class SupplierOAParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private SupplierOAParams params;

	public SupplierOAParams getParams() {
		return params;
	}

	public void setParams(SupplierOAParams params) {
		this.params = params;
	}
	
}
