package com.armitage.server.api.business.invmaterialreq.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqInvOrgUnitParamsApi")
public class InvMaterialReqInvOrgUnitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvMaterialReqInvOrgUnitParams params;

	public InvMaterialReqInvOrgUnitParams getParams() {
		return params;
	}
	public void setParams(InvMaterialReqInvOrgUnitParams params) {
		this.params = params;
	}
}
