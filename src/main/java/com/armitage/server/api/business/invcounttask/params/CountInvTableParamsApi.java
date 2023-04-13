package com.armitage.server.api.business.invcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTableParamsApi")
public class CountInvTableParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountInvTableParams params;

	public CountInvTableParams getParams() {
		return params;
	}

	public void setParams(CountInvTableParams params) {
		this.params = params;
	}	
}
