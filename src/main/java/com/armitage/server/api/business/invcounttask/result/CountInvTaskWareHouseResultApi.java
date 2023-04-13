package com.armitage.server.api.business.invcounttask.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="CountInvTaskWareHouseResultApi")
public class CountInvTaskWareHouseResultApi extends ResultApi {
	private List<CountInvTaskWareHouseResult> resultList;

	public List<CountInvTaskWareHouseResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<CountInvTaskWareHouseResult> resultList) {
		this.resultList = resultList;
	}
	
}
