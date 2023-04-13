package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="UsrPrivResultApi",description="用户权限结果")
public class UsrPrivResultApi extends ResultApi {
	private UsrPrivResult result;

	public UsrPrivResult getResult() {
		return result;
	}

	public void setResult(UsrPrivResult result) {
		this.result = result;
	}

}
