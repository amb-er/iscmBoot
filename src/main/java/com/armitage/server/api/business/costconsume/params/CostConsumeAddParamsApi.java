package com.armitage.server.api.business.costconsume.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CostConsumeAddParamsApi")
public class CostConsumeAddParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CostConsumeAddParams params;

	public CostConsumeAddParams getParams() {
		return params;
	}

	public void setParams(CostConsumeAddParams params) {
		this.params = params;
	}
	
}
