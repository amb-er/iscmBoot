package com.armitage.server.api.business.security.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="OrgAdminResultApi",description="返回结果集")
public class OrgAdminResultApi extends ResultApi {
	private List<OrgAdminResult> resultList;

	public List<OrgAdminResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<OrgAdminResult> resultList) {
		this.resultList = resultList;
	}

}

