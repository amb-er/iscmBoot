package com.armitage.server.api.business.invcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTaskWareHouseParamsApi")
public class CountInvTaskWareHouseParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountInvTaskWareHouseParams params;

	public CountInvTaskWareHouseParams getParams() {
		return params;
	}

	public void setParams(CountInvTaskWareHouseParams params) {
		this.params = params;
	}	
}
