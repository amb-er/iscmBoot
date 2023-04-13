package com.armitage.server.api.business.security.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="SysParamResultApi",description="系统参数查询返回结果")
public class SysParamResultApi extends ResultApi {
	private SysParamResult result;

	public SysParamResult getResult() {
		return result;
	}

	public void setResult(SysParamResult result) {
		this.result = result;
	}
	
}
