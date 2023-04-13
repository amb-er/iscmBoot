package com.armitage.server.api.business.security.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="OrgCompanyParamsApi")
public class OrgCompanyParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private OrgCompanyParams params;
	public OrgCompanyParams getParams() {
		return params;
	}
	public void setParams(OrgCompanyParams params) {
		this.params = params;
	}
	
}
