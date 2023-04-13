package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="SupplierOAResultApi",description="返回结果集")
public class SupplierOAResultApi extends ResultApi {
	private List<SupplierOAResult> resultList;

	public List<SupplierOAResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<SupplierOAResult> resultList) {
		this.resultList = resultList;
	}

}
