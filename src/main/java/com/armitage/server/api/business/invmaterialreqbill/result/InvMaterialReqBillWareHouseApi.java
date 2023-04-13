package com.armitage.server.api.business.invmaterialreqbill.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqBillWareHouseApi",description="返回结果集")
public class InvMaterialReqBillWareHouseApi extends ResultApi{
	
	private List<InvMaterialReqBillWareHouseResult> resultList;

	public List<InvMaterialReqBillWareHouseResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqBillWareHouseResult> resultList) {
		this.resultList = resultList;
	}
}
