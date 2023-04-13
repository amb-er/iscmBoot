package com.armitage.server.api.business.invsaleorder.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvSaleOrderResultApi",description="返回结果集")
public class InvSaleOrderResultApi extends ResultApi {
	private InvSaleOrderResult result;

	public InvSaleOrderResult getResult() {
		return result;
	}

	public void setResult(InvSaleOrderResult result) {
		this.result = result;
	}
	
}
