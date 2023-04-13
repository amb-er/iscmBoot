package com.armitage.server.api.business.datareport.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupSupplyOfMaterialDetailsParamsApi")
public class SupSupplyOfMaterialDetailsParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private SupSupplyOfMaterialDetailsParams params;

	public SupSupplyOfMaterialDetailsParams getParams() {
		return params;
	}

	public void setParams(SupSupplyOfMaterialDetailsParams params) {
		this.params = params;
	}
}
