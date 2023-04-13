package com.armitage.server.api.business.datareport.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="DeptConsumeEntryDataParamsApi")
public class DeptConsumeEntryDataParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private DeptConsumeEntryDataParams params;

	public DeptConsumeEntryDataParams getParams() {
		return params;
	}

	public void setParams(DeptConsumeEntryDataParams params) {
		this.params = params;
	}
	
}
