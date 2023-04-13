package com.armitage.server.api.business.purrequire.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqWareHouseResultApi",description="返回结果集")
public class PurReqWareHouseResultApi extends ResultApi {
	private List<PurReqWareHouseResult> resultList;

	public List<PurReqWareHouseResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReqWareHouseResult> resultList) {
		this.resultList = resultList;
	}

}
