package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqBillPersonResultApi",description="返回结果集")
public class InvMaterialReqBillPersonResultApi extends ResultApi{

	private List<InvMaterialReqBillPersonResult> resultList;

	public List<InvMaterialReqBillPersonResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqBillPersonResult> resultList) {
		this.resultList = resultList;
	}
}
