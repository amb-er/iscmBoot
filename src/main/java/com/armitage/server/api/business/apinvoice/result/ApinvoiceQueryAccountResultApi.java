package com.armitage.server.api.business.apinvoice.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ApinvoiceQueryAccountResultApi",description="返回结果集")
public class ApinvoiceQueryAccountResultApi extends ResultApi {
	private List<ApinvoiceQueryAccountResult> resultList;

	public List<ApinvoiceQueryAccountResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ApinvoiceQueryAccountResult> resultList) {
		this.resultList = resultList;
	}
}
