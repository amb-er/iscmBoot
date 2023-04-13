package com.armitage.server.api.business.pursuppliersource.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierSourceParamsApi")
public class SupplierSourceParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private SupplierSourceParams params;

	public SupplierSourceParams getParams() {
		return params;
	}

	public void setParams(SupplierSourceParams params) {
		this.params = params;
	}

}
