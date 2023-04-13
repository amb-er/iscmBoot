package com.armitage.server.api.business.invcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountInvTaskListResultApi")
public class CountInvTaskListResultApi extends ResultApi {
	private List<CountInvTaskListResult> resultList;

	public List<CountInvTaskListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountInvTaskListResult> resultList) {
		this.resultList = resultList;
	}
	
}
