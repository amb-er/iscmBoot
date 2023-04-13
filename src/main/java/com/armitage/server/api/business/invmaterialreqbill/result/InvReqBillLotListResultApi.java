package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvReqBillLotListResultApi",description="返回结果集")
public class InvReqBillLotListResultApi extends ResultApi{

	private List<InvReqBillLotListResult> resultList;

	public List<InvReqBillLotListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvReqBillLotListResult> resultList) {
		this.resultList = resultList;
	}
}
