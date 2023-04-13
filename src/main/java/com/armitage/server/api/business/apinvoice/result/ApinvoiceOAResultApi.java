package com.armitage.server.api.business.apinvoice.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ApinvoiceOAResultApi",description="返回结果集")
public class ApinvoiceOAResultApi extends ResultApi {
	private List<ApinvoiceOAResult> resultList;

	public List<ApinvoiceOAResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ApinvoiceOAResult> resultList) {
		this.resultList = resultList;
	}
}
