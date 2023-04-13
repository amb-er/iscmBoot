package com.armitage.server.api.business.costcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountCostTableSaveParamsApi")
public class CountCostTableSaveParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountCostTableSaveParams params;

	public CountCostTableSaveParams getParams() {
		return params;
	}

	public void setParams(CountCostTableSaveParams params) {
		this.params = params;
	}	
}
