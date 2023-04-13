package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqOrgAdminResultApi",description="返回结果集")
public class InvMaterialReqOrgAdminResultApi extends ResultApi {
	private List<InvMaterialReqOrgAdminResult> resultList;

	public List<InvMaterialReqOrgAdminResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvMaterialReqOrgAdminResult> resultList) {
		this.resultList = resultList;
	}

}

