package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvReqBillMaterialListResultApi",description="返回结果集")
public class InvReqBillMaterialListResultApi extends ResultApi{

	private List<InvReqBillMaterialListResult> resultList;

	public List<InvReqBillMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvReqBillMaterialListResult> resultList) {
		this.resultList = resultList;
	}
}
