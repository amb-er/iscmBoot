package com.armitage.server.api.business.purrequire.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqPurOrgUnitResultApi",description="返回结果集")
public class PurReqPurOrgUnitResultApi extends ResultApi {
	private List<PurReqPurOrgUnitResult> resultList;

	public List<PurReqPurOrgUnitResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReqPurOrgUnitResult> resultList) {
		this.resultList = resultList;
	}

}
