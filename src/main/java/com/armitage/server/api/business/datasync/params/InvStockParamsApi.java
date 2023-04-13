package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvStockParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private List<InvStockListParams> params;

	public List<InvStockListParams> getParams() {
		return params;
	}

	public void setParams(List<InvStockListParams> params) {
		this.params = params;
	}
	
	
}
