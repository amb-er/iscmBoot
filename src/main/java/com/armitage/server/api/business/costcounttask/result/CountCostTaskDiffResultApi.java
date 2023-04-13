package com.armitage.server.api.business.costcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountCostTaskDiffResultApi")
public class CountCostTaskDiffResultApi extends ResultApi {
	private List<CountCostTaskDiffResult> resultList;

	public List<CountCostTaskDiffResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountCostTaskDiffResult> resultList) {
		this.resultList = resultList;
	}
	
}
