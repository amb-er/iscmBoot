package com.armitage.server.api.business.invmaterialreq.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqPersonResultApi",description="返回结果集")
public class InvMaterialReqPersonResultApi extends ResultApi {
	private List<InvMaterialReqPersonResult> resultList;

	public List<InvMaterialReqPersonResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqPersonResult> resultList) {
		this.resultList = resultList;
	}

}
