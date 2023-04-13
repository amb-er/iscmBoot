package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvCountingCostParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private List<InvCountingCostListParams> params;

	public List<InvCountingCostListParams> getParams() {
		return params;
	}

	public void setParams(List<InvCountingCostListParams> params) {
		this.params = params;
	}

}
