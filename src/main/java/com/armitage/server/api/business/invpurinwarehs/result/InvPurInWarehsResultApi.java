package com.armitage.server.api.business.invpurinwarehs.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvPurInWarehsResultApi",description="返回结果集")
public class InvPurInWarehsResultApi extends ResultApi {
	private InvPurInWarehsResult result;

	public InvPurInWarehsResult getResult() {
		return result;
	}

	public void setResult(InvPurInWarehsResult result) {
		this.result = result;
	}
	
}
