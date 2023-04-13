package com.armitage.server.api.business.security.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="OrgCostCenterResultApi",description="返回结果集")
public class OrgCostCenterResultApi extends ResultApi {
	private List<OrgCostCenterResult> resultList;

	public List<OrgCostCenterResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<OrgCostCenterResult> resultList) {
		this.resultList = resultList;
	}

}
