package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="SupplierToFinListResultApi",description="返回结果集")
public class SupplierToFinListResultApi extends ResultApi {
	private List<SupplierToFinListResult> resultList;

	public List<SupplierToFinListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SupplierToFinListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}
