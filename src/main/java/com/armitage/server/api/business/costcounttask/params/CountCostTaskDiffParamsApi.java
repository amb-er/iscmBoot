package com.armitage.server.api.business.costcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountCostTaskDiffParamsApi")
public class CountCostTaskDiffParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountCostTaskDiffParams params;

	public CountCostTaskDiffParams getParams() {
		return params;
	}

	public void setParams(CountCostTaskDiffParams params) {
		this.params = params;
	}	
}
