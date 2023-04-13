package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierBindDemanderParamsApi")
public class SupplierBindDemanderParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private SupplierBindDemanderParams params;
	public SupplierBindDemanderParams getParams() {
		return params;
	}
	public void setParams(SupplierBindDemanderParams params) {
		this.params = params;
	}
}
