package com.armitage.server.api.business.costcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountCostTaskListResultApi")
public class CountCostTaskListResultApi extends ResultApi {
	private List<CountCostTaskListResult> resultList;

	public List<CountCostTaskListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountCostTaskListResult> resultList) {
		this.resultList = resultList;
	}
	
}
