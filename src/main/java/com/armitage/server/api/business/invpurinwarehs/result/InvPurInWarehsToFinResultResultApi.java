package com.armitage.server.api.business.invpurinwarehs.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="InvPurInWarehsToFinResultResultApi",description="返回结果集")
public class InvPurInWarehsToFinResultResultApi extends ResultApi {

	private List<InvPurInWarehsToFinResult> resultList;

	public List<InvPurInWarehsToFinResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<InvPurInWarehsToFinResult> resultList) {
		this.resultList = resultList;
	}
	
	
	
}
