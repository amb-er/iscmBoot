package com.armitage.server.api.business.invSaleIssueBill.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvSaleIssueBillResultApi",description="返回结果集")
public class InvSaleIssueBillResultApi extends ResultApi{
	private InvSaleIssueBillResult result;

	public InvSaleIssueBillResult getResult() {
		return result;
	}

	public void setResult(InvSaleIssueBillResult result) {
		this.result = result;
	}
	
}
