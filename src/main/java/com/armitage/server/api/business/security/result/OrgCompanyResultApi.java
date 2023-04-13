package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="OrgCompanyResultApi",description="返回结果集")
public class OrgCompanyResultApi extends ResultApi {
	private OrgCompanyResult result;

	public OrgCompanyResult getResult() {
		return result;
	}

	public void setResult(OrgCompanyResult result) {
		this.result = result;
	}

}

