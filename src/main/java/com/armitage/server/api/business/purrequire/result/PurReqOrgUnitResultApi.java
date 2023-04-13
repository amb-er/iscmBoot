package com.armitage.server.api.business.purrequire.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqOrgUnitResultApi",description="返回结果集")
public class PurReqOrgUnitResultApi extends ResultApi {
	private List<PurReqOrgUnitResult> resultList;

	public List<PurReqOrgUnitResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReqOrgUnitResult> resultList) {
		this.resultList = resultList;
	}

}
