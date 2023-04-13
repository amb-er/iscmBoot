package com.armitage.server.api.business.invcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CountInvTableResultApi")
public class CountInvTableResultApi extends ResultApi {
	private CountInvTableResult result;

	public CountInvTableResult getResult() {
		return result;
	}

	public void setResult(CountInvTableResult result) {
		this.result = result;
	}
}
