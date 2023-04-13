package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PlatformInfoResultApi",description="查询平台信息返回结果")
public class PlatformInfoResultApi extends ResultApi {
	private PlatformInfoResult result;

	public PlatformInfoResult getResult() {
		return result;
	}

	public void setResult(PlatformInfoResult result) {
		this.result = result;
	}
	
}
