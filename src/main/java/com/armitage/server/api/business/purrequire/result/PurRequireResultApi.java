package com.armitage.server.api.business.purrequire.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurRequireResultApi",description="返回结果集")
public class PurRequireResultApi extends ResultApi {
	private PurRequireResult result;

	public PurRequireResult getResult() {
		return result;
	}

	public void setResult(PurRequireResult result) {
		this.result = result;
	}
	
}
