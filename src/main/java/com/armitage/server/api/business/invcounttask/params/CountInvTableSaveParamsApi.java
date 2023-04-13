package com.armitage.server.api.business.invcounttask.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CountInvTableSaveParamsApi")
public class CountInvTableSaveParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CountInvTableSaveParams params;

	public CountInvTableSaveParams getParams() {
		return params;
	}

	public void setParams(CountInvTableSaveParams params) {
		this.params = params;
	}	
}
