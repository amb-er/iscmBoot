package com.armitage.server.api.business.datasync.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="DataSyncResultApi",description="返回结果集")
public class DataSyncResultApi extends ResultApi {
	
	private List<DataSyncResult> resultList;

	public List<DataSyncResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<DataSyncResult> resultList) {
		this.resultList = resultList;
	}

	
}
