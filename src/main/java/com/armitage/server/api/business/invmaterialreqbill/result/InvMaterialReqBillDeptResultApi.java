package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqBillDeptResultApi",description="返回结果集")
public class InvMaterialReqBillDeptResultApi extends ResultApi{

	private List<InvMaterialReqBillDeptResult> resultList;

	public List<InvMaterialReqBillDeptResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqBillDeptResult> resultList) {
		this.resultList = resultList;
	}
}
