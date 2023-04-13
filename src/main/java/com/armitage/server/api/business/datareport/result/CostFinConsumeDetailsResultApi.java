package com.armitage.server.api.business.datareport.result;

import java.util.List;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="CostFinConsumeDetailsResultApi")
public class CostFinConsumeDetailsResultApi extends ResultApi {
	
	private List<CostFinConsumeDetailsResult> resultList;

	public List<CostFinConsumeDetailsResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CostFinConsumeDetailsResult> resultList) {
		this.resultList = resultList;
	}

}
