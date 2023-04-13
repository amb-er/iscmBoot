package com.armitage.server.api.business.purreceive.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqOrgUnitResultApi",description="返回结果集")
public class PurReceiveDeptResultApi extends ResultApi {
	private List<PurReceiveDeptResult> resultList;

	public List<PurReceiveDeptResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<PurReceiveDeptResult> resultList) {
		this.resultList = resultList;
	}

}
