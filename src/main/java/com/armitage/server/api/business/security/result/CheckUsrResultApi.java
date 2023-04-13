package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CheckUsrResultApi",description="用户检查返回结果")
public class CheckUsrResultApi extends ResultApi {
	private CheckUsrResult result;

	public CheckUsrResult getResult() {
		return result;
	}

	public void setResult(CheckUsrResult result) {
		this.result = result;
	}
	
}
