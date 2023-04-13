package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqWareHouseParamsApi")
public class PurReqWareHouseParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReqWareHouseParams params;

	public PurReqWareHouseParams getParams() {
		return params;
	}
	public void setParams(PurReqWareHouseParams params) {
		this.params = params;
	}
}
