package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierToFinListParamsApi")
public class SupplierToFinListParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	private SupplierToFinListParams params;
	
	public SupplierToFinListParams getParams() {
		return params;
	}
	public void setParams(SupplierToFinListParams params) {
		this.params = params;
	}
}
