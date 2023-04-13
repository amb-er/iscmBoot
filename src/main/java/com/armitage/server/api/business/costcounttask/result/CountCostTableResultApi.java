package com.armitage.server.api.business.costcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CountCostTableResultApi")
public class CountCostTableResultApi extends ResultApi {
	private CountCostTableResult result;

	public CountCostTableResult getResult() {
		return result;
	}

	public void setResult(CountCostTableResult result) {
		this.result = result;
	}

	
}
