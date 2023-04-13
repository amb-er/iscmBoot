package com.armitage.server.api.business.costcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountCostTaskDeptParamsApi")
public class CountCostTaskDeptParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountCostTaskDeptParams params;

	public CountCostTaskDeptParams getParams() {
		return params;
	}

	public void setParams(CountCostTaskDeptParams params) {
		this.params = params;
	}	
}
