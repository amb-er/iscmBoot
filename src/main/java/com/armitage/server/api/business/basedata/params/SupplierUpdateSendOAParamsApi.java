package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierUpdateSendOAParamsApi")
public class SupplierUpdateSendOAParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private SupplierUpdateSendOAParams params;

	public SupplierUpdateSendOAParams getParams() {
		return params;
	}

	public void setParams(SupplierUpdateSendOAParams params) {
		this.params = params;
	}
	
}
