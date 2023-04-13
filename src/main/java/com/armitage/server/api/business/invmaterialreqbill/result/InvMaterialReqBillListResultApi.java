package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurRequireListResultApi",description="返回结果集")
public class InvMaterialReqBillListResultApi extends ResultApi{
	private List<InvMaterialReqBillListResult> resultList;

	public List<InvMaterialReqBillListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqBillListResult> resultList) {
		this.resultList = resultList;
	}

	
}
