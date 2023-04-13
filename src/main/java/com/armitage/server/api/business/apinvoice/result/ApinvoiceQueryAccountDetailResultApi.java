package com.armitage.server.api.business.apinvoice.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ApinvoiceQueryAccountDetailResultApi",description="返回结果集")
public class ApinvoiceQueryAccountDetailResultApi extends ResultApi {
	private List<ApinvoiceQueryAccountDetailResult> resultList;

	public List<ApinvoiceQueryAccountDetailResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ApinvoiceQueryAccountDetailResult> resultList) {
		this.resultList = resultList;
	}
}
