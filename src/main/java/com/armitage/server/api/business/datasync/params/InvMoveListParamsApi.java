package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="InvMoveListParamsApi")
public class InvMoveListParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	public List<InvMoveListParams> params;

	public List<InvMoveListParams> getParams() {
		return params;
	}

	public void setParams(List<InvMoveListParams> params) {
		this.params = params;
	}



}
