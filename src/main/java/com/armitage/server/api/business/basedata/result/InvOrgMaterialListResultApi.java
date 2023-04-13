package com.armitage.server.api.business.basedata.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvOrgMaterialListResultApi",description="返回结果集")
public class InvOrgMaterialListResultApi extends ResultApi {

	private List<InvOrgMaterialListResult> resultList;

	public List<InvOrgMaterialListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvOrgMaterialListResult> resultList) {
		this.resultList = resultList;
	}
	
}
