package com.armitage.server.api.business.purrequire.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqPersonResultApi",description="返回结果集")
public class PurReqPersonResultApi extends ResultApi {
	private List<PurReqPersonResult> resultList;

	public List<PurReqPersonResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReqPersonResult> resultList) {
		this.resultList = resultList;
	}

}
