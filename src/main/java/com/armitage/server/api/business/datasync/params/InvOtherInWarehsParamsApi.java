package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvOtherInWarehsParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	public List<InvOtherInWarehsListParams> params;

	public List<InvOtherInWarehsListParams> getParams() {
		return params;
	}

	public void setParams(List<InvOtherInWarehsListParams> params) {
		this.params = params;
	}

}
