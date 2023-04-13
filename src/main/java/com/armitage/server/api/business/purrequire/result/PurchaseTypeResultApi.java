package com.armitage.server.api.business.purrequire.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurchaseTypeResultApi",description="返回结果集")
public class PurchaseTypeResultApi extends ResultApi {
	private List<PurchaseTypeResult> resultList;

	public List<PurchaseTypeResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurchaseTypeResult> resultList) {
		this.resultList = resultList;
	}

}
