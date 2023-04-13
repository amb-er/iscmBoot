package com.armitage.server.api.business.costcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="DeptListResultApi")
public class DeptListResultApi extends ResultApi {
	private List<DeptListResult> resultList;

	public List<DeptListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<DeptListResult> resultList) {
		this.resultList = resultList;
	}
	
}
