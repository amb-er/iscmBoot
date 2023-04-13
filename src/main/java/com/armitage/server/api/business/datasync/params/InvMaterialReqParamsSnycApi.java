package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvMaterialReqParamsSnycApi extends RequestParams {
	@ApiModelProperty(required = true)
	private List<InvMaterialReqListSParams> params;

	public List<InvMaterialReqListSParams> getParams() {
		return params;
	}

	public void setParams(List<InvMaterialReqListSParams> params) {
		this.params = params;
	}
}

