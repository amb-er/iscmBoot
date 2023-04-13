package com.armitage.server.api.business.costcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountCostTaskDeptResultApi")
public class CountCostTaskDeptResultApi extends ResultApi {
	private List<CountCostTaskDeptResult> resultList;

	public List<CountCostTaskDeptResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountCostTaskDeptResult> resultList) {
		this.resultList = resultList;
	}
	
}
