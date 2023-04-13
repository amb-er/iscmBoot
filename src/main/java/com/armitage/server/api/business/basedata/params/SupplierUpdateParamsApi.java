package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierUpdateParamsApi")
public class SupplierUpdateParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private SupplierUpdateParams params;

	public SupplierUpdateParams getParams() {
		return params;
	}

	public void setParams(SupplierUpdateParams params) {
		this.params = params;
	}
	
}
