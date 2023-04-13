package com.armitage.server.api.business.basedata.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value="WareHouseResultApi")
public class WareHouseResultApi extends ResultApi {
	private List<WareHouseResult> resultList;

	public List<WareHouseResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<WareHouseResult> resultList) {
		this.resultList = resultList;
	}
	
}
