package com.armitage.server.api.business.pursuppliersource.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="SupplierSourceResultApi",description="返回结果集")
public class SupplierSourceResultApi extends ResultApi{
	private SupplierSourceResult result;

	public SupplierSourceResult getResult() {
		return result;
	}

	public void setResult(SupplierSourceResult result) {
		this.result = result;
	}
	
}
