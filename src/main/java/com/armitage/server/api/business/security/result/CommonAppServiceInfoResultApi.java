package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CommonAppServiceInfoResultApi",description="应用服务返回结果")
public class CommonAppServiceInfoResultApi extends ResultApi {
	private CommonAppServiceInfoResult result;

	public CommonAppServiceInfoResult getResult() {
		return result;
	}

	public void setResult(CommonAppServiceInfoResult result) {
		this.result = result;
	}

}
