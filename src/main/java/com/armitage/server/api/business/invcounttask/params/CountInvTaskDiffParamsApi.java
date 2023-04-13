package com.armitage.server.api.business.invcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTaskDiffParamsApi")
public class CountInvTaskDiffParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountInvTaskDiffParams params;

	public CountInvTaskDiffParams getParams() {
		return params;
	}

	public void setParams(CountInvTaskDiffParams params) {
		this.params = params;
	}	
}
