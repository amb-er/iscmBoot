package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="SupplierResultApi",description="返回结果集")
public class SupplierResultApi extends ResultApi {
	private SupplierResult result;

	public SupplierResult getResult() {
		return result;
	}

	public void setResult(SupplierResult result) {
		this.result = result;
	}

}
