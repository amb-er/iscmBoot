package com.armitage.server.api.business.purreceive.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqPurOrgUnitResultApi",description="返回结果集")
public class PurRecPurOrgUnitResultApi extends ResultApi {
	private List<PurRecPurOrgUnitResult> resultList;

	public List<PurRecPurOrgUnitResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurRecPurOrgUnitResult> resultList) {
		this.resultList = resultList;
	}

}
