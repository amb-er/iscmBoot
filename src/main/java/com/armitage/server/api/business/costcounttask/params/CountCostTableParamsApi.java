package com.armitage.server.api.business.costcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountCostTableParamsApi")
public class CountCostTableParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountCostTableParams params;

	public CountCostTableParams getParams() {
		return params;
	}

	public void setParams(CountCostTableParams params) {
		this.params = params;
	}	
}
