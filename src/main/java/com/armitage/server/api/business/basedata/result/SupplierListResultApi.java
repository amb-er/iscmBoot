package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="SupplierListResultApi",description="返回结果集")
public class SupplierListResultApi extends ResultApi {
	private List<SupplierListResult> resultList;

	public List<SupplierListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SupplierListResult> resultList) {
		this.resultList = resultList;
	}
	
	
}
