package com.armitage.server.api.business.security.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="OrgAdminToFinResultApi",description="返回结果集")
public class OrgAdminToFinResultApi extends ResultApi {
	private List<OrgAdminToFinResult> resultList;

	public List<OrgAdminToFinResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<OrgAdminToFinResult> resultList) {
		this.resultList = resultList;
	}

}

