package com.armitage.server.api.business.invcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountInvTaskDiffResultApi")
public class CountInvTaskDiffResultApi extends ResultApi {
	private List<CountInvTaskDiffResult> resultList;

	public List<CountInvTaskDiffResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountInvTaskDiffResult> resultList) {
		this.resultList = resultList;
	}
	
}
