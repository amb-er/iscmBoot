package com.armitage.server.api.business.security.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="OrgStorageResultApi",description="返回结果集")
public class OrgStorageResultApi extends ResultApi {
	private List<OrgStorageResult> resultList;

	public List<OrgStorageResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<OrgStorageResult> resultList) {
		this.resultList = resultList;
	}

}

