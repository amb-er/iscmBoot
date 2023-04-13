package com.armitage.server.api.business.appaymentbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="ApPaymentBillOAResultApi",description="返回结果集")
public class ApPaymentBillOAResultApi extends ResultApi {
	private List<ApPaymentBillOAResult> resultList;

	public List<ApPaymentBillOAResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ApPaymentBillOAResult> resultList) {
		this.resultList = resultList;
	}
}
