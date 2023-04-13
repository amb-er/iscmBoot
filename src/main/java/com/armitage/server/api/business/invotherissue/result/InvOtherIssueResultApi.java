package com.armitage.server.api.business.invotherissue.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvOtherIssueResultApi",description="返回结果集")
public class InvOtherIssueResultApi extends ResultApi {
	private InvOtherIssueResult result;

	public InvOtherIssueResult getResult() {
		return result;
	}

	public void setResult(InvOtherIssueResult result) {
		this.result = result;
	}
	
}
