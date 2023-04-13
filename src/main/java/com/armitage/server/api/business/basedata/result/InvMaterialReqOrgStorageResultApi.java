package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqOrgStorageResultApi",description="返回结果集")
public class InvMaterialReqOrgStorageResultApi extends ResultApi {
	private List<InvMaterialReqOrgStorageResult> resultList;

	public List<InvMaterialReqOrgStorageResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqOrgStorageResult> resultList) {
		this.resultList = resultList;
	}

}

